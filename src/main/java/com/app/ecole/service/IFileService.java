package com.app.ecole.service;

import com.app.ecole.dto.response.FileDataDtoResponse;
import com.app.ecole.entities.Reponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	 public Reponse uploadFile(MultipartFile file, String type) ;
     public Reponse createAllDirectories();

}
