package edu.mum.coffee;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRestfulTest {

    public final String SERVER_URI = "http://localhost:8080/rest/product";

    @Test
    public void a_updateProduct(){
        RestTemplate restTemplate = new RestTemplate();
        long productId = 121;
        Product product = restTemplate.getForObject(SERVER_URI + "/findById/" + productId, Product.class);

        System.out.println("Before update");
        if(product == null){
            System.out.println("Product not found");
            return;
        } else {
            PrintProduct(product);
        }

        product.setPrice(8.5);

        product = restTemplate.postForObject(SERVER_URI + "/saveProduct", product, Product.class);

        PrintProduct(product);
    }

    @Test
    public void b_saveProduct(){
        RestTemplate restTemplate = new RestTemplate();

        Product product = new Product();
        product.setProductName("Americano");
        product.setDescription("This is americano.");
        product.setPrice(6);
        product.setProductType(ProductType.DINNER);

        product = restTemplate.postForObject(SERVER_URI + "/saveProduct", product, Product.class);

        PrintProduct(product);
    }


    @Test
    public void c_allProducts(){
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap> productsMap = restTemplate.getForObject(SERVER_URI + "/allProducts", List.class);

        System.out.println("Total " + productsMap.size() + " products");
        for(LinkedHashMap map : productsMap) {
            PrintProduct(parseProduct(map));

            System.out.println("------------------------");
        }
    }

    @Test
    public void d_findById(){
        RestTemplate restTemplate = new RestTemplate();
        long productId = 121;
        Product product = restTemplate.getForObject(SERVER_URI + "/findById/" + productId, Product.class);

        if(product == null){
            System.out.println("Product not found");
        } else {
            PrintProduct(product);
        }

    }

    @Test
    public void e_findByTextSearch(){
        RestTemplate restTemplate = new RestTemplate();

        String criteria = "this";

        List<LinkedHashMap> productsMap = restTemplate.getForObject(SERVER_URI + "/findByTextSearch/" + criteria, List.class);

        System.out.println("Total " + productsMap.size() + " products found");
        for(LinkedHashMap map : productsMap) {
            PrintProduct(parseProduct(map));

            System.out.println("------------------------");
        }
    }

    @Test
    public void f_findByPrice(){
        RestTemplate restTemplate = new RestTemplate();

        double minPrice = 4;
        double maxPrice = 6;
        List<LinkedHashMap> productsMap = restTemplate.getForObject(SERVER_URI + "/findByPrice/" + minPrice + "/" + maxPrice, List.class);

        System.out.println("Total " + productsMap.size() + " products found");
        for(LinkedHashMap map : productsMap) {
            PrintProduct(parseProduct(map));

            System.out.println("------------------------");
        }
    }

    @Test
    public void g_findByProductType(){
        RestTemplate restTemplate = new RestTemplate();

        ProductType productType = ProductType.BREAKFAST;

        List<LinkedHashMap> productsMap = restTemplate.getForObject(SERVER_URI + "/findByProductType/" + productType, List.class);

        System.out.println("Total " + productsMap.size() + " products found");
        for(LinkedHashMap map : productsMap) {
            PrintProduct(parseProduct(map));

            System.out.println("------------------------");
        }
    }

    @Test
    public void h_removeProduct(){
        RestTemplate restTemplate = new RestTemplate();
        long productId = 123;
        Product product = restTemplate.getForObject(SERVER_URI + "/findById/" + productId, Product.class);

        System.out.println("Before remove");
        if(product == null){
            System.out.println("Product not found");
            return;
        } else {
            PrintProduct(product);
        }

        restTemplate.postForEntity(SERVER_URI + "/removeProduct", product, Product.class);

        product = restTemplate.getForObject(SERVER_URI + "/findById/" + productId, Product.class);

        Assert.assertTrue(product == null);

    }


    public void PrintProduct(Product product){
        System.out.println("Product name : " + product.getProductName());
        System.out.println("Description : " + product.getDescription());
        System.out.println("Price : " + product.getPrice());
        System.out.println("Product ty0pe : " + product.getProductType());
    }

    public Product parseProduct(LinkedHashMap map){
        Product product = new Product();

        product.setProductName(map.get("productName").toString());
        product.setDescription(map.get("description").toString());
        product.setPrice((Double) map.get("price"));
        product.setProductType(ProductType.valueOf(map.get("productType").toString()));

        return product;
    }
}
