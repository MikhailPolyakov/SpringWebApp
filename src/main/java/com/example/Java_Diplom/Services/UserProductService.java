package com.example.Java_Diplom.Services;


import com.example.Java_Diplom.Repositories.ProductRepository;
import com.example.Java_Diplom.Repositories.UserRepository;
import com.example.Java_Diplom.models.Product;
import com.example.Java_Diplom.models.Role;
import com.example.Java_Diplom.models.Users;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public UserProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void JustSave(Users user){
        userRepository.save(user);
    }

    @Transactional
    public List<Role> getRolesByUserName(String name) {
        List<Role> roleList= userRepository.findByUsername(name).getRoleList();
        for (Role role:roleList){}
        return roleList;
    }

    public Optional<Users> getOneByEmail(String email){
        return userRepository.findByEmail(email);
    }

public List<Product> getAllProducts(){
return productRepository.findAll();
}



}
