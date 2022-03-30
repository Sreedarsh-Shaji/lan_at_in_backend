package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CompanySelectedCandidatesService {

    public static final String COMPANY_COL_NAME="js-Invites";
    public static final String COMPANY_PLACED_CANDIDATES="js-Invites";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveInvites(InvitedProfile profile) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                .document(profile.getUuid().toString()).set(profile);
        return "Saved successfully";
    }

    public String saveSelectedCandidates(InvitedProfile profile) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_PLACED_CANDIDATES)
                .document(profile.getUuid().toString()).set(profile);
        return "Saved successfully";
    }

    public List<InvitedProfile> getAllInvites() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COMPANY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(InvitedProfile.class));
        }
        return documents.stream().map( a -> a.toObject(InvitedProfile.class)).collect(Collectors.toList());
    }

}
