package com.example.Java_Diplom.contollers;


import com.example.Java_Diplom.Services.RoleAdminService;
import com.example.Java_Diplom.Services.UserAdminService;
import com.example.Java_Diplom.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

private final RoleAdminService roleAdminService;
    private final UserAdminService userAdminService;

    @Autowired
    public AdminController( RoleAdminService roleAdminService, UserAdminService userAdminService) {

        this.roleAdminService = roleAdminService;

        this.userAdminService = userAdminService;
    }

    @GetMapping("/allUsers")
    public String mainpage(Model model){

        model.addAttribute("users",userAdminService.getAllUsers());
        return "admin/all";
    }


    @GetMapping("/allUsers/{id}")
    public String userpage(@PathVariable("id") int id,Model model){

        model.addAttribute("Obj",userAdminService.getOne(id));
        return "admin/user";
    }

    @GetMapping("/allUsers/{id}/edit")
    public String editUserPage(@PathVariable("id") int id,Model model){
model.addAttribute("Obj",userAdminService.getOne(id));
        return "admin/EditUser";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOneUser(@PathVariable("id") int id){
        userAdminService.deleteOneById(id);
        return "redirect:/admin/allUsers";
    }







    @GetMapping("/{id}/role/edit")
    public String editUserRole(@PathVariable("id") int id,Model model){
        model.addAttribute("Obj",userAdminService.getOne(id));

        model.addAttribute("Roles",roleAdminService.getAll());
        return "admin/EditRole";
    }


    @PatchMapping("/{id}/role/edit")
public String editUserRoleAccept(@RequestParam(value = "answerRoles",required = false) List<Role> roles,@PathVariable("id") int id){
        userAdminService.updateOneRoles(id, roles);
        return "redirect:/admin/allUsers";
    }
}
