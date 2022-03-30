package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import org.springframework.stereotype.Service;

@Service
public class CompanyInterviewService {

    public static final String COMPANY_COL_NAME="js-interview";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String scheduleInterview(InvitedProfile profile)
    {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                .document(profile.getUuid().toString()).set(profile);
        return "Saved successfully";
    }

    public boolean updateInterview()
    {
        return false;
    }

    public boolean informInterviewSchedule()
    {
        return false;
    }

    public boolean updateInterviewResult()
    {
        return false;
    }

}

