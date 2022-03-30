package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dao.profiles.ApplicationProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import com.jobseeker.company.jobseekercompany.services.CompanyInterviewService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/Company/interview")
public class CompanyInterviewScheduleController {

    @Autowired
    CompanyInterviewService companyInterviewService;

    @PostMapping("/save")
    ResponseEntity<String> setInterviewSchedule(@RequestBody InvitedProfile profile)
    {
        profile.setUuid(UUID.randomUUID().toString());
       return ResponseEntity.ok().body(companyInterviewService.scheduleInterview(profile));
    }
}
