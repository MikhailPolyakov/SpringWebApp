package com.example.Java_Diplom.security;

import com.example.Java_Diplom.models.Role;
import com.example.Java_Diplom.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class UsersDetails implements UserDetails {

    private final Users user;
private final boolean ifelse;
private final List<Role> roleList;
    @Autowired
    public UsersDetails(Users user,boolean ifelse,List<Role> roleList) {
        this.ifelse=ifelse;
        this.user = user;
        this.roleList=roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (ifelse) {
            return null;
        }
         else {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
                role.getPrivileges().stream()
                        .map(p -> new SimpleGrantedAuthority(p.getPrivilege_name()))
                        .forEach(authorities::add);
            }
            return authorities;
        }


    }

    //    return Collections.singletonList(new SimpleGrantedAuthority(user.getRoleList().get(0).getPrivileges().get(0).getPrivilege_name()));


    //System.out.println(role);
    // return Collections.singletonList(new SimpleGrantedAuthority(role));


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //просрочен иили нет
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //заблокирован или нет
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    //пароль не просрочен
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    //аккаунт включен или выклыченю
    @Override
    public boolean isEnabled() {
      if(user.getActivatiCode()==null)
        return true;
      else {
          return false;
      }
    }

    public Users getUser() {
        return this.user;
    }

    @Override
    public String toString() {
        return "UsersDetails{" +
                "user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object otherUser) {

        UsersDetails other= (UsersDetails) otherUser;

        if (Objects.equals(other.getUsername(), user.getUsername()) && (other.getUsername()!="anonymousUser") ){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return this.user.getUsername().hashCode() ;


    }
}
