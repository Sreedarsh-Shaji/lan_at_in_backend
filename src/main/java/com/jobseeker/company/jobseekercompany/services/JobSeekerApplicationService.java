package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.auto.value.extension.serializable.SerializableAutoValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.ApplicationProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.InvitedProfile;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Company;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class JobSeekerApplicationService {

    public static final String COMPANY_COL_NAME="js-application";
    public static final String JOB_SEEKER_COL_NAME="js-Invites";

    Firestore dbFirestore = FirestoreClient.getFirestore();

    public boolean saveUserApplication(ApplicationProfile profile) throws ExecutionException, InterruptedException {
        profile.getJobseeker().setUid(UUID.randomUUID().toString());
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                .document(profile.getUuid().toString()).set(profile);
        return true;

    }

    public List<ApplicationProfile> getAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COMPANY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(ApplicationProfile.class)).collect(Collectors.toList());

    }

    public List<ApplicationProfile> getByMail(String mail) throws ExecutionException, InterruptedException {
        return this.getAll().stream().filter(f -> f.getJobseeker().getEmail().equals(mail)).collect(Collectors.toList());
    }

    public List<ApplicationProfile> getById(String id) throws ExecutionException, InterruptedException {
        return this.getAll().stream().filter(f -> f.getJobseeker().getUid().equals(id)).collect(Collectors.toList());
    }

}
