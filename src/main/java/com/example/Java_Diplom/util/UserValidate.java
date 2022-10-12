package com.example.Java_Diplom.util;

import com.example.Java_Diplom.Services.UserProductService;

import com.example.Java_Diplom.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidate implements Validator {


    private final UserProductService userService;

    @Autowired
    public UserValidate(UserProductService userService) {


        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users person=(Users )target;
        try {
            userService.getOneByEmail(person.getUsername()).isPresent();
        }catch (UsernameNotFoundException ignored){
            return;
        }
      //  if( userRepository.findByActivaticode()) {
            errors.rejectValue("username", "такое имя пользователя уже существует");
      //  }
    }
}
