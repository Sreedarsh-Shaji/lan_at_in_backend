package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.services.CompanyShortlistCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("Company/shortlisted-candidate")
public class CompanyShortListedCandidateController {

    @Autowired
    CompanyShortlistCandidateService companyShortlistCandidateService;

    @PostMapping("/save")
    ResponseEntity<String> setSelectedCandidate(@RequestBody InvitedProfile profile) throws ExecutionException, InterruptedException {
        companyShortlistCandidateService.saveSelectedCandidates(profile);
        return ResponseEntity.ok().body("Saved");
    }

}
