package com.example.Java_Diplom.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Producttype")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @Column(name = "name")
    String name;

@OneToMany(fetch = FetchType.EAGER,mappedBy = "producttype")
List<Product> product;


    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
