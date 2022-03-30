package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dao.profiles.ApplicationProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.services.CompanySelectedCandidatesService;
import com.jobseeker.company.jobseekercompany.services.JobSeekerAccessService;
import com.jobseeker.company.jobseekercompany.services.JobSeekerApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/v1/userApply")
public class JobSeekerApplicationController {

    @Autowired
    JobSeekerApplicationService jobSeekerApplicationService;

    @Autowired
    CompanySelectedCandidatesService companySelectedCandidatesService;

    @PostMapping("/apply")
    public ResponseEntity<String> registerApplication(@RequestBody ApplicationProfile profile) throws ExecutionException, InterruptedException {
        profile.setUuid(UUID.randomUUID().toString());
        jobSeekerApplicationService.saveUserApplication(profile);
        return ResponseEntity.ok().body("Applied for position successfully");
    }

    @GetMapping("/get-bt-mail/{mail}")
    public ResponseEntity<List<ApplicationProfile>> getAllBasedOnMail(@PathVariable(value = "mail") String email) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(jobSeekerApplicationService.getByMail(email));
    }

}
