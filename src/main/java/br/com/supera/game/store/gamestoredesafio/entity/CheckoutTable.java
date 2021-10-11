package br.com.supera.game.store.gamestoredesafio.entity;

import br.com.supera.game.store.gamestoredesafio.model.CheckoutModel;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "chekout_tb")
public class CheckoutTable {

    @Id
    @Column(name = "checkout_id")
    private String checkoutId;

    @ManyToMany
    @JoinTable(
            name = "checkout_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "checkout_id")
    )

    private List<ProductTable> products;

    public CheckoutTable(String checkoutId, List<ProductTable> products) {
        this.checkoutId = checkoutId;
        this.products = products;
    }

    public CheckoutTable() {
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

    public List<ProductTable> getProducts() {
        return products;
    }

    public void setProducts(List<ProductTable> products) {
        this.products = products;
    }

    public CheckoutModel toModel() {
        return new CheckoutModel(
                this.checkoutId,
                this.products.stream().map(ProductTable::toModel).collect(Collectors.toList())
        );
    }
}