package kz.edgeapps.edgeappsjdbc.model;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Integer price;

    public Product() {
    }

    public Product(Integer id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
