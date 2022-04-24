package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.Admin;
import com.jobseeker.company.jobseekercompany.dto.CareerPath;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CareerPathService {

    public static final String COL_NAME="js-career-path";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveCareerPath(CareerPath careerPath) throws InterruptedException, ExecutionException {

        ApiFuture<WriteResult> collectionsApiFuture
                = dbFirestore.collection(COL_NAME).document(careerPath.getId().toString()).set(careerPath);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}
