package kz.edgeapps.edgeappsjdbc.repository;

public class Query {
    public static final String FIND_ONE =
            "select id, name, description,"
                    + " price from product_positions where id = ?";

    public static final String FIND_ALL =
            "select id, name, description,"
                    + " price from product_positions";

    public static final String SAVE =
            "insert into product_positions(name, "
                    + "description, price) "
                    + "values (?, ?, ?)";

    public static final String SAVE_AND_RETURN_ID =
            "insert into product_positions"
                    + "(name, description, price) "
                    + "values (?, ?, ?)";
    public static final String UPDATE =
            "update product_positions set "
                    + "name = ?, description = ?,"
                    + " price = ? " + "where id = ?";
    public static final String DELETE =
            "delete from product_positions where id = ?";
}
