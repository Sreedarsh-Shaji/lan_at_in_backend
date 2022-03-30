package com.jobseeker.company.jobseekercompany.dao.profiles;

import com.jobseeker.company.jobseekercompany.dto.Jobseeker;
import com.jobseeker.company.jobseekercompany.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationProfile{

   String uuid;
   Jobseeker jobseeker;
   Profile profile;

}