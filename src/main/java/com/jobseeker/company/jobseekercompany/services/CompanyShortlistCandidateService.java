package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CompanyShortlistCandidateService {

    public static final String COMPANY_COL_NAME="js-selected-candidates";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveSelectedCandidates(InvitedProfile profile) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                .document(profile.getUuid().toString()).set(profile);
        return "Saved successfully";
    }

}
