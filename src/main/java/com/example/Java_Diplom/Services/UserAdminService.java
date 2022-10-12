package com.example.Java_Diplom.Services;


import com.example.Java_Diplom.Repositories.UserRepository;
import com.example.Java_Diplom.models.Role;
import com.example.Java_Diplom.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserAdminService {

    private final UserRepository userRepository;

    @Autowired
    public UserAdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    public Users getOne(int id) {
        Optional<Users> users = userRepository.findById(id);
        return users.orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    @Transactional
    public void updateOneRoles(int id, List<Role> role) {
        Users user = getOne(id);
        user.setRoleList(role);
        user.setNeedToUpdate(true);
        userRepository.save(user);
        //  user.needToUpdate();
    }

 //   @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
public void deleteOneById(int id){
        userRepository.deleteById(id);
    }


    public Users getUserByUserName(String name){
        return userRepository.findByUsername(name);
    }

    //использую для обновления ролей по ссылкам

}
