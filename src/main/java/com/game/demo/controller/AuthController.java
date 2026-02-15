package com.game.demo.controller;

import com.game.demo.entity.User;
import com.game.demo.service.AuthService;
import com.game.demo.service.OtpService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final OtpService otpService;

    public AuthController(AuthService s, OtpService otpService){
        service=s;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User u){
        return service.login(u.getEmail(),u.getPassword());
    }

    @PostMapping("/otp/send")
    public void send(@RequestParam String phone){
        otpService.generate(phone);
    }

    @PostMapping("/otp/verify")
    public boolean verify(String phone,String otp){
        return otpService.verify(phone,otp);
    }

}
