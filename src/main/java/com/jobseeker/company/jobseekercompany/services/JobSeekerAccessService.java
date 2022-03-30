package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class JobSeekerAccessService {

    public static final String COMPANY_COL_NAME="js-jobseeker";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public List<Jobseeker> getAllUsers() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COMPANY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Jobseeker.class));
        }
        return documents.stream().map( a -> a.toObject(Jobseeker.class)).collect(Collectors.toList());
    }

    public Jobseeker loginUser(LoginRequest credentials) throws ExecutionException, InterruptedException {
        Optional<Jobseeker> company = getAllUsers()
                .stream()
                .filter( c -> ( c.getEmail().equals(credentials.getUsername()) && c.getPassword().equals(credentials.getPassword()) ) )
                .findFirst();
        return company.isPresent()? company.get() : null;
    }

    public boolean saveUserDetails(Jobseeker jobseeker) throws ExecutionException, InterruptedException {
        long duplicates = getAllUsers().stream().filter(c -> c.getEmail().equals(jobseeker.getEmail())).count();
        if( duplicates<=0 ) {
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                    .document(jobseeker.getUid()).set(jobseeker);
            return true;
        }
        else{
            return false;
        }
    }

}
