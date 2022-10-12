package com.example.Java_Diplom.Services;


import com.example.Java_Diplom.Repositories.RoleRepository;
import com.example.Java_Diplom.Repositories.UserRepository;
import com.example.Java_Diplom.models.Role;
import com.example.Java_Diplom.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserRegistration {
private final EmailSenderService emailSenderService;
private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
private final UserRepository userRepository;
@Autowired
    public UserRegistration(EmailSenderService emailSenderService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.emailSenderService = emailSenderService;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Transactional
    public void Registration(Users user) throws MessagingException, UnsupportedEncodingException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role=roleRepository.findByName("ROLE_USER") ;
      //  role.setPrivileges(Arrays.asList(new Privilege(1,"READ_AUTHORITY")));
        user.setRoleList(Arrays.asList(role));

user.setActivatiCode(UUID.randomUUID().toString());
        Map<String,Object> map=new HashMap<>();
        map.put("code",user.getActivatiCode());
emailSenderService.sendMimeEmail(user.getEmail(),"Заверешение регистрации аккаунта","/mail/activate.html",user.getActivatiCode(),
       map );
        userRepository.save(user);

    }

    public boolean activate(String code){
        Users user=userRepository.findByActivaticode(code);
        if (user==null){
            System.out.println("User пустой");
            return false;
        }
        user.setActivatiCode(null);
        userRepository.save(user);
        return true;
    }


}
