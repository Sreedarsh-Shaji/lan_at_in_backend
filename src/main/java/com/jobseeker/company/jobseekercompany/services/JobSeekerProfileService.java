package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class JobSeekerProfileService {

    public static final String COMPANY_COL_NAME="js-jobseeker";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public void saveUserDetails(Jobseeker jobseeker) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                .document(jobseeker.getUid()).set(jobseeker);
    }


}
