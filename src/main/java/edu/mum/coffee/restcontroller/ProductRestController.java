package edu.mum.coffee.restcontroller;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/product/")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "saveProduct")
    public @ResponseBody Product save(@RequestBody @Valid Product product){
        return productService.save(product);
    }

    @PostMapping(path = "removeProduct")
    public void delete(@RequestBody @Valid Product product){
        productService.delete(product);
    }

    @GetMapping(path = "allProducts")
    public @ResponseBody
    List<Product> allProducts(){
        return productService.getAllProduct();
    }

    @GetMapping(path = "findById/{id}")
    public @ResponseBody Product findById(@PathVariable int id){
        return productService.getProduct(id);
    }

    @GetMapping(path = "findByTextSearch/{criteria}")
    public @ResponseBody List<Product> findByTextSearch(@PathVariable String criteria){
        return productService.findByTextSearch(criteria);
    }

    @GetMapping(path = "findByPrice/{minPrice}/{maxPrice}")
    public @ResponseBody List<Product> findByPrice(@PathVariable double minPrice, @PathVariable double maxPrice){
        return productService.findByPrice(minPrice, maxPrice);
    }

    @GetMapping(path = "findByProductType/{productType}")
    public @ResponseBody List<Product> findByProductType(@PathVariable ProductType productType){
        return productService.findByProductType(productType);
    }
}
