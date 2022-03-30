package com.jobseeker.company.jobseekercompany.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping("/utils")
public class UtilController {

    List<String> states= Arrays.asList("Uttar Pradesh","West Bengal","Maharashtra","Karnataka","Rajasthan","Andhra Pradesh","Madhya Pradesh",
                        "Gujarat","Bihar","Tamil Nadu","Odisha","Kerala","Assam","Jharkhand","Punjab","Chhattisgarh",
                        "Himachal Pradesh","Uttarakhand","Haryana","Tripura","Delhi","Meghalaya","Goa","Manipur","Mizoram",
                        "Arunachal Pradesh","Nagaland","Puducherry","Andaman and Nicobar Islands","Sikkim","Daman and Diu",
                        "Lakshadweep","Chandigarh","Jammu and Kashmir","Dadra and Nagar Haveli","Ladakh");

    TreeSet<String> sortedStates = new TreeSet<String>(states);

    @GetMapping("/States")
    public TreeSet<?> getAllIndianStates()
    {
        return this.sortedStates;
    }



}
