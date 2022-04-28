package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecruiterServices {

    public static final String COMPANY_COL_NAME="js-recruiter";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    @Autowired
    CompanyInterviewService companyInterviewService;

    public void selectCandidates(String uuid) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("js-Invites").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<InvitedProfile> invitedProfiles = documents.stream().map( a -> a.toObject(InvitedProfile.class)).collect(Collectors.toList());

        InvitedProfile invitedProfileSelected = null;

        for (InvitedProfile invitedProfile:invitedProfiles ) {
            if(invitedProfile.getUuid().equals(uuid)){
                invitedProfileSelected = invitedProfile;
            }
        }

        this.saveSelectedCandidates(invitedProfileSelected);

    }

    public String saveSelectedCandidates(InvitedProfile profile)
    {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("js-selected")
                .document(profile.getUuid().toString()).set(profile);
        return "Saved successfully";
    }

}
