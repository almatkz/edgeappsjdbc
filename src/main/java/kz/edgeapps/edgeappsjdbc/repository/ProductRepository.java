package kz.edgeapps.edgeappsjdbc.repository;

import kz.edgeapps.edgeappsjdbc.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Product findOne(Integer id);

    List<Product> findAll();

    void save(Product product);

    Long saveAndReturnId(Product product);

    void update(Product product);

    Boolean delete(Integer id);

}
