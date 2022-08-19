package kz.edgeapps.edgeappsjdbc.exception;

public class ProductObjectNotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public ProductObjectNotFound(String msg) {
        super(msg);
    }
}
