package com.jobseeker.company.jobseekercompany.dao.profiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.Mapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileMatchWithPercentage extends Profile{

    float percentageMatch;

    public ProfileMatchWithPercentage mapper(Profile profile)
    {
        ProfileMatchWithPercentage withPercentage = new ProfileMatchWithPercentage();
        withPercentage.setUuid( profile.getUuid() );
        withPercentage.setRole( profile.getRole() );
        withPercentage.setDescription(profile.getDescription());
        withPercentage.setQualification(profile.getQualification());
        withPercentage.setSkill(profile.getSkill());

        return withPercentage;

    }

}
