package kz.edgeapps.edgeappsjdbc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ProductObject {

    UUID id = UUID.randomUUID();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp receiveDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp expirationDate;
    private Integer serialNum;
    private Boolean isDefective;
    private Integer productId;
    private String additionalInfo;

    public ProductObject() {
    }

    public ProductObject(String expirationDate, Integer serialNum, Boolean isDefective, Integer productId, String additionalInfo) {

        this.expirationDate = Timestamp.valueOf(expirationDate);
        this.serialNum = serialNum;
        this.isDefective = isDefective;
        this.productId = productId;
        this.additionalInfo = additionalInfo;
    }

    public ProductObject(UUID id, String receiveDate, String expirationDate, Integer serialNum, Boolean isDefective, Integer productId, String additionalInfo) {
        this.id = id;
        this.receiveDate = Timestamp.valueOf(receiveDate);
        this.expirationDate = Timestamp.valueOf(expirationDate);
        this.serialNum = serialNum;
        this.isDefective = isDefective;
        this.productId = productId;
        this.additionalInfo = additionalInfo;
    }

    public ProductObject(UUID id, String expirationDate, Integer serialNum, Boolean isDefective, Integer productId, String additionalInfo) {
        this.id = id;
        this.expirationDate = Timestamp.valueOf(expirationDate);
        this.serialNum = serialNum;
        this.isDefective = isDefective;
        this.productId = productId;
        this.additionalInfo = additionalInfo;
    }
}
