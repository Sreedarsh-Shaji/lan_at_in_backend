package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import com.jobseeker.company.jobseekercompany.services.CompanySelectedCandidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Company/invites")
public class CompanyCandidateInviteController {

    @Autowired
    CompanySelectedCandidatesService companySelectedCandidatesService;

    @PostMapping("/save")
    ResponseEntity<String> inviteCandidateForInterview(@RequestBody InvitedProfile invitedProfiles) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(companySelectedCandidatesService.saveInvites(invitedProfiles));
    }

    @GetMapping("/get-all-invited")
    ResponseEntity<List<InvitedProfile>> getAllInvited() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(companySelectedCandidatesService.getAllInvites());
    }

    @PostMapping("/save-interview-candidate")
    ResponseEntity<String> getAllInvitedCandidate(@RequestBody InvitedProfile profile) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(companySelectedCandidatesService.saveSelectedCandidates(profile));
    }

}
