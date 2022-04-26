package com.jobseeker.company.jobseekercompany.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.services.JobSeekerAccessService;
import com.jobseeker.company.jobseekercompany.services.JobSeekerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Candidate/profile")
public class JobSeekerUpdateProfileController {

    @Autowired
    JobSeekerAccessService jobSeekerAccessService;

    @Autowired
    JobSeekerProfileService jobSeekerProfileService;

    @PutMapping("/update")
    public ResponseEntity<String> setProfileDetails(@RequestParam("uuid") String uuid, @RequestBody Profile profile) throws ExecutionException, InterruptedException {
        Jobseeker jobSeeker = jobSeekerAccessService.getAllUsers().stream()
                .filter(jobseeker -> { return jobseeker.getUid().equals(uuid) ? true : false;})
                .collect(Collectors.toList()).get(0);

        jobSeeker.setProfile(profile);
        jobSeekerAccessService.update(jobSeeker);
        return ResponseEntity.ok("Saved profile details");
    }

    @GetMapping("is-profile-set")
    public ResponseEntity isProfileUpdated(@RequestParam("id") String id) throws ExecutionException, InterruptedException {
        Jobseeker jobSeeker = jobSeekerAccessService.getAllUsers().stream()
                .filter(jobseeker -> { return jobseeker.getUid().equals(id) ? true : false;})
                .collect(Collectors.toList()).get(0);

        boolean isSet = jobSeeker.getProfile()==(null) ? false : true;
        return ResponseEntity.ok(isSet);

    }



}
