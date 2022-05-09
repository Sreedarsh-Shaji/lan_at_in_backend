package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("Admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    AdminService adminService;

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

    @GetMapping("/view-all-companies")
    public ResponseEntity getAllCompanies() throws ExecutionException, InterruptedException {

        return ResponseEntity.ok(adminService.getAllCompanies()) ;
    }



}
