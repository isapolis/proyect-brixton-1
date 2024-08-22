package com.brixton.sodimac.model.audit;

public enum TypeStatusForAudit {
    INACTIVE(1),
    ACTIVE(2);


    private Integer status;

    TypeStatusForAudit(Integer status) {
        this.status = status;
    }

    Integer getStatus() {
        return this.status;
    }
}
