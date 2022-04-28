package com.jobseeker.company.jobseekercompany.controller;

import com.jobseeker.company.jobseekercompany.dto.CareerPath;
import com.jobseeker.company.jobseekercompany.services.CareerPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/career")
public class CareerPathController {

    @Autowired
    CareerPathService service;

    @PostMapping("/save")
    ResponseEntity saveCareerPath(@RequestBody CareerPath careerPath) throws ExecutionException, InterruptedException {
        careerPath.setId(UUID.randomUUID().toString());
        service.saveCareerPath(careerPath);
        return ResponseEntity.ok("Saved");
    }


    @GetMapping("/get-all")
    ResponseEntity getAllCareerPath() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok( service.getAllCareerPath() );
    }

}
