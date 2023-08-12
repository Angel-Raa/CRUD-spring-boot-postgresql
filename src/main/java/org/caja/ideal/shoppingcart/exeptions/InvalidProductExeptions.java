package org.caja.ideal.shoppingcart.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidProductExeptions extends RuntimeException{
    private String message;

}
