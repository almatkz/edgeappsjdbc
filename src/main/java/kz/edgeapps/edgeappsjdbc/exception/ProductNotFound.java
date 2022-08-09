package kz.edgeapps.edgeappsjdbc.exception;

public class ProductNotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public ProductNotFound(String msg) {
        super(msg);
    }
}
