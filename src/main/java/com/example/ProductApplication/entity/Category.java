package com.example.ProductApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Entity mapping

    //here a category can have multiple products
    // So this will be OneToMany relation
    //so here will add list of product

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    private List<Product> products=new ArrayList<>();
}
