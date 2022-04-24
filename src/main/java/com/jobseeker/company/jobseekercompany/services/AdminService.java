package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.Admin;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Company;
import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import com.jobseeker.company.jobseekercompany.utils.password.PasswordHasher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class AdminService {

    public static final String COL_NAME="js-admin";
    public static final String COMPANY_COL_NAME="js-company";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String saveAdminDetails(Admin admin) throws InterruptedException, ExecutionException {

        ApiFuture<WriteResult> collectionsApiFuture
                = dbFirestore.collection(COL_NAME).document(admin.getName()).set(admin);

        admin.encryptPassword();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Admin getAdminDetailsByName(String name) throws InterruptedException, ExecutionException
    {
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Admin admin = null;
        if(document.exists()) {
            admin = document.toObject(Admin.class);
            return admin;
        }else {
            return null;
        }
    }


    public List<Company> getAllCompanies() throws InterruptedException, ExecutionException
    {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COMPANY_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Profile.class));
        }
        return documents.stream().map( a -> a.toObject(Company.class)).collect(Collectors.toList());
    }

    public String updateAdminDetails(Admin admin) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> collectionsApiFuture
                = dbFirestore.collection(COL_NAME).document(admin.getName()).set(admin);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteAdmin(String name) {
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Admin ID "+name+" has been deleted";
    }

    public List<Admin> getAllAdmins() throws ExecutionException, InterruptedException {
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(Admin.class));
        }
        return documents.stream().map( a -> a.toObject(Admin.class)).collect(Collectors.toList());
    }

    public Boolean adminLogin(String mailId , String password) throws ExecutionException, InterruptedException {

       List<Admin> admins = this.getAllAdmins();

       Admin credsMatchedAdmin = admins.stream()
               .filter( admin -> ( admin.getEmail().equals( mailId )
                       && PasswordHasher.equatePasswordAndHash( password , admin.getPassword() )) )
               .collect(Collectors.toList()).get(0);

        return credsMatchedAdmin == null ? true : false;
    }

}
