package com.example.Java_Diplom.Repositories;

import com.example.Java_Diplom.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
