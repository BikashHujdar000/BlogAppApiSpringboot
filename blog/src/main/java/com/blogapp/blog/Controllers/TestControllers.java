package com.blogapp.blog.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/test/")

public class TestControllers {



    @GetMapping("/security")
    public String getMethodName(@RequestParam String param) {
        return " this is unathorixed test";
    }
    
    
}
