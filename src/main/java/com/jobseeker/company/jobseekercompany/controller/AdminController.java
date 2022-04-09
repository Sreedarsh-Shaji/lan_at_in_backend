package com.jobseeker.company.jobseekercompany.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @GetMapping("/login/{username}/{password}")
    public String adminLogin(@PathVariable(value = "username") String username , @PathVariable(value = "password") String password)
    {
        if( username.equals("admin") && password.equals("admin") ){
            return "Valid credentials";
        }
        else
        {
            return null;
        }
    }

}
