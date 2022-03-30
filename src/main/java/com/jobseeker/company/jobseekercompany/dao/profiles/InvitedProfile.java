package com.jobseeker.company.jobseekercompany.dao.profiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitedProfile {
    String uuid = UUID.randomUUID().toString();
    ApplicationProfile applicationProfile;
    boolean invited;
    String dateAndTimeOfInterview;
    String meetingDetails;
}
