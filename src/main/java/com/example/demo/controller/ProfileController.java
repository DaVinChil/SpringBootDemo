package com.example.demo.controller;

import com.example.demo.profiles.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProfileController {
    private SystemProfile profile;

    public ProfileController(SystemProfile profile){
        this.profile = profile;
    }

    @GetMapping("profile")
    public String getProfile(){
        return profile.getProfile();
    }
}
