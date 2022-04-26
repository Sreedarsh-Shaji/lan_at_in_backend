package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.ApplicationProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class JobSeekerProfileService {

    public static final String COMPANY_COL_NAME="js-jobseeker";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public void saveUserDetails(Jobseeker jobseeker) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                .document(jobseeker.getUid()).set(jobseeker);
    }

    public Jobseeker getUserDetailsById(String uid) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COMPANY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        List<Jobseeker> jobseekers = documents.stream()
                .map( a -> a.toObject(Jobseeker.class)).collect(Collectors.toList());
        Jobseeker jobseeker = new Jobseeker();

        for (Jobseeker jobseeker1 : jobseekers)
        {
            if(jobseeker1.getUid().equals(uid)){ return jobseeker1; }
        }

        return jobseeker;
    }


}
