package com.dev.backend.devstore.domain.product;

public enum ProductSize {
    P("p"),
    M("m"),
    G("g"),
    GG("gg");

    private String size;

    ProductSize(String size){
        this.size = size;
    }

    public String getSize(){
        return size;
    }
}
