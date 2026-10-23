package com.myblog.controller;
import com.myblog.model.BlogProfile;
import com.myblog.model.MessageRequest;
import com.myblog.model.PersonalProfile;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    private final List<MessageRequest> messages = new ArrayList<>();

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello, I Exist!";
    }

    @GetMapping("/me")
    public String me() {
        return "My name is MyBlog";
    }

    @GetMapping("/profile1")
    public BlogProfile profile() {
        BlogProfile blogProfile = new BlogProfile();
        blogProfile.name = "MyBlog";
        blogProfile.owner = "Your Name";
        blogProfile.message = "Nice to meet you.";
        return blogProfile;
    }

    @GetMapping("/profile")
    public PersonalProfile personalProfile() {
        PersonalProfile p = new PersonalProfile();
        p.blogName ="Hong's Blog";
        p.owner = "Ruiling Hong";
        p.introduction = "Welcome to Web Programming";
        p.interests = " Soccer ";
        p.vision = "welcome to AI era";

        return p;
    }


}
