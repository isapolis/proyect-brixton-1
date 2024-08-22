package com.brixton.sodimac.model.employee;

public enum Area {
    LOGISTIC(1),
    SELLING(2),
    SHOPPING(3);

    private Integer workArea;

    Area(Integer workArea){
        this.workArea = workArea;
    }

    public Integer getWorkArea(){
        return this.workArea;
    }
}
