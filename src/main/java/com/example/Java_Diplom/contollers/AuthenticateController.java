package com.example.Java_Diplom.contollers;


import com.example.Java_Diplom.Services.EmailSenderService;
import com.example.Java_Diplom.Services.UserAdminService;
import com.example.Java_Diplom.Services.UserProductService;
import com.example.Java_Diplom.Services.UserRegistration;

import com.example.Java_Diplom.models.Users;
import com.example.Java_Diplom.security.UserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class AuthenticateController {
private final UserRegistration userRegistration;
private final PasswordEncoder bCryptPasswordEncoder;
private final UserAdminService userAdminService;
private final UserDetailsServices userDetailsServices;
private final EmailSenderService emailSenderService;
private  final UserProductService userService;
@Autowired
    public AuthenticateController(UserRegistration userRegistration, PasswordEncoder bCryptPasswordEncoder, UserAdminService userAdminService, UserDetailsServices userDetailsServices, EmailSenderService emailSenderService, UserProductService userService) {
        this.userRegistration = userRegistration;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userAdminService = userAdminService;
    this.userDetailsServices = userDetailsServices;
    this.emailSenderService = emailSenderService;
    this.userService = userService;
}

    @GetMapping("/auth/login")
    public String loginpage(@ModelAttribute("logUser")Users user){
        if (
                SecurityContextHolder.getContext().getAuthentication() != null &&
                        SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                        !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)

        ) {
            return "redirect:/";
        }
        return "authenticate/login";
    }

    @PostMapping("/auth/login")
    public  String handle(Model model,@ModelAttribute("logUser") Users users){
    Users DAOuser=userAdminService.getUserByUserName(users.getUsername());

    if(DAOuser==null || !bCryptPasswordEncoder.matches(users.getPassword(),DAOuser.getPassword())) {
        model.addAttribute("activate", "Неверный логин или пароль");
    }else if(DAOuser.getActivatiCode()!=null){
        model.addAttribute("activate", "Пользователь не активирован");
    }else {
        model.addAttribute("activate","Превышенно максимальное кол-во сессий для одного пользователя");
    }

    return "authenticate/login";
    }

    @GetMapping("/auth/login/err")
    public String logErr(Model model){
    model.addAttribute("err","Кол-во сессий пользователя превышено");

    return "authenticate/loginError";
    }

  //  @ExceptionHandler(BadCredentialsException.class)
 //   public String handleActivateException( Exception e){

 //   return "authenticate/loginError";
 //   }

    @GetMapping("/registration")
    public String registr(@ModelAttribute("user")Users user){

    return "authenticate/registr";
    }

    @PostMapping("/registration")
    public String PostRegistr(@ModelAttribute("user") Users user) throws MessagingException, UnsupportedEncodingException {

    userRegistration.Registration(user);

        return "redirect:/auth/login";
    }

    @GetMapping("/registration/getcodeagain")
    public  String getcode(@ModelAttribute("getCode") String code){

    return "authenticate/registr";
    }

    @PostMapping("/registration/sendcode")
    public String Sendcodeagain( @RequestParam(value = "email",required = false) String email,@ModelAttribute("getCode") String code) throws MessagingException, UnsupportedEncodingException {
        System.out.println(email+ " емейл юзера");
        if(email==null || email.equals("")){
            return "authenticate/registr";
        }
        Optional<Users> DataUser=userService.getOneByEmail(email);
        if(DataUser.isPresent() && DataUser.get().getActivatiCode()!=null){
        Map<String,Object> map=new HashMap<>();
        map.put("code",DataUser.get().getActivatiCode());
        emailSenderService.sendMimeEmail(email,"Активация аккаунта","/mail/activate.html",
                DataUser.get().getActivatiCode(),map);
        }
        return "redirect:/auth/login";
    }



    @GetMapping("/activate/{code}")
    public String activateaccount(@PathVariable("code") String code, Model model){
    System.out.println(code);
if(userRegistration.activate(code)){
model.addAttribute("message","Пользователь успешно активирован");
        }else {
    model.addAttribute("message","Код активации не найден или неправильный");
        }
    return "authenticate/login";
    }
}
