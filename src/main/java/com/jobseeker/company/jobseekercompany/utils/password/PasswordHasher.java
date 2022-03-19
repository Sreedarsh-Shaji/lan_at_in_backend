package com.jobseeker.company.jobseekercompany.utils.password;


public class PasswordHasher {


    public static String hashPassword(String rawString)
    {
        return String.valueOf(rawString.hashCode());
    }

    public static boolean equatePasswordAndHash(String rawString , String hashedPassword)
    {
        return ( hashPassword(rawString) . equals (hashedPassword) ) ? true : false ;
    }

}
