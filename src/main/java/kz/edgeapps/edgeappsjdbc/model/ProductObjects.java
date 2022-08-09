package kz.edgeapps.edgeappsjdbc.model;

import lombok.Data;

//Еще никуда не подключено, на будущее
import java.math.BigDecimal;

@Data
public class ProductObjects {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductObjects(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
