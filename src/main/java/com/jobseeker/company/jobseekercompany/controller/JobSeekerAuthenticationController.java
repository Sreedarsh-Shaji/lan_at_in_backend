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
    public ResponseEntity loginUser(@RequestBody LoginRequest newUser) throws ExecutionException, InterruptedException {

        System.out.println(newUser.getUsername());
        System.out.println(newUser.getPassword());

        List<Jobseeker> jobseekers = service.getAllUsers();
        Jobseeker jobseeker = null;

        for (Jobseeker temp: jobseekers) {
            System.out.println(" Comparing : " + temp.getEmail());
            System.out.println( newUser.getUsername() + "==" + temp.getEmail() );
            System.out.println( newUser.getUsername().trim().equals(temp.getEmail().trim()) );
            System.out.println( newUser.getPassword() + "==" + temp.getPassword() );
            System.out.println( newUser.getPassword().trim().equals(temp.getPassword().trim()) );
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            if( newUser.getUsername().trim().equals(temp.getEmail().trim()) && newUser.getPassword().trim().equals(temp.getPassword().trim()))
            {
                jobseeker = temp;
            }
        }

        System.out.println("Found a user matching : " + jobseeker );

        return ResponseEntity.ok( jobseeker );
    }

    @PostMapping("/login-user-mobile")
    public ResponseEntity<Jobseeker> loginUserMobile(@RequestBody LoginRequest newUser) throws ExecutionException, InterruptedException {
        List<Jobseeker> jobseekers = service.getAllUsers();
        Jobseeker jobseeker = null;

        for (Jobseeker temp: jobseekers) {
            System.out.println(" Comparing : " + temp.getEmail());
            System.out.println( newUser.getUsername() + "==" + temp.getEmail() );
            System.out.println( newUser.getUsername().trim().equals(temp.getEmail().trim()) );
            System.out.println( newUser.getPassword() + "==" + temp.getPassword() );
            System.out.println( newUser.getPassword().trim().equals(temp.getPassword().trim()) );
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            if( newUser.getUsername().trim().equals(temp.getEmail().trim()) && newUser.getPassword().trim().equals(temp.getPassword().trim()))
            {
                jobseeker = temp;
            }
        }
        return jobseeker == null ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() : ResponseEntity.ok( jobseeker );
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
