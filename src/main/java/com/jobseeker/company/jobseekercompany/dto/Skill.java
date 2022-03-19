package com.jobseeker.company.jobseekercompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    List<ProfessionalSkill> skills;

    List<ProfessionalSkill> add(ProfessionalSkill skill){
        this.skills.add(skill);
        return this.skills;
    }

    List<ProfessionalSkill> removeQualification(ProfessionalSkill skill){
        this.skills.removeIf( item -> item.getSkillName().equals(skill.getSkillName()) );
        return this.skills;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ProfessionalSkill
{
    String skillName;
    String skillType;
    SKILL_LEVEL skillLevel;
    int yearsOfExperience;
}

enum SKILL_LEVEL{
    BASIC,INTERMEDIATE,ADVANCED
}