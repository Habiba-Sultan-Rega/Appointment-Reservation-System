package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.User;
import com.ea.group6.appointmentsystem.security.JWTService;
import com.ea.group6.appointmentsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {
    private JWTService jwtService;
    private UserService userService;

    @Autowired
    public AuthenticationController(JWTService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String,Object>> authenticate(@RequestBody Map<String,String> request)
    {
        if(!request.containsKey("username")||!request.containsKey("password")){
            return ResponseEntity.badRequest().body(Map.of("reason","Incorrect User Name and Password"));
        }
        String username = request.get("username");
        String password = request.get("password");

        User user = userService.findUserByUsernameAndPassword(username, password);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("result","Bad Credential"));
        }

        Map<String,Object> response = new HashMap<>();
        response.put("token",jwtService.generateToken(user));
        response.put("firstname",user.getFirstName());
        response.put("lastname",user.getLastName());
        response.put("id",user.getId()+"");
        response.put("roles",userService.findUserRolesByUserId(user.getId()));

        return ResponseEntity.ok(response);

    }
}
