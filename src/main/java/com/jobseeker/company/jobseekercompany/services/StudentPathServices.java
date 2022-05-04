package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dto.CareerPath;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentPathServices {

    public static final String STUDENT_COL_NAME = "js-student";
    public static final String CAREER_PATH_COL_NAME = "js-career-path";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public List<CareerPath> getAllPaths() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(CAREER_PATH_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().map(a -> a.toObject(CareerPath.class)).collect(Collectors.toList());
    }

    public Set<String> getAllPlusTwo() throws ExecutionException, InterruptedException {
        List<CareerPath> careerPaths = getAllPaths();
        Set<String> plustwoSet = new TreeSet<>();

        for (CareerPath tempPath: careerPaths) {
            plustwoSet.add(tempPath.getPlusTwo().toUpperCase());
        }
        return plustwoSet;
    }

    public Set<String> getAllGraduation(String plusTwo) throws ExecutionException, InterruptedException {
        List<CareerPath> careerPaths = getAllPaths();
        Set<String> graduationSet = new TreeSet<>();

        for (CareerPath tempPath: careerPaths) {
            if(tempPath.getPlusTwo().toUpperCase().equals(plusTwo.toUpperCase())) {
                graduationSet.add(tempPath.getGraduation().toUpperCase());
            }
        }
        return graduationSet;
    }

    public Set<String> getAllPostGraduation(String plusTwo,String graduation) throws ExecutionException, InterruptedException {
        List<CareerPath> careerPaths = getAllPaths();
        Set<String> pgSet = new TreeSet<>();

        for (CareerPath tempPath: careerPaths) {
            if(tempPath.getPlusTwo().toUpperCase().equals(plusTwo.toUpperCase())
                    && tempPath.getGraduation().toUpperCase().equals(graduation.toUpperCase())) {
                pgSet.add(tempPath.getPostGrad().toUpperCase());
            }
        }
        return pgSet;
    }

    public Set<String> getAllCareers(String plusTwo,String graduation,String pg) throws ExecutionException, InterruptedException {
        List<CareerPath> careerPaths = getAllPaths();
        Set<String> careerSet = new TreeSet<>();

        for (CareerPath tempPath: careerPaths) {
            if(tempPath.getPlusTwo().toUpperCase().equals(plusTwo.toUpperCase())
                    && tempPath.getGraduation().toUpperCase().equals(graduation.toUpperCase())
                    && tempPath.getPostGrad().toUpperCase().equals(pg.toUpperCase())) {
                careerSet.add(tempPath.getRole()    .toUpperCase());
            }
        }
        return careerSet;
    }

    public List<CareerPath> getAllSavedCareers(String email) throws ExecutionException, InterruptedException {
        return dbFirestore.collection("js-student-career").get().get().getDocuments().stream().map(a -> a.toObject(CareerPath.class))
                .collect(Collectors.toList()).stream().filter( careerPath -> { return careerPath.getStudentEmail().equalsIgnoreCase(email); } )
                .collect(Collectors.toList());
    }

}
