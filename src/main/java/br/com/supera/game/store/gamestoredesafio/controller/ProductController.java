package br.com.supera.game.store.gamestoredesafio.controller;

import br.com.supera.game.store.gamestoredesafio.model.ProductModel;
import br.com.supera.game.store.gamestoredesafio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping()
    public ProductModel registerProduct(@RequestBody ProductModel product) {
        return service.registerProduct(product);
    }
    ;
    @PostMapping("/save-all")
    public void registerAllProducts(@RequestBody List<ProductModel> products) {
        service.registerAllProducts(products);
    }

    @GetMapping("/find")
    public List<ProductModel> listProducts(@RequestParam("sort") String sort) {
        return service.listProducts(sort);
    }
}
