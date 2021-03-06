package com.jobseeker.company.jobseekercompany.dto;

import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recruiter extends User {
   private ROLES role = ROLES.RECRUITER;
   Company company;
}