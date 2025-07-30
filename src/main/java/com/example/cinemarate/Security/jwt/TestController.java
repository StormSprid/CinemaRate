package com.example.cinemarate.Security.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("secured")
public class TestController {
    @GetMapping("/me")
    public String getName(Principal principal){
        if(principal == null){
            return "ERROR: NAME IS NULL";
        }
        return principal.getName();
    }
    @GetMapping
    public String test(){
        return "HELLO";

    }}
