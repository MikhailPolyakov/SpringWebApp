package com.example.Java_Diplom.contollers;


import com.example.Java_Diplom.Repositories.ProductTypeRepository;
import com.example.Java_Diplom.Services.UserAdminService;
import com.example.Java_Diplom.Services.UserProductService;
import com.example.Java_Diplom.models.ProductType;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
private final SessionRegistry sessionRegistry;
private final UserProductService userProductService;
private final ProductTypeRepository productTypeRepository;
private final UserAdminService userAdminService;
    public MainController(SessionRegistry sessionRegistry, UserProductService userProductService, ProductTypeRepository productTypeRepository, UserAdminService userAdminService) {
        this.sessionRegistry = sessionRegistry;
        this.userProductService = userProductService;
        this.productTypeRepository = productTypeRepository;

        this.userAdminService = userAdminService;
    }

    @GetMapping("/main")
    public String main(){
        return "main/main";

    }

    @GetMapping("/")
    public String glavnay(HttpServletRequest httpServletRequest,Model model){
 //       productTypeRepository.findAll();
        System.out.println("Maim controller ...");
        List<ProductType> add=productTypeRepository.findAll();
//model.addAttribute("products",userProductService.getAllProducts());
      model.addAttribute("type",add);
      //  File file=new File("C:/Users/Mikle/Desktop/Java_Diplom/src/main/resources/static/images/test.jpg");
   //     System.out.println(file);
    //    System.out.println("Значение "+ userProductService.getAllProducts().get(0).getPrice());
  //     System.out.println( httpServletRequest.getParameter("name"));
//for(Cookie cookie:httpServletRequest.getCookies()){
 //   System.out.println(cookie.getName());
 //   System.out.println(cookie.getValue());
//}

        return "main/glavnay";
    }

    @PostMapping("/")
    public String handleFileUpload(Model model,@RequestParam("file") MultipartFile file) throws IOException {
System.out.println( "filename");
String path="C://Users/Mikle/Desktop/Java_Diplom/src/main/resources/static/images";
File file1=new File("C:/Users/Mikle/Desktop/Java_Diplom/src/main/resources/static/images/"+file.getOriginalFilename());
file.transferTo(file1);

   //     storageService.store(file);
  //      redirectAttributes.addFlashAttribute("message",
    //            "You successfully uploaded " + file.getOriginalFilename() + "!");
model.addAttribute("file", file.getOriginalFilename());
        return "main/main";
    }

}
