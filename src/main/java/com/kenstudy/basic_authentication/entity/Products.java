package com.kenstudy.basic_authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private int id;
    private String name;
    private int price;
    private List<String> manufacturer;


}
