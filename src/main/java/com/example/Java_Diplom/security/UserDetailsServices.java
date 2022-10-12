package com.example.Java_Diplom.security;


import com.example.Java_Diplom.Repositories.UserRepository;
import com.example.Java_Diplom.models.Role;
import com.example.Java_Diplom.models.Users;
import com.example.Java_Diplom.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    //таким образом даем понять springy securiyty,что этот сервис загружает пользователя по имени пользвоаетля
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userRepository.findByUsername(username);
      //  if (users==null) {
      //      throw new UsernameNotFoundException("User not found tak to" );
     //   }
   //    if(users!=null && users.getActivatiCode()!=null){
    //        System.out.println("Не атиквен епта");
    //      throw new BadCredentialsException("Пользователь не активирован бля");
   //     }

        //вызывается из-за ленивой инициализации геттер Rolelist
        //чтобы не ставить fetchtype eager и не подгуржать каждый раз с человеком его роли
        List<Role> roleList=users.getRoleList();
boolean ifelse=roleList.isEmpty();
        return  new UsersDetails(users,ifelse,roleList);

    }
}
