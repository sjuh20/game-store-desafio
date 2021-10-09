package br.com.supera.game.store.gamestoredesafio.model;

import br.com.supera.game.store.gamestoredesafio.entity.CheckoutTable;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutModel {

    private long checkoutId;
    private List<ProductModel> products;

    public CheckoutModel(long checkoutId, List<ProductModel> products) {
        this.checkoutId = checkoutId;
        this.products = products;
    }

    public long getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(long checkoutId) {
        this.checkoutId = checkoutId;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
    public CheckoutTable toTable(){
        return new CheckoutTable(
                this.checkoutId,
                this.products.stream().map(ProductModel::toTable).collect(Collectors.toList())
        );
    }
}