package com.artur.MegaMarketOpenAPI.core.exception;

public class ShopUnitNotFoundException extends RuntimeException{
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ShopUnitNotFoundException (){
    }

    public ShopUnitNotFoundException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
