package com.foodresq.authservice.controller;
import com.foodresq.authservice.entity.User;
import com.foodresq.authservice.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final UserRepository repo;
    public AuthController(UserRepository repo){this.repo=repo;}
    @PostMapping("/register") public User register(@RequestBody User u){return repo.save(u);}
    @PostMapping("/login") public User login(@RequestBody User u){
        return repo.findByEmail(u.getEmail()).filter(x->x.getPassword().equals(u.getPassword()))
            .orElseThrow(()->new RuntimeException("Invalid credentials"));
    }
}
