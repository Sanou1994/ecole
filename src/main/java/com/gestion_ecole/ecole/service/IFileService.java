package com.gestion_ecole.ecole.service;

import org.springframework.web.multipart.MultipartFile;

import com.gestion_ecole.ecole.entities.Reponse;

public interface IFileService {
	 public Reponse uploadFile(MultipartFile file,String type) ;
	 public Reponse createAllDirectories();
}
