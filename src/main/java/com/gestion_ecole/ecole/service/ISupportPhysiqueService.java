package com.gestion_ecole.ecole.service;

import org.springframework.web.multipart.MultipartFile;

import com.gestion_ecole.ecole.entities.Reponse;
public interface ISupportPhysiqueService {
	 public Reponse uploadFile(MultipartFile file,String type) ;
	    public Reponse load(String filename);
	    public Reponse deleteFile(String url);
	    public Reponse deleteAll();
	    public Reponse loadAll();
}
