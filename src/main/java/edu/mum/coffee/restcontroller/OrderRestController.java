package edu.mum.coffee.restcontroller;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("rest/order/")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "saveOrder")
    public @ResponseBody Order save(@RequestBody @Valid Order order){
        return orderService.save(order);
    }

    @PostMapping(path = "removeOrder")
    public void delete(@RequestBody Order order){
        orderService.delete(order);
    }

    @GetMapping(path = "allOrders")
    public @ResponseBody List<Order> allOrders(){
        List<Order> orders = orderService.findAll();
        return orders;
    }

    @GetMapping(path = "findById/{id}")
    public @ResponseBody Order findById(@PathVariable int id){
        return orderService.findById(id);
    }

    @PostMapping(path = "findByProduct")
    public @ResponseBody List<Order> findByProduct(@RequestBody @Valid Product product){
        return orderService.findByProduct(product);
    }

    @GetMapping(path = "findByDate/{minDate}/{maxDate}")
    public @ResponseBody List<Order> findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDate,
                                                @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDate){
        return orderService.findByDate(minDate, maxDate);
    }
}
