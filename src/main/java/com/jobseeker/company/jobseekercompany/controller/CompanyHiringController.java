package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.services.CompanyVacancyReportingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/hiring")
@RequiredArgsConstructor
@Slf4j
public class CompanyHiringController {

    private final CompanyVacancyReportingService service;

    @PostMapping("/save-vacancy")
    private ResponseEntity<?> saveVacancyService(@RequestBody Profile profile) throws ExecutionException, InterruptedException {
        log.info("Vacancy reported!!!");
        profile.setUuid(UUID.randomUUID().toString());
        return ResponseEntity.ok().body( service.saveVacancy(profile) );
    }

    @GetMapping("/get-all-vacancies")
    private ResponseEntity<?> getAllVacancies() throws ExecutionException, InterruptedException {
        log.info("All Vacancies listed!!!");
        return ResponseEntity.ok().body( service.getAllVacancies() );
    }


    @GetMapping("/get-company-vacancy")
    private ResponseEntity<?> getCompanyVacancies(@PathVariable String companyName) throws ExecutionException, InterruptedException {
        log.info("All company listed vacancies!!!");
        return ResponseEntity.ok().body( service.getAllVacancies() );
    }

    @GetMapping("/get-vacancy/{id}")
    private ResponseEntity<?> getSingleVacancy(@PathVariable("id") String id)throws ExecutionException, InterruptedException
    {
        return ResponseEntity.ok().body( service.getAVacancyById(id) );
    }

    @GetMapping("/get-vacancy/mobile")
    private ResponseEntity<?> getSingleVacancyMobile(@RequestParam("id") String id)throws ExecutionException, InterruptedException
    {
        return ResponseEntity.ok().body( service.getAVacancyById(id) );
    }

    @GetMapping("/close-vacancy")
    private ResponseEntity<?> closeVacancies(@PathVariable String vacancyId) throws ExecutionException, InterruptedException {
        log.info("All company listed vacancies!!!");
        return ResponseEntity.ok().body( service.suspendVacancies(vacancyId) );
    }

    @GetMapping("/reopen-vacancy")
    private ResponseEntity<?> reopenVacancies(@PathVariable String vacancyId) throws ExecutionException, InterruptedException {
        log.info("All company listed vacancies!!!");
        return ResponseEntity.ok().body( service.reopenVacancies(vacancyId) );
    }

    @GetMapping("/see-matching-profiles")
    private ResponseEntity<?> seeMatchingProfiles() throws ExecutionException, InterruptedException {
        log.info("All company listed vacancies!!!");
        return ResponseEntity.ok().body( service.seeMatchingProfiles() );
    }



}
