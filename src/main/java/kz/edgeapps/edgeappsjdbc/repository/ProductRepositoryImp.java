package kz.edgeapps.edgeappsjdbc.repository;

import kz.edgeapps.edgeappsjdbc.exception.InternalServerError;
import kz.edgeapps.edgeappsjdbc.exception.ProductNotFound;
import kz.edgeapps.edgeappsjdbc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.Objects;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product findOne(Long id) {
        var sqlQuery = Query.FIND_ONE;

        try {

            return jdbcTemplate.
                    queryForObject(sqlQuery,
                            this::mapRowToProduct, id);

        } catch (EmptyResultDataAccessException ex) {
            throw new ProductNotFound("Invalid Product Id");
        } catch (Exception e) {
            throw new InternalServerError
                    ("Internal Server Error");
        }

    }

    @Override
    public List<Product> findAll() {

        var sqlQuery = Query.FIND_ALL;

        return jdbcTemplate.
                query(sqlQuery, this::mapRowToProduct);
    }

    @Override
    public void save(Product product) {

        var sqlQuery = Query.SAVE;

        jdbcTemplate.update(sqlQuery,
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }

    @Override
    public Long saveAndReturnId(Product product) {

        var sqlQuery = Query.SAVE_AND_RETURN_ID;

        var keyHolder = new GeneratedKeyHolder();

//        jdbcTemplate.update(connection -> {
//            PreparedStatement stmt = connection.
//                    prepareStatement(sqlQuery,
//                            new String[] { "id" });
//
//            stmt.setString(1, product.getName());
//            stmt.setString(2, product.getDescription());
//            stmt.setBigDecimal(3, product.getPrice());
//
//            return stmt;
//
//        }, keyHolder);

        jdbcTemplate.update("insert into product_positions"
                        + "(name, description, price) "
                        + "values (?, ?, ?) ",
                product.getName(),
                product.getDescription(),
                product.getPrice());

        return 3L;
    }

    @Override
    public void update(Product product) {

        var sqlQuery = Query.UPDATE;

        jdbcTemplate.update(sqlQuery,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getId());
    }

    @Override
    public Boolean delete(Long id) {

        var sqlQuery = Query.DELETE;

        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

    private Product mapRowToProduct(ResultSet resultSet, int rowNum)
            throws SQLException {

        var product = new Product(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBigDecimal("price"));

        return product;
    }

}
