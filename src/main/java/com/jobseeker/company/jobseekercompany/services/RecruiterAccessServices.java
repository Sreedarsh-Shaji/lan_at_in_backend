package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Company;
import com.jobseeker.company.jobseekercompany.dto.Credentials;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecruiterAccessServices {

    public static final String COMPANY_COL_NAME="js-recruiter";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public List<Recruiter> getAllRecruiter() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COMPANY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Recruiter.class)).collect(Collectors.toList());
    }

    public Recruiter loginRecruiter(LoginRequest recruiter) throws ExecutionException, InterruptedException {
        Optional<Recruiter> recruiters = getAllRecruiter()
                .stream()
                .filter( c -> ( c.getEmail().equals(recruiter.getUsername()) && c.getPassword().equals(recruiter.getPassword())))
                .findFirst();

        return recruiters.isPresent()? recruiters.get() : null;
    }
}
