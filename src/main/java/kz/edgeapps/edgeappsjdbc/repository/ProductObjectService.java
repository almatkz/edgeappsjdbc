package kz.edgeapps.edgeappsjdbc.repository;

import kz.edgeapps.edgeappsjdbc.exception.InternalServerError;
import kz.edgeapps.edgeappsjdbc.exception.ProductObjectNotFound;
import kz.edgeapps.edgeappsjdbc.model.ProductObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductObjectService implements ProductObjectRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductObjectService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductObject findOne(UUID id) {
        var sqlQuery = QueryObject.FIND_ONE;

        try {
            return jdbcTemplate.
                    queryForObject(sqlQuery,
                            this::mapRowToProductObject, id);

        } catch (EmptyResultDataAccessException ex) {
            throw new ProductObjectNotFound("Invalid ProductObject Id");
        } catch (Exception e) {
            throw new InternalServerError
                    ("Internal Server Error");
        }

    }

    @Override
    public List<ProductObject> findAll() {

        var sqlQuery = QueryObject.FIND_ALL;

        return jdbcTemplate.
                query(sqlQuery, this::mapRowToProductObject);
    }

    @Override
    public void save(ProductObject productObject) {

        var sqlQuery = QueryObject.SAVE;

        jdbcTemplate.update(sqlQuery,
                productObject.getExpirationDate(),
                productObject.getSerialNum(),
                productObject.getIsDefective(),
                productObject.getProductId(),
                productObject.getAdditionalInfo());
    }

    @Override
    public String saveAndReturnId(ProductObject productObject) {

        var sqlQuery = QueryObject.SAVE_AND_RETURN_ID;

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.
                    prepareStatement(sqlQuery,
                            new String[] { "id" });

            stmt.setObject(1, productObject.getId());
            stmt.setTimestamp(2, productObject.getExpirationDate());
            stmt.setInt(3, productObject.getSerialNum());
            stmt.setBoolean(4, productObject.getIsDefective());
            stmt.setInt(5, productObject.getProductId());
            stmt.setString(6, productObject.getAdditionalInfo());

            return stmt;

        }, keyHolder);

        return  String.valueOf(keyHolder.getKeyList().get(0).get("id"));
    }

    @Override
    public void update(ProductObject productObject) {

        var sqlQuery = QueryObject.UPDATE;

        jdbcTemplate.update(sqlQuery,
                productObject.getExpirationDate(),
                productObject.getSerialNum(),
                productObject.getIsDefective(),
                productObject.getProductId(),
                productObject.getAdditionalInfo(),
                productObject.getId());
    }

    @Override
    public Boolean delete(UUID id) {

        var sqlQuery = QueryObject.DELETE;

        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

    private ProductObject mapRowToProductObject(ResultSet resultSet, int rowNum)
            throws SQLException {
        var productObject = new ProductObject(resultSet.getObject("id", java.util.UUID.class),
                resultSet.getString("receive_date"),
                resultSet.getString("expiration_date"),
                resultSet.getInt("serial_num"),
                resultSet.getBoolean("isDefective"),
                resultSet.getInt("product_id"),
                resultSet.getString("additional_info"));

        return productObject;
    }

}
