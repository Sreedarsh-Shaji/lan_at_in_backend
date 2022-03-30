package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dto.Company;
import com.jobseeker.company.jobseekercompany.dto.Credentials;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import com.jobseeker.company.jobseekercompany.services.CompanyAccessServices;
import com.jobseeker.company.jobseekercompany.services.RecruiterAccessServices;
import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/recruiter")
@RequiredArgsConstructor
@Slf4j
public class RecruiterAccessController {

    private final RecruiterAccessServices recruiterAccessServices;
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> companySignup(@RequestBody Recruiter recruiter) throws ExecutionException, InterruptedException {
//        recruiter.setUid(UUID.randomUUID().toString());
//        recruiter.setRole(ROLES.RECRUITER);
//        return ResponseEntity.ok().body( recruiterAccessServices.saveRecruiter(recruiter) ? "Saved" : "Duplicate mail id" );
//    }

    @PostMapping("/login")
    public ResponseEntity<?> companyLogin(@RequestBody LoginRequest login) throws ExecutionException, InterruptedException {
        Recruiter loggedRecruiter = recruiterAccessServices.loginRecruiter(login);
        log.info(login.toString());
        return ResponseEntity.ok().body(loggedRecruiter);
        //return company != null ? ResponseEntity.ok().body(company) : ResponseEntity.ok().body("User dosn't exist");
    }



}
