package org.caja.ideal.shoppingcart.utils;

public record Message() {
    public static final String NOT_FOUND_PRODUCT = "product not found";
    public static final String PRODUCT_ALREADY_EXISTS = "there is already a product with this ID" ;

   public static final String SUCCESSFULLY_REMOVED = "product removed successfully";
   public static final String USERNAME_ALREADY_EXISTS = "there is already a user with this";
   public static final String JWT_EXPIRED = "static token expired";

}
