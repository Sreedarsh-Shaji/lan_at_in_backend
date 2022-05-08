package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Student;
import com.jobseeker.company.jobseekercompany.services.JobSeekerAccessService;
import com.jobseeker.company.jobseekercompany.services.StudentAccessServices;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

@RestController
@Slf4j
@RequestMapping("/api/v1/studentAuth")
public class StudentAuthenticationController {

    @Autowired
    private StudentAccessServices accessServices;

//    @PostMapping("/signup-student")
//    public ResponseEntity<Jobseeker> signupUser(@RequestBody Jobseeker newUser) throws ExecutionException, InterruptedException {
//        newUser.setUid(UUID.randomUUID().toString());
//        accessServices(newUser);
//        return new ResponseEntity<Jobseeker>( newUser , HttpStatus.OK );
//    }

    @PostMapping("/login-user")
    public ResponseEntity loginUser(@RequestBody LoginRequest newUser) throws ExecutionException, InterruptedException {

        System.out.println(newUser.getUsername());
        System.out.println(newUser.getPassword());

        List<Student> students = accessServices.getAllStudents();
        Student student = null;

        for (Student temp: students) {
            System.out.println(" Comparing : " + temp.getEmail());
            System.out.println( newUser.getUsername() + "==" + temp.getEmail() );
            System.out.println( newUser.getUsername().trim().equals(temp.getEmail().trim()) );
            System.out.println( newUser.getPassword() + "==" + temp.getPassword() );
            System.out.println( newUser.getPassword().trim().equals(temp.getPassword().trim()) );
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            if( newUser.getUsername().trim().equals(temp.getEmail().trim()) && newUser.getPassword().trim().equals(temp.getPassword().trim()))
            {
                student = temp;
            }
        }

        System.out.println("Found a student matching : " + student );

        return ResponseEntity.ok( student );
    }

    @PostMapping("/login-user-mobile")
    public ResponseEntity<Student> loginUserMobile(@RequestBody LoginRequest newUser) throws ExecutionException, InterruptedException {
        List<Student> students = accessServices.getAllStudents();
        Student student = null;

        for (Student temp: students) {
            System.out.println(" Comparing : " + temp.getEmail());
            System.out.println( newUser.getUsername() + "==" + temp.getEmail() );
            System.out.println( newUser.getUsername().trim().equals(temp.getEmail().trim()) );
            System.out.println( newUser.getPassword() + "==" + temp.getPassword() );
            System.out.println( newUser.getPassword().trim().equals(temp.getPassword().trim()) );
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            if( newUser.getUsername().trim().equals(temp.getEmail().trim()) && newUser.getPassword().trim().equals(temp.getPassword().trim()))
            {
                student = temp;
            }
        }
        return student == null ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() : ResponseEntity.ok( student );
    }

    /*
    * This is a test program
    * */

    @GetMapping("/{id}/all")
    public List<Student> getAll(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        if( id.equals("pass") )
        {
            return accessServices.getAllStudents();
        }
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody Student student) throws ExecutionException, InterruptedException {
        accessServices.saveStudent(student);
        return ResponseEntity.ok("Saved");
    }

    @PostMapping("/upgrade")
    public ResponseEntity upgradeStudent(@RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(accessServices.upgrade(email));
    }

}
