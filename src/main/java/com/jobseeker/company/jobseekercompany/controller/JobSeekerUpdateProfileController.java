package com.jobseeker.company.jobseekercompany.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("Candidate/profile")
public class JobSeekerUpdateProfileController {



    public ResponseEntity<String> setProfileDetails()
    {
        return null;
    }



}
