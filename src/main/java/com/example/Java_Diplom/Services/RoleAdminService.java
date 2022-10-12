package com.example.Java_Diplom.Services;

import com.example.Java_Diplom.Repositories.RoleRepository;
import com.example.Java_Diplom.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleAdminService {


    private final RoleRepository roleRepository;
@Autowired
    public RoleAdminService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    public List<Role> getAll(){
    return roleRepository.findAll();
    }

    //@Transactional
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")

}
