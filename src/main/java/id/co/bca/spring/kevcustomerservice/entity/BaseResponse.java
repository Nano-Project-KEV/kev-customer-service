package id.co.bca.spring.kevcustomerservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponse<T> {
    @JsonProperty("payload")
    private T payload;

    private String errorCode;

    private String errorMessage;
}
