package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    PersonService personService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping("userDetails")
    public String userDetailsPage(Model model, Principal principal){
        String email = principal.getName();
        List<Person> people = personService.findByEmail(email);
        if(people.size() > 0){
            Person person = people.get(0);
            model.addAttribute("person", person);
        }
        return "userDetails";
    }

    @PostMapping("userDetails")
    public String userDetails(@Valid Person person, Principal principal){
        List<Person> people = personService.findByEmail(principal.getName());
        if(people.size() > 0){
            Person person1 = people.get(0);
            person1.setFirstName(person.getFirstName());
            person1.setLastName(person.getLastName());
            person1.setPhone(person.getPhone());
            person1.setAddress(person.getAddress());
            personService.savePerson(person1);
        }
        return "redirect:/user/products";
    }

    @PostMapping("addOrder/{id}")
    public String addOrder(@PathVariable int id, Principal principal, HttpSession session){
        if(principal == null){
            return "redirects:/login";
        }

        Product product = productService.getProduct(id);

        if(session.getAttribute("order") == null) {
            Person person = personService.findByEmail(principal.getName()).get(0);
            Order order = new Order();
            order.setPerson(person);
            session.setAttribute("order", order);
        }

        Order order = (Order) session.getAttribute("order");
        for(Orderline orderline : order.getOrderLines()){
            if(orderline.getProduct().getId() == id){
                orderline.setQuantity(orderline.getQuantity()+1);
                return "redirect:/products";
            }
        }

        Orderline orderline = new Orderline();
        orderline.setOrder(order);
        orderline.setQuantity(1);
        orderline.setProduct(product);
        order.addOrderLine(orderline);

        return "redirect:/products";
    }

    @PostMapping("removeOrder/{id}")
    public String removeFromOrder(@PathVariable int id, Principal principal, HttpSession session){
        if(principal == null){
            return "redirects:/login";
        }

        if(session.getAttribute("order") == null) {
            return "redirect:/products";
        }

        Order order = (Order) session.getAttribute("order");
        List<Orderline> orderlines = order.getOrderLines();
        for(Orderline orderline : orderlines){
            if(orderline.getProduct().getId() == id){
                if(orderline.getQuantity() == 1){
                    order.removeOrderLine(orderline);
                } else {
                    orderline.setQuantity(orderline.getQuantity()-1);
                }
                break;
            }
        }
        return "redirect:/products";
    }

    @PostMapping("placeOrder")
    public String placeOrder(HttpSession session, RedirectAttributes attributes){
        Order order = (Order) session.getAttribute("order");
        orderService.save(order);
        session.removeAttribute("order");
        attributes.addFlashAttribute("message", "Your order is successully placed and your order will be ready soon");
        return "redirect:/products";
    }
}
