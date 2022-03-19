package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class VacancyReportingService {

    public static final String VACANCY_COL_NAME="js-vacancy";

    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveVacancy(Profile profile) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(VACANCY_COL_NAME).document(profile.getUuid()
                .toString()).set(profile);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Profile> getAllVacancies() throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(VACANCY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Profile.class)).collect(Collectors.toList());
    }

    public List<Profile> getCompanyVacancies(String company) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(VACANCY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Profile.class)).collect(Collectors.toList());
    }


    public List<Profile> suspendVacancies(String uuid) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(VACANCY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Profile.class)).collect(Collectors.toList());
    }

    public List<Profile> reopenVacancies(String uuid) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(VACANCY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Profile.class)).collect(Collectors.toList());
    }


    public List<Profile> seeMatchingProfiles(  ) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(VACANCY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Profile.class)).collect(Collectors.toList());
    }


}
