package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dto.Company;
import com.jobseeker.company.jobseekercompany.dto.Credentials;
import com.jobseeker.company.jobseekercompany.services.AccessServices;
import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyAccessController {

    private final AccessServices accessServices;

    @PostMapping("/signup")
    public ResponseEntity<?> companySignup(@RequestBody Company company) throws ExecutionException, InterruptedException {
        company.setUid(UUID.randomUUID().toString());
        company.setRole(ROLES.COMPANY);
        return ResponseEntity.ok().body( accessServices.saveCompany(company) ? "Saved" : "Duplicate mail id" );
    }

    @PostMapping("/login")
    public ResponseEntity<?> companyLogin(@RequestBody Credentials credentials) throws ExecutionException, InterruptedException {
        Company company = accessServices.loginCompany(credentials);
        log.info(credentials.toString());
        return company != null ? ResponseEntity.ok().body(company) : ResponseEntity.ok().body("User dosn't exist");
    }



}
