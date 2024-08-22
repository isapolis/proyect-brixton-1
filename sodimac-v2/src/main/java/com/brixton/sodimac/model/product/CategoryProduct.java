package com.brixton.sodimac.model.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class CategoryProduct {
    private int id;
    private TypeCategory name;
}
