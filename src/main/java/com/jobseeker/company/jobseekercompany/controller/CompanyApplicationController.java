package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dao.profiles.ApplicationProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.services.JobSeekerAccessService;
import com.jobseeker.company.jobseekercompany.services.JobSeekerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Company/applications")
public class CompanyApplicationController {

    @Autowired
    JobSeekerApplicationService jobSeekerApplicationService;

    @Autowired
    JobSeekerAccessService jobSeekerAccessService;

    @GetMapping("/get-all")
    ResponseEntity<List<ApplicationProfile>> getAllApplications() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(jobSeekerApplicationService.getAll());
    }

    @GetMapping("/get-by-id/{email}")
    ResponseEntity<Jobseeker> getCandidateByMailId(@PathVariable String email) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(jobSeekerAccessService.getAllUsers().stream()
                .filter( f -> f.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList()).get(0));
    }

}
