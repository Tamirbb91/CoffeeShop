package edu.mum.coffee;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderRestfulTest {
    public final String SERVER_URI = "http://localhost:8080/rest/order";
    public final String SERVER_URI_PRODUCT = "http://localhost:8080/rest/product";
    public final String SERVER_URI_PERSON = "http://localhost:8080/rest/person";

    @Test
    public void a_updateOrder(){
        RestTemplate restTemplate = new RestTemplate();
        long orderId = 27;
        Order order = restTemplate.getForObject(SERVER_URI + "/findById/" + orderId, Order.class);

        System.out.println("Before update");
        if(order == null){
            System.out.println("Product not found");
            return;
        } else {
            PrintOrder(order);
        }

        order.setOrderDate(new Date(-2));

        order = restTemplate.postForObject(SERVER_URI + "/saveOrder", order, Order.class);

        PrintOrder(order);
    }

    @Test
    public void b_saveOrder(){
        RestTemplate restTemplate = new RestTemplate();

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setPerson(getPerson(1));

        order = restTemplate.postForObject(SERVER_URI + "/saveOrder", order, Order.class);

        PrintOrder(order);
    }


    @Test
    public void c_allOrders(){
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap> ordersMap = restTemplate.getForObject(SERVER_URI + "/allOrders", List.class);

        System.out.println("Total " + ordersMap.size() + " orders");
        for(LinkedHashMap map : ordersMap) {
            System.out.println(map.get("id").toString());
            System.out.println("------------------------");
        }
    }

    @Test
    public void d_findById(){
        RestTemplate restTemplate = new RestTemplate();
        long orderId = 28;
        Order order = restTemplate.getForObject(SERVER_URI + "/findById/" + orderId, Order.class);

        if(order == null){
            System.out.println("Order not found");
        } else {
            PrintOrder(order);
        }

    }

    @Test
    public void e_findByProduct(){
        RestTemplate restTemplate = new RestTemplate();

        Product product = getProduct(122);

        List<LinkedHashMap> ordersMap = restTemplate.postForObject(SERVER_URI + "/findByProduct/", product, List.class);

        System.out.println("Total " + ordersMap.size() + " orders found");
        for(LinkedHashMap map : ordersMap) {
            System.out.println(map.get("id").toString());

            System.out.println("------------------------");
        }
    }

    @Test
    public void f_findByDate(){
        RestTemplate restTemplate = new RestTemplate();

        String minDate = "2018-03-10";
        String maxDate = "2018-03-12";
        List<LinkedHashMap> ordersMap = restTemplate.getForObject(SERVER_URI + "/findByDate/" + minDate + "/" + maxDate, List.class);

        System.out.println("Total " + ordersMap.size() + " orders found");
        for(LinkedHashMap map : ordersMap) {
            System.out.println(map.get("id").toString());

            System.out.println("------------------------");
        }
    }

    @Test
    public void g_removeOrder(){
        RestTemplate restTemplate = new RestTemplate();
        long orderId = 29;
        Order order = restTemplate.getForObject(SERVER_URI + "/findById/" + orderId, Order.class);

        System.out.println("Before remove");
        if(order == null){
            System.out.println("Order not found");
            return;
        } else {
            PrintOrder(order);
        }

        restTemplate.postForEntity(SERVER_URI + "/removeOrder", order, Order.class);

        order = restTemplate.getForObject(SERVER_URI + "/findById/" + orderId, Order.class);

        Assert.assertTrue(order == null);

    }

    public Person getPerson(long personId){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(SERVER_URI_PERSON + "/findById/" + personId, Person.class);
    }

    public Product getProduct(long productId){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(SERVER_URI_PRODUCT + "/findById/" + productId, Product.class);
    }

    public void PrintOrder(Order order){
        System.out.println("Order person : " + order.getPerson().getFirstName());
        if(order.getOrderLines().size() > 0) {
            System.out.println("Order product : " + order.getOrderLines().get(0).getProduct().getProductName());
            System.out.println("Quantity : " + order.getQuantity());
            System.out.println("Order total amount : " + order.getTotalAmount());
        }
        System.out.println("Order date : " + order.getOrderDate());

    }

}
