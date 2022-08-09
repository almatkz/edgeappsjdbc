package kz.edgeapps.edgeappsjdbc.repository;

import kz.edgeapps.edgeappsjdbc.model.Product;

import java.util.List;

public interface ProductRepository {
    public Product findOne(Long id);

    public List<Product> findAll();

    public void save(Product product);

    public Long saveAndReturnId(Product product);

    public void update(Product product);

    public Boolean delete(Long id);

}
