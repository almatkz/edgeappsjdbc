package kz.edgeapps.edgeappsjdbc.repository;

import kz.edgeapps.edgeappsjdbc.model.Product;
import kz.edgeapps.edgeappsjdbc.model.ProductObject;

import java.util.List;
import java.util.UUID;

public interface ProductObjectRepository {

    ProductObject findOne(UUID id);

    List<ProductObject> findAll();

    void save(ProductObject productObject);

    String saveAndReturnId(ProductObject productObject);

    void update(ProductObject productObject);

    Boolean delete(UUID id);
}
