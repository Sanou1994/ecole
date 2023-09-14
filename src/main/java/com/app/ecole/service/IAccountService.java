package com.app.ecole.service;
import com.app.ecole.dto.request.ChangePasswordDtoRequest;
import com.app.ecole.dto.request.UserDtoRequest;
import com.app.ecole.entities.Reponse;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
      // SERVICE UTILISATEUR
	  public void initAccount();
	  public Reponse getUserById(UUID id);
	  public Reponse disableUserById(UUID id);
	  public Reponse getUserByEmail(String Email);
	  public Reponse se_connecter(String Username,String password);
	  public Reponse login_up(UserDtoRequest User);
	  public Reponse changePassword(ChangePasswordDtoRequest changePasswordDtoRequest);
      public Reponse getAllUsers();
}
