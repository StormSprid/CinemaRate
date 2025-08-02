package com.example.cinemarate.Controller;

import com.example.cinemarate.DTO.MovieDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String getAdminPanel(Principal principal){
        return "Hello, " + principal.getName();

    }



    }
