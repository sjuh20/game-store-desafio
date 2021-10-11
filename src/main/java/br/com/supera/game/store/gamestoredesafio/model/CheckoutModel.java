package br.com.supera.game.store.gamestoredesafio.model;

import br.com.supera.game.store.gamestoredesafio.entity.CheckoutTable;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutModel {

    private String checkoutId;
    private BigDecimal freight;
    private BigDecimal total;
    private BigDecimal subTotal;
    private List<ProductModel> products;

    public CheckoutModel(String checkoutId, BigDecimal freight, BigDecimal total, BigDecimal subTotal, List<ProductModel> products) {
        this.checkoutId = checkoutId;
        this.freight = freight;
        this.total = total;
        this.subTotal = subTotal;
        this.products = products;
    }

    public CheckoutModel(String checkoutId, List<ProductModel> products) {
        this.checkoutId = checkoutId;
        this.products = products;
    }

    public CheckoutModel(List<ProductModel> products) {
        this.products = products;
        this.checkoutId = "";
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public CheckoutTable toTable() {
        return new CheckoutTable(
                this.checkoutId,
                this.products.stream().map(ProductModel::toTable).collect(Collectors.toList())
        );
    }
}