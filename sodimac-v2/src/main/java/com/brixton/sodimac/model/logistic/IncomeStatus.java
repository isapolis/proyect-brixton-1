package com.brixton.sodimac.model.logistic;

public enum IncomeStatus {
    RECEIVED(1),
    CANCELED(2);

    private Integer incomeStatus;

    IncomeStatus (Integer incomeStatus){
        this.incomeStatus=incomeStatus;
    }

    public Integer getIncomeStatus(){
        return this.incomeStatus;
    }

}
