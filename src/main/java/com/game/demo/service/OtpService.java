package com.game.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    private Map<String,String> otpStore=new HashMap<>();

    public String generate(String phone){

        String otp=""+(1000+(int)(Math.random()*9000));
        otpStore.put(phone,otp);

        System.out.println("OTP:"+otp); // replace with SMS API
        return otp;
    }

    public boolean verify(String phone,String otp){
        return otp.equals(otpStore.get(phone));
    }
}
