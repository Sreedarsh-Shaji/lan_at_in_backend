package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import com.jobseeker.company.jobseekercompany.services.RecruiterService;
import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    RecruiterService recruiterService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Recruiter>> getAllRecruiters() throws ExecutionException, InterruptedException {
           return ResponseEntity.ok().body(recruiterService.getAllRecruiter());
        }

    @PostMapping("/save-a-recruiter")
    public ResponseEntity<String> saveAllRecruiters(@RequestBody Recruiter recruiter) throws ExecutionException, InterruptedException {
            recruiter.setUid(UUID.randomUUID().toString());
            recruiter.setRole(ROLES.RECRUITER);
            return ResponseEntity.ok().body( recruiterService.saveRecruiter(recruiter) ? "Saved" : "Duplicate mail id" );
    }

}
