package com.jobseeker.company.jobseekercompany.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dto.CareerPath;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Student;
import com.jobseeker.company.jobseekercompany.services.StudentAccessServices;
import com.jobseeker.company.jobseekercompany.services.StudentPathServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequestMapping("/api/v1/studentPath")
public class StudentPathController {

    @Autowired
    private StudentPathServices pathServices;

    @GetMapping("/get-all-plustwo")
    public ResponseEntity getPlusTwo() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(pathServices.getAllPlusTwo());
    }

    @GetMapping("/get-all-graduation")
    public ResponseEntity getGraduation(@RequestParam("plus2") String plustwo) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(pathServices.getAllGraduation(plustwo));
    }

    @GetMapping("/get-all-pg")
    public ResponseEntity getPostGraduation(@RequestParam("plus2") String plustwo , @RequestParam("grad") String graduation) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(pathServices.getAllPostGraduation(plustwo,graduation));
    }

    @GetMapping("/get-all-paths")
    public ResponseEntity getCareerPaths(@RequestParam("plus2") String plustwo , @RequestParam("grad") String graduation, @RequestParam("pg") String pg) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(pathServices.getAllCareers(plustwo,graduation,pg));
    }

    @PostMapping("/save-career-path")
    public ResponseEntity saveStudentCareer(@RequestBody CareerPath careerPath)
    {
        careerPath.setId(UUID.randomUUID().toString());
        FirestoreClient.getFirestore().collection("js-student-career").document(careerPath.getId().toString()).set(careerPath);
        return ResponseEntity.ok("Saved Successfully");
    }

    @GetMapping("/get-by-id")
    public ResponseEntity getAllSavedPaths(@RequestParam String email) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok( pathServices.getAllSavedCareers(email) );
    }

}