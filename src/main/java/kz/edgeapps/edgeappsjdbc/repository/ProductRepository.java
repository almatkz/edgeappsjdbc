package kz.edgeapps.edgeappsjdbc.repository;

import kz.edgeapps.edgeappsjdbc.model.Product;

import java.util.List;

public interface ProductRepository {
    Product findOne(Long id);

    List<Product> findAll();

    void save(Product product);

    Long saveAndReturnId(Product product);

    void update(Product product);

    Boolean delete(Long id);

}
