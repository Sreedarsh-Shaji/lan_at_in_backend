package com.jobseeker.company.jobseekercompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uid;
    private String name;
    private String password;
    private String email;
    private String phone;
}
