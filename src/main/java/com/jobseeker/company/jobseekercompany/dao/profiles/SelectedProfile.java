package com.jobseeker.company.jobseekercompany.dao.profiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedProfile extends ShortListedProfile{

    List<PersonalDocument> personalDocuments;

}


class PersonalDocument{
    String idProof;
    String location;
}
