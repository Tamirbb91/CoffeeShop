package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
import edu.mum.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired
    PersonService personService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping("product/{id}")
    public String editProductPage(@PathVariable int id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "productDetail";
    }

    @PostMapping("product/{id}")
    public String editProduct(@PathVariable int id, @Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return "productDetail";
        }
        Product prod = productService.getProduct(id);
        prod.setProductName(product.getProductName());
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setProductType(product.getProductType());
        productService.save(prod);
        return "redirect:/products";
    }

    @GetMapping("product/addProduct")
    public String addProductPage(Model model){
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("product/addProduct")
    public String addProduct(@Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return "addProduct";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("product/delete/{id}")
    public String removeProduct(@PathVariable int id){
        Product product = productService.getProduct(id);
        productService.delete(product);
        return "redirect:/products";
    }

    @GetMapping("users")
    public String allUser(Model model){
        model.addAttribute("users", personService.getUsers());
        return "users";
    }

    @GetMapping("user/addUser")
    public String addUserPage(Model model){
        model.addAttribute("person", new Person());
        return "addUser";
    }

    @PostMapping("user/addUser")
    public String addUser(@Valid Person person, String password, String password2, BindingResult result){
        if(result.hasErrors()){
            return "addUser";
        }

        if(!password.equals(password2)){
            return "addUser";
        }

        if(personService.findByEmail(person.getEmail()).size() > 0){
            return "addUser";
        }

        List<GrantedAuthority> authorities = new ArrayList(){};
        authorities.add(new SimpleGrantedAuthority("USER"));
        UserDetails user = new User(person.getEmail(), password, authorities);
        userService.createUser(user, authorities);
        personService.savePerson(person);

        return "redirect:/admin/users";
    }

    @GetMapping("orders")
    public String allOrder(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }
}
