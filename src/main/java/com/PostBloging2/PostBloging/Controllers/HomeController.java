package com.PostBloging2.PostBloging.Controllers;


import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Service.PostEntityImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(name = "Контроллер класс для главной страницы ")
@Controller
public class HomeController {

    @Autowired
    private PostEntityImpl postEntityImpl;

    @Operation(summary = "открывает главную сттраницу сайта")
    @GetMapping("/home")
    public String homePage(Model model) {
        List<PostEntity> posts = postEntityImpl.findAll();
        model.addAttribute("posts", posts);
        return "indexHomePage";

    }

}
