package com.myblog.controller;
import com.myblog.model.BlogProfile;
import com.myblog.model.PersonalProfile;
import com.myblog.model.MeResponse;
import com.myblog.model.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, I Exist.";
    }

    @GetMapping("/time")
    public String time() {
        return "Server time: " + LocalDateTime.now().toString();
    }

    @GetMapping("/me")
    public MeResponse me(){
        MeResponse meResponse = new MeResponse();
        meResponse.name = "MyBlog";
        meResponse.owner = "Mila";
        meResponse.hobby = "walking";
        meResponse.vision = "Build my digital self";
        meResponse.food = "tomato";
        return meResponse;
    }

    @GetMapping("/profile")
    public PersonalProfile profile() {
        PersonalProfile profile = new PersonalProfile();
        profile.blogName = "MyBlog";
        profile.owner = "Mila";
        profile.introduction = "Nice to meet you.";
        profile.interests = "walking";
        profile.vision = "vision1";
        return profile;
    }
    @GetMapping("/status")
    public Status status(){
        Status status = new Status();
        status.status = "alive";
        status.week = 2;
        status.ability = "I can speak.";
        return status;
    }
}

