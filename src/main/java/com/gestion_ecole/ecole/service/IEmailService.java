package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.entities.EmailDetails;

public interface IEmailService {
	// To send a simple email
    boolean sendSimpleMail(EmailDetails details); 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
