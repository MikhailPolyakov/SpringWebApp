package com.example.Java_Diplom.security;

import com.example.Java_Diplom.Services.UserAdminService;
import com.example.Java_Diplom.Services.UserProductService;
import com.example.Java_Diplom.models.Role;
import com.example.Java_Diplom.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends GenericFilterBean {
    @Autowired
    UserAdminService userAdminService;

    @Autowired
    UserProductService userService;
    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long timestart=System.currentTimeMillis();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getName().equals("anonymousUser")) {
            System.out.println("Время до первого if условия " + (System.currentTimeMillis()-timestart));
            Users user = userAdminService.getUserByUserName(auth.getName());

            //  if(user.isNeedToUpdate()){};
            //    System.out.println("время с выносом первого If условия" + (System.currentTimeMillis()-timestart));
            if (user.isNeedToUpdate()) {
                List<Role> roleList = user.getRoleList();
                if (roleList == null) {
                    Authentication authnull = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), null);
                    SecurityContextHolder.getContext().setAuthentication(authnull);
                } else {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    for (Role role : roleList) {
                        authorities.add(new SimpleGrantedAuthority(role.getName()));
                        role.getPrivileges().stream()
                                .map(p -> new SimpleGrantedAuthority(p.getPrivilege_name()))
                                .forEach(authorities::add);

                    }
                    Authentication newauth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
                    SecurityContextHolder.getContext().setAuthentication(newauth);

                }
                user.setNeedToUpdate(false);
                userService.JustSave(user);
            }
        }
        System.out.println("время после всего выполненеия фильтра "+ (System.currentTimeMillis()-timestart));
        chain.doFilter(request, response);
    }
}

