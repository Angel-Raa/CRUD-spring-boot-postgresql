package org.caja.ideal.shoppingcart.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String message;
    private int code;
    private HttpStatus http;
}
