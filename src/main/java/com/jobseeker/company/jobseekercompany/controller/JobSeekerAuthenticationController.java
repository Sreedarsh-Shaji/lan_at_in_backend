package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.services.JobSeekerAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequestMapping("/api/v1/userAuth")
public class JobSeekerAuthenticationController {

    @Autowired
    private JobSeekerAccessService service;

    @PostMapping("/signup-user")
    public ResponseEntity<Jobseeker> signupUser(@RequestBody Jobseeker newUser) throws ExecutionException, InterruptedException {
        newUser.setUid(UUID.randomUUID().toString());
        service.saveUserDetails(newUser);
        return new ResponseEntity<Jobseeker>( newUser , HttpStatus.OK );
    }

    @PostMapping("/login-user")
    public ResponseEntity<Jobseeker> loginUser(@RequestBody LoginRequest newUser) throws ExecutionException, InterruptedException {
        return new ResponseEntity<Jobseeker>( service.loginUser(newUser) , HttpStatus.OK );
    }

    /*
    * This is a test program
    * */

    @GetMapping("/{id}/all")
    public List<Jobseeker> getAll(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        if( id.equals("pass") )
        {
            return service.getAllUsers();
        }
        return null;
    }

}
