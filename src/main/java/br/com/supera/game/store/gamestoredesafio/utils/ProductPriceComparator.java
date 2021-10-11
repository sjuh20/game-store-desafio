package br.com.supera.game.store.gamestoredesafio.utils;

import br.com.supera.game.store.gamestoredesafio.model.CheckoutModel;
import br.com.supera.game.store.gamestoredesafio.model.ProductModel;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<ProductModel> {
    @Override
    public int compare(ProductModel o1, ProductModel o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
