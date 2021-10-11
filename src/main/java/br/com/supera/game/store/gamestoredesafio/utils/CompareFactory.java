package br.com.supera.game.store.gamestoredesafio.utils;

import java.util.Comparator;

public class CompareFactory {

    public static Comparator getComparator(String sort) {
        switch (sort) {
            case "name":
                return new ProductNameComparator();
            case "score":
                return new ProductScoreComparator();
            case "price":
                return new ProductPriceComparator();
            default:
                return new ProductNameComparator();

        }
    }
}
