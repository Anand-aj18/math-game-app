package com.game.demo.service;

import com.game.demo.entity.User;
import com.game.demo.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo repo;

    public AuthService(UserRepo repo){
        this.repo=repo;
    }

    public String login(String email,String pass){

        User u = repo.findByEmail(email).orElseThrow();

        if(!u.getPassword().equals(pass))
            throw new RuntimeException("Invalid login");

        return "LOGIN SUCCESS";
    }
}
