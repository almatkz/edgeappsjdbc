package kz.edgeapps.edgeappsjdbc.exception;

import lombok.Data;
import java.util.Date;

@Data
public class ErrorMessage {
    Integer statusCode;
    Date timestamp;
    String message;
    String description;

    public ErrorMessage(Integer statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
