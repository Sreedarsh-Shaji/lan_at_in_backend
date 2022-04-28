package com.jobseeker.company.jobseekercompany.dto;

import com.jobseeker.company.jobseekercompany.dao.profiles.Profile;
import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Jobseeker extends User {
   Profile profile;
   private ROLES role = ROLES.JOBSEEKER;
}