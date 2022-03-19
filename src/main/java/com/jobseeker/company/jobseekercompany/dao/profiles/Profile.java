package com.jobseeker.company.jobseekercompany.dao.profiles;

import com.jobseeker.company.jobseekercompany.dto.Education;
import com.jobseeker.company.jobseekercompany.dto.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
@ToString
public class Profile {

    String uuid;
    List<Education> qualification;
    List<Skill> skill;
    int minPay;
    int maxPay;
    String locationOfEmployment;
    String modeOfEmployment;
    boolean shouldPossesPassport;
    boolean willingToRelocate;
    String linkedInProfile;

    public Profile() {
    }
}
