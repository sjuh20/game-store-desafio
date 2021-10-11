package br.com.supera.game.store.gamestoredesafio.controller;

import br.com.supera.game.store.gamestoredesafio.model.CheckoutModel;
import br.com.supera.game.store.gamestoredesafio.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService service;

    public CheckoutController(CheckoutService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public CheckoutModel createCheckout(@RequestBody List<Long> ids) {
        return service.createCheckout(ids);
    }

    @PostMapping("/add-product")
    public CheckoutModel addProduct(
            @RequestParam("checkout-id") long checkoutId,
            @RequestParam("product-id") long productId,
            @RequestParam("sort") String sort) {

        return service.addProduct(checkoutId, productId, sort);

    }

    @PostMapping("/remove-product")
    public CheckoutModel removeProduct(
            @RequestParam("checkout-id") long checkoutId,
            @RequestParam("product-id") long productId,
            @RequestParam("sort") String sort) {

        return service.removeProduct(checkoutId, productId, sort);
    }
}
