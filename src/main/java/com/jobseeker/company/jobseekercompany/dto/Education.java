package com.jobseeker.company.jobseekercompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Education {


    String course;
    String specialisation;
    String college;
    String collegeAddress;
    String university;
    String startingYear;
    String endingYear;


}
