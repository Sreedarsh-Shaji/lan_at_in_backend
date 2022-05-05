package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dto.Company;
import com.jobseeker.company.jobseekercompany.dto.Credentials;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import com.jobseeker.company.jobseekercompany.services.CompanyAccessServices;
import com.jobseeker.company.jobseekercompany.services.RecruiterAccessServices;
import com.jobseeker.company.jobseekercompany.services.RecruiterServices;
import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recruiter")
@RequiredArgsConstructor
@Slf4j
public class RecruiterAccessController {

    private final RecruiterAccessServices recruiterAccessServices;
    private final RecruiterServices recruiterServices;
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> companySignup(@RequestBody Recruiter recruiter) throws ExecutionException, InterruptedException {
//        recruiter.setUid(UUID.randomUUID().toString());
//        recruiter.setRole(ROLES.RECRUITER);
//        return ResponseEntity.ok().body( recruiterAccessServices.saveRecruiter(recruiter) ? "Saved" : "Duplicate mail id" );
//    }

    @PostMapping("/login")
    public ResponseEntity companyLogin(@RequestBody LoginRequest login) throws ExecutionException, InterruptedException {
        Recruiter loggedRecruiter = recruiterAccessServices.loginRecruiter(login);
        log.info(login.toString());
        return ResponseEntity.ok().body(loggedRecruiter);
        //return company != null ? ResponseEntity.ok().body(company) : ResponseEntity.ok().body("User dosn't exist");
    }

    @PostMapping("/select-candidate")
    public ResponseEntity selectCandidate(@RequestParam String uuid) throws ExecutionException, InterruptedException {
        recruiterServices.selectCandidates(uuid);
        log.info("Selected for interview");
        return ResponseEntity.ok().body("Selected");
        //return company != null ? ResponseEntity.ok().body(company) : ResponseEntity.ok().body("User dosn't exist");
    }

    @GetMapping("/get-selected-candidate/mobile")
    public ResponseEntity getSelectedCandidates(@RequestParam("mail") String mail) throws ExecutionException, InterruptedException {
       return ResponseEntity.ok(recruiterServices.getAllSelectedProfiles().stream().filter(
                profile -> { return profile.getApplicationProfile().getJobseeker().getEmail().equalsIgnoreCase(mail); }
        ).collect(Collectors.toList()));
    }


}
