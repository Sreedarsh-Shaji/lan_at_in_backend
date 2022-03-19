package com.jobseeker.company.jobseekercompany.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Experience {

    private String companyName;
    private String companyAddress;
    private Date startDate;
    private Date endDate;

}