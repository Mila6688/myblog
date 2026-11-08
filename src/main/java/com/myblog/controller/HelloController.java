package com.myblog.controller;
import com.myblog.model.BlogMe;
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
        return "Hello, I Exist! I am Mila";
    }

    @GetMapping("/me")
    public BlogMe me(){
        BlogMe blogMe = new BlogMe();
        blogMe.name = "Mila";
        blogMe.age = 19;
        return blogMe;
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
        p.blogName ="Mila's Blog";
        p.owner = "Minlin Liu";
        p.introduction = "Good today!";
        p.interests = " walking ";
        p.vision = "simple";

        return p;
    }


}
