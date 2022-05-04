package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import com.jobseeker.company.jobseekercompany.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentAccessServices {

    public static final String STUDENT_COL_NAME="js-student";
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public List<Student> getAllStudents() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(STUDENT_COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().map( a -> a.toObject(Student.class)).collect(Collectors.toList());
    }

    public Student loginRecruiter(LoginRequest recruiter) throws ExecutionException, InterruptedException {
        Optional<Student> students = getAllStudents()
                .stream()
                .filter( c -> ( c.getEmail().equals(recruiter.getUsername()) && c.getPassword().equals(recruiter.getPassword())))
                .findFirst();

        return students.isPresent() ? students.get() : null;
    }
}