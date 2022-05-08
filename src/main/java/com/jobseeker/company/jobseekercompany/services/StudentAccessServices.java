package com.jobseeker.company.jobseekercompany.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.dto.LoginRequest;
import com.jobseeker.company.jobseekercompany.dto.Recruiter;
import com.jobseeker.company.jobseekercompany.dto.Student;
import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
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


    public Jobseeker upgrade(String email) throws ExecutionException, InterruptedException {
        Student student = this.getAllStudents().stream().filter( student1 -> student1.getEmail().equals(email) ).collect(Collectors.toList()).get(0);
        dbFirestore.collection(STUDENT_COL_NAME).document(student.getUid()).delete();

        Jobseeker jobSeeker = new Jobseeker();
        jobSeeker.setUid(student.getUid());
        jobSeeker.setName(student.getName());
        jobSeeker.setPassword(student.getPassword());
        jobSeeker.setEmail(student.getEmail());
        jobSeeker.setPhone(student.getPhone());
        jobSeeker.setRole(ROLES.JOBSEEKER);

        dbFirestore.collection("js-jobseeker").document(jobSeeker.getUid()).set(jobSeeker);
        return jobSeeker;
    }
}
