package com.example.Java_Diplom.Repositories;

import com.example.Java_Diplom.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);
Users findByActivaticode(String code);
Optional<Users> findByEmail(String email);
}