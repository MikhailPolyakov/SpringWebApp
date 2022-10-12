package com.example.Java_Diplom.Repositories;

import com.example.Java_Diplom.models.Product;
import com.example.Java_Diplom.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {

}
