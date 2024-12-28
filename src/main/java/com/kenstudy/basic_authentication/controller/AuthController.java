package com.kenstudy.basic_authentication.controller;

import com.kenstudy.basic_authentication.config.jwt.JWTService;
import com.kenstudy.basic_authentication.dto.AuthRequest;
import com.kenstudy.basic_authentication.entity.Products;
import com.kenstudy.basic_authentication.entity.UserInfo;
import com.kenstudy.basic_authentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String homePage(){
        return "Welcome to our website";
    }

    @GetMapping("/product/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Products> getAllProducts(){
        return authService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Products getProduct(@PathVariable int productId){
        return authService.getProductById(productId);
    }

    @PostMapping("/adduser")
    public String addUsers(@RequestBody UserInfo userInfo){
        authService.addUsers(userInfo);
        return "added new user";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager
             .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return  jwtService.generateToken(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid user request!");
        }

    }

}
