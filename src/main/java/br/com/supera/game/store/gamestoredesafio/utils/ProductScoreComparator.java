package br.com.supera.game.store.gamestoredesafio.utils;

import br.com.supera.game.store.gamestoredesafio.model.ProductModel;

import java.util.Comparator;

public class ProductScoreComparator implements Comparator<ProductModel> {
    @Override
    public int compare(ProductModel o1, ProductModel o2) {
        return o1.getScore() - o2.getScore();
    }
}
