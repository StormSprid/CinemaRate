package com.example.cinemarate.Controller;

import com.example.cinemarate.DTO.MovieDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String getAdminPanel(){
        return "redirect:/admin.html";

    }



    }
