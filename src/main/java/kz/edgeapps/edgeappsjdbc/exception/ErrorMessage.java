package kz.edgeapps.edgeappsjdbc.exception;

import lombok.Data;
import java.util.Date;

@Data
public class ErrorMessage {
    Integer statusCode;
    Date timestamp;
    String message;
    String description;
    //TODO: здесь косяк
    public ErrorMessage(Integer i, Date date, String message, String internal_server_error) {
    }
}
