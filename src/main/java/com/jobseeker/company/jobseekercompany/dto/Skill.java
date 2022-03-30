package com.jobseeker.company.jobseekercompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    String skillName;
    String skillType;
    SKILL_LEVEL skillLevel;
    int yearsOfExperience;
}

enum SKILL_LEVEL{
    BASIC,INTERMEDIATE,ADVANCED
}