package com.brixton.sodimac.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class Person extends Audit {
    @Column(length = 50)
    private String address;
    @Column(length = 15)
    private String phone;
    @Column(length = 30)
    private String email;

}
