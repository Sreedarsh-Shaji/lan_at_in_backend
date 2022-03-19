package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

public class ReffService {

   /* public static final String COL_NAME="js-admin";

    public String saveAdminDetails(Admin admin) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture
                = dbFirestore.collection(COL_NAME).document(admin.getName()).set(admin);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Admin getAdminDetailsByName(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
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

    public String updateAdminDetails(Admin admin) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture
                = dbFirestore.collection(COL_NAME).document(admin.getName()).set(admin);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteAdmin(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Admin ID "+name+" has been deleted";
    }

    public Admin adminLogin(String mailId , String password) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
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

    }*/

}
