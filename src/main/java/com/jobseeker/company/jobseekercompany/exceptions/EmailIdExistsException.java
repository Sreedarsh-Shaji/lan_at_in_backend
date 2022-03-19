package com.jobseeker.company.jobseekercompany.exceptions;

public class EmailIdExistsException extends Exception{

    public EmailIdExistsException() {
    }

    public EmailIdExistsException(String message) {
        super(message);
    }
}
