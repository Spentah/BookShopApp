package com.example.BookShopApp.controllers;

import com.example.BookShopApp.data.Author;
import com.example.BookShopApp.data.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("authors")
public class AuthorPageController {

    private AuthorService authorService;

    @Autowired
    public AuthorPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("authors")
    public String authorsPage(Model model) {
        model.addAttribute("authorData", authorService.getAuthors());
        return "authors/index";
    }
}
