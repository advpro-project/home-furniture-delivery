package com.hoomgroom.delivery.model;

public class Transportation {
    private String type;

    public Transportation(){
    }

    public Transportation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
