package com.gestion_ecole.ecole.service;

import com.gestion_ecole.ecole.dto.request.UserDtoRequest;
import com.gestion_ecole.ecole.entities.Reponse;

public interface IAccountService {
      // SERVICE UTILISATEUR
	
	      
	         //GESTION User
	  public void initAccount();
	  public String getToken(String email , String password);
	  public Reponse getUserById(Long id,String type);
	  public Reponse getAllUsersByType(String type);
	  public Reponse getUserByTelephone(String telephone);
	  public Reponse getUserByEmail(String Email);
	  public Reponse bloquerUser(Long id); 
	  public Reponse se_connecter(String Username,String password);
	  public Reponse activation(int code);
	  public Reponse createOrUpdateUser(UserDtoRequest user);
	  public Reponse CheckEmailOrPhone(String tel,String email) ;
	  
	 }
