package com.gestion_ecole.ecole.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.repository.PersonnalRepository;

@Transactional
@Service
public class EmailService implements IEmailService{
			@Autowired
	private PersonnalRepository userRepository;
	
}
