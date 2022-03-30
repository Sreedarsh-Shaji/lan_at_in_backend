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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CompanyAccessServices {

    public static final String COMPANY_COL_NAME="js-company";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public boolean saveCompany(Company company) throws InterruptedException, ExecutionException {

        long duplicates = getAllCompanies().stream().filter(c -> c.getEmail().equals(company.getEmail())).count();
        if( duplicates<=0 ) {
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COMPANY_COL_NAME)
                    .document(company.getUid()).set(company);
            return true;
        }
        else{
            return false;
        }

    }

    public List<Company> getAllCompanies() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COMPANY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Company.class)).collect(Collectors.toList());
    }

    public Company loginCompany(Credentials credentials) throws ExecutionException, InterruptedException {
        Optional<Company> company = getAllCompanies()
                .stream()
                .filter( c -> ( c.getEmail().equals(credentials.getEmail()) && c.getPassword().equals(credentials.getPassword()) ) )
                .findFirst();
        return company.isPresent()? company.get() : null;
    }
}
