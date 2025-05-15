package com.example.ProductApplication.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NaturalId;

@Entity
// here we can give the table name as @Table (name="Product")
//By default class name set to table name if not specified
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    //Entity Relation mapping
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private Category category;

    //here "category" is mapped with product there in Category entity
    //in the entity mapping
}
