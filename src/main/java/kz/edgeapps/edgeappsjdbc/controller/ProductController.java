package kz.edgeapps.edgeappsjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.edgeapps.edgeappsjdbc.exception.InternalServerError;
import kz.edgeapps.edgeappsjdbc.model.Product;
import kz.edgeapps.edgeappsjdbc.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    //Create product
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Product product) {

        try {
            var productId = productRepository.saveAndReturnId(product);

            return new ResponseEntity<String>
                    ("Product successfully created , Id ="
                            + productId, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    //Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        try {
            return new ResponseEntity<List<Product>>
                    (productRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    //Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById
    (@PathVariable("id") Integer id) {

        return new ResponseEntity<>
                (productRepository.findOne(id), HttpStatus.OK);

    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct
    (@PathVariable("id") Integer id) {

        productRepository.delete(productRepository.findOne(id).getId());

        return new ResponseEntity<>
                ("Product removed successfully", HttpStatus.OK);

    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct
    (@PathVariable("id") Integer id, @RequestBody Product product) {

        var _product = productRepository.findOne(id);
        var _upProduct = new Product(_product.getId(),
                product.getName(),product.getDescription(),product.getPrice());

        productRepository.update(_upProduct);
        return new ResponseEntity<>
                ("Updated successfully", HttpStatus.OK);
    }
}