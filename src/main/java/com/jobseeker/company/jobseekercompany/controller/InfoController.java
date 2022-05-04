package com.jobseeker.company.jobseekercompany.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InfoController {

    @GetMapping
    private String info()
    {
        return  "<center>" +
                "<h2> Admin Portal - Job Seeker </h2>" +
                "<hr/>" +
                "<table>" +
                "<tr><th>GIT</th><td> : </td><td>   https://github.com/Sreedarsh-Shaji/Placement_portal_snit.git</td></tr>" +
                "<tr><th>Heroku URL</th><td> : </td><td>https://serene-castle-69736.herokuapp.com/</td></tr>" +
                "<tr><th>Swagger URL</th><td> : </td><td><a href ='/swagger-ui.html'>Link</a></td></tr>" +
                "</table>" +
                "</center>";
    }

}
