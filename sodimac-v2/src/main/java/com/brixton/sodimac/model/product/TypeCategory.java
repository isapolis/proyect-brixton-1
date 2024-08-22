package com.brixton.sodimac.model.product;

public enum TypeCategory {
    CONSTRUCTION(1),
    HARDWARE(2),
    BATHROOM_AND_KITCHENS(3),
    GARDEN(4),
    VEHICLES(5),
    DECORATION(6);

    private Integer category;

    TypeCategory(Integer category){
        this.category = category;
    }

    public Integer getCategory(){
        return this.category;
    }
}
