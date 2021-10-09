package br.com.supera.game.store.gamestoredesafio.model;



import br.com.supera.game.store.gamestoredesafio.entity.ProductTable;

import java.math.BigDecimal;

public class ProductModel {

    private long id;
    private String name;
    private BigDecimal price;
    private short score;
    private String image;

    public ProductModel(
            long id,
            String name,
            BigDecimal price,
            short score,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.score = score;
        this.image = image;
    }
    public ProductModel(
            String name,
            BigDecimal price,
            short score,
            String image
    ) {
        this.name = name;
        this.price = price;
        this.score = score;
        this.image = image;

    }
    public ProductModel(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductTable toTable(){
        return new ProductTable(
                this.id,
                this.name,
                this.price,
                this.score,
                this.image
        );
    }
}
