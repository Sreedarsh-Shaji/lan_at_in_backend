package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.Admin;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.CareerPath;
import com.jobseeker.company.jobseekercompany.dto.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CareerPathService {

    public static final String COL_NAME="js-career-path";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveCareerPath(CareerPath careerPath) throws InterruptedException, ExecutionException {
        careerPath.setStudentEmail("");
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(careerPath.getId().toString()).set(careerPath);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<CareerPath> getAllCareerPath() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().map( a -> a.toObject(CareerPath.class)).collect(Collectors.toList());
    }

}
