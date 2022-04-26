package com.jobseeker.company.jobseekercompany.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dao.profiles.ProfileMatchWithPercentage;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.services.CompanyVacancyReportingService;
import com.jobseeker.company.jobseekercompany.services.JobSeekerAccessService;
import com.jobseeker.company.jobseekercompany.services.JobSeekerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Candidate/profile")
public class JobSeekerUpdateProfileController {

    @Autowired
    JobSeekerAccessService jobSeekerAccessService;

    @Autowired
    JobSeekerProfileService jobSeekerProfileService;

    @Autowired
    CompanyVacancyReportingService companyVacancyReportingService;

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

    @GetMapping("get-matching-profile-per")
    public ResponseEntity getMatchingVacanciesBasedOnProfile(String uid) throws ExecutionException, InterruptedException {
        Jobseeker jobSeeker = jobSeekerAccessService.getAllUsers().stream()
                .filter(jobseeker -> { return jobseeker.getUid().equals(uid) ? true : false;})
                .collect(Collectors.toList()).get(0);

        List <Profile> jobProfiles = companyVacancyReportingService.getAllVacancies();
        List <ProfileMatchWithPercentage> jobProfileWithPercentageList = new ArrayList<>();

        int matchCount=0;
        for (Profile profile: jobProfiles) {

//            if( profile.getQualification() != null && jobSeeker.getProfile().getQualification() != null ){
//                if( profile.getQualification().get(0).getCourse().equals( jobSeeker.getProfile().getQualification().get(0).getCourse() ) ||
//                        profile.getQualification().get(0).getCourse().equals( jobSeeker.getProfile().getQualification().get(1).getCourse()) ||
//                        profile.getQualification().get(0).getCourse().equals( jobSeeker.getProfile().getQualification().get(2).getCourse()))
//                {
//                    matchCount++;
//                }
//
//            }


            ProfileMatchWithPercentage profileMatchWithPercentageCreated = new ProfileMatchWithPercentage();

            /* Mapping started */
            profileMatchWithPercentageCreated.setUuid(profile.getUuid());
            profileMatchWithPercentageCreated.setRole(profile.getRole());
            profileMatchWithPercentageCreated.setDescription(profile.getDescription());
            profileMatchWithPercentageCreated.setQualification(profile.getQualification());
            profileMatchWithPercentageCreated.setSkill(profile.getSkill());
            profileMatchWithPercentageCreated.setMinPay(profile.getMinPay());
            profileMatchWithPercentageCreated.setMaxPay(profile.getMaxPay());
            profileMatchWithPercentageCreated.setLocationOfEmployment(profile.getLocationOfEmployment());
            profileMatchWithPercentageCreated.setShouldPossesPassport(profile.getShouldPossesPassport());
            profileMatchWithPercentageCreated.setWillingToRelocate(profile.getWillingToRelocate());
            profileMatchWithPercentageCreated.setLinkedInProfile(profile.getLinkedInProfile());
            profileMatchWithPercentageCreated.setReportingCompany(profile.getReportingCompany());
            profileMatchWithPercentageCreated.setPercentageMatch(matchCount);
            /* Mapping ended */

            profileMatchWithPercentageCreated.setPercentageMatch(matchCount);
            jobProfileWithPercentageList.add(profileMatchWithPercentageCreated);

        }

        return ResponseEntity.ok(jobProfileWithPercentageList);
    }



}
