package kz.edgeapps.edgeappsjdbc.repository;

public class QueryObject {
    public static final String FIND_ONE =
            "select id, receive_date, expiration_date, serial_num, isDefective, product_id, "
                    + "additional_info from product_objects where id = ?";

    public static final String FIND_ALL =
            "select id, receive_date, expiration_date, serial_num, isDefective, product_id, "
                    + "additional_info from product_objects";

    public static final String SAVE =
            "insert into product_objects(id, expiration_date, serial_num, "
                    + "isDefective, product_id, additional_info) "
                    + "values (?, ?, ?, ?, ?, ?)";

    public static final String SAVE_AND_RETURN_ID =
            "insert into product_objects(id, expiration_date, serial_num, "
                    + "isDefective, product_id, additional_info) "
                    + "values (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE =
            "update product_objects set "
                    + "expiration_date = ?, serial_num = ?, isDefective = ?, "
                    + "product_id = ?, additional_info = ? " + "where id = ?";
    public static final String DELETE =
            "delete from product_objects where id = ?";
}
