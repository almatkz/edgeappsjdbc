package kz.edgeapps.edgeappsjdbc.controller;

import kz.edgeapps.edgeappsjdbc.exception.InternalServerError;
import kz.edgeapps.edgeappsjdbc.model.ProductObject;
import kz.edgeapps.edgeappsjdbc.repository.ProductObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product_objects")
public class ProductObjectController {

    @Autowired
    ProductObjectRepository productObjectRepository;

    //Create product
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProductObject productObject) {

        try {
            var productObjectId = productObjectRepository.saveAndReturnId(productObject);

            return new ResponseEntity<String>
                    ("ProductObject successfully created , Id ="
                            + productObjectId, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    //Get all products
    @GetMapping
    public ResponseEntity<List<ProductObject>> getAll() {

        try {
            return new ResponseEntity<List<ProductObject>>
                    (productObjectRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    //Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductObject> getById
    (@PathVariable("id") UUID id) {

        return new ResponseEntity<>
                (productObjectRepository.findOne(id), HttpStatus.OK);

    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct
    (@PathVariable("id") UUID id) {

        productObjectRepository.delete(productObjectRepository.findOne(id).getId());

        return new ResponseEntity<>
                ("ProductObject removed successfully", HttpStatus.OK);

    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct
    (@PathVariable("id") UUID id, @RequestBody ProductObject productObject) {

        var _productObject = productObjectRepository.findOne(id);
        var _upProductObject = new ProductObject(_productObject.getId(),
                productObject.getExpirationDate().toString(),
                productObject.getSerialNum(),
                productObject.getIsDefective(),
                productObject.getProductId(),
                productObject.getAdditionalInfo());

        productObjectRepository.update(_upProductObject);
        return new ResponseEntity<>
                ("Updated successfully", HttpStatus.OK);
    }
}