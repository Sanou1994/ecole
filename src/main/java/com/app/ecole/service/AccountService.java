package com.app.ecole.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.app.ecole.dto.request.ChangePasswordDtoRequest;
import com.app.ecole.dto.request.UserDtoRequest;
import com.app.ecole.dto.response.UserDtoResponse;
import com.app.ecole.entities.Reponse;
import com.app.ecole.entities.Utilisateur;
import com.app.ecole.repository.IDaoGroupe;
import com.app.ecole.repository.IDaoUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService implements IAccountService{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private IDaoUser userRepository;
	@Autowired
	private IDaoGroupe daoGroupe;
	@Autowired(required=true)
	private ModelMapper modelMapper;
	@Override
	public Reponse login_up(UserDtoRequest user)
	{

		Reponse reponse = new Reponse();
		try
		{

			if(user.getID() != null)
			{
				Optional<Utilisateur>  userGot = userRepository.getUserByEmailAndTelephoneAndId(user.getEmail(),user.getTelephone(),user.getID());
				userGot.ifPresentOrElse(
						(userObj)->
						{
							reponse.setMessage("Cet email ou téléphone est déjà utilisé !");
							reponse.setCode(201);

						},
						()->
						{

							Optional<Utilisateur>  userById = userRepository.findById(user.getID());

							if(user.getPrenom() != null && !user.getPrenom().equals(userById.get().getPrenom()))
							{
								userById.get().setPrenom(user.getPrenom());
							}
							if(user.getNom() != null && !user.getNom().equals(userById.get().getNom()))
							{
								userById.get().setNom(user.getNom());
							}
							if(user.getEmail() != null && !user.getEmail().equals(userById.get().getEmail()))
							{
								userById.get().setEmail(user.getEmail());
							}
							if(user.getTelephone() != null && !user.getTelephone().equals(userById.get().getTelephone()))
							{
								userById.get().setTelephone(user.getTelephone());
							}

							if(user.getGroupeID() != null && user.getGroupeID().compareTo(userById.get().getGroupeID()) != 0)
							{
								userById.get().setGroupeID(user.getGroupeID());
							}

							Utilisateur userSave = userRepository.save(userById.get());
							var userDtoResponse=modelMapper.map(userSave, UserDtoResponse.class);
							if(userSave.getGroupeID() != null){ daoGroupe.findByID(userSave.getGroupeID()).ifPresent(p-> userDtoResponse.setGroupe(p)); }

							reponse.setResult(userDtoResponse);
							reponse.setMessage("Ce compte a été modifié avec succès");
							reponse.setCode(200);

						}
				);



			}
			else
			{
				Optional<Utilisateur>  userByEmail = userRepository.getUserByEmailOrTelephone(user.getEmail(),user.getTelephone());
				String pwdCryp = bCryptPasswordEncoder.encode(user.getPassword());
				userByEmail.ifPresentOrElse(
						(userGot)->{
							reponse.setMessage("Cet email est déjà utilisé !");
							reponse.setCode(201);

						},
						()->
						{

							var ambuser =modelMapper.map(user, Utilisateur.class);
							ambuser.setPassword(pwdCryp);
							ambuser.setStatus(true);


							Utilisateur userSave = userRepository.save(ambuser);
							var amb =modelMapper.map(userSave, UserDtoResponse.class);
							if(userSave.getGroupeID() != null){ daoGroupe.findByID(userSave.getGroupeID()).ifPresent(p-> amb.setGroupe(p)); }


							reponse.setMessage("Ce compte a été enregistré avec succès");
							reponse.setResult(amb);
							reponse.setCode(200);

						}


				);
			}
		}
		catch (Exception e)
		{
			reponse.setCode(500);
			reponse.setMessage("Une erreur interne est survenue coté serveur  !");
		}

		return reponse ;


	}
	@Override
	public Reponse se_connecter(String email,String password)
	{
		Reponse response = new Reponse();
		try
		{
			Optional<Utilisateur>  user = userRepository.findByEmail(email);
			// apply ifPresentOrElse
			user.ifPresentOrElse(
					(userC)->
					{
						if((bCryptPasswordEncoder.matches(password, userC.getPassword())))
						{
							var userGot =modelMapper.map(userC, UserDtoResponse.class);
							if(userC.getGroupeID() != null){ daoGroupe.findByID(userC.getGroupeID()).ifPresent(p-> userGot.setGroupe(p)); }

							response.setCode(200);
							response.setMessage("La connexion a reussi !");
							response.setResult(userGot);

						}
						else
						{
							response.setCode(201);
							response.setMessage("La connexion a échoué !");
						}


					},
					()-> {
						response.setCode(201);
						response.setMessage("Ce compte n'existe pas !");
					}
			);
		}
		catch (Exception e)
		{

			response.setCode(500);
			response.setMessage("Une erreur serveur est survenue !" +e.getMessage());
		}
		return response ;
	}


	@Override
	public void initAccount() {

		List<Utilisateur> users=  userRepository.findAll();
		boolean arouna =false;

		if(users.size() != 0)
		{
			for (int i = 0; i < users.size(); i++) {
				switch (users.get(i).getTelephone()) {
				case "775073511":
					arouna=true;

					break;

				default:
					break;
				}
			}	
		}

		if(arouna == false) {

			UserDtoRequest admin =new UserDtoRequest();
			admin.setTelephone("775073511");
			admin.setEmail("sanouarouna90@gmail.com");
			admin.setPassword("1234");
			login_up(admin);
		}


	}

	@Override
	public Reponse getUserById(UUID id) {
		Reponse reponse = new Reponse();
		try
		{
			Optional<Utilisateur> user = userRepository.findById(id);
			user.ifPresentOrElse(
					(userP)->{

							var userDtoResponse=modelMapper.map(userP, UserDtoResponse.class);
						if(userP.getGroupeID() != null){ daoGroupe.findByID(userP.getGroupeID()).ifPresent(p-> userDtoResponse.setGroupe(p)); }

						reponse.setResult(userDtoResponse);
							reponse.setMessage("Ce compte a été retrouvé avec succès");
							reponse.setCode(200);


					},
					()->{reponse.setMessage("Ce compte n'existe pas");
						reponse.setCode(201);}
			);



		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue");
			reponse.setCode(500);

		} 

		return reponse;
	}

	@Override
	public Reponse disableUserById(UUID id)
	{

		Reponse reponse = new Reponse();
		try
		{
			Optional<Utilisateur> user = userRepository.findById(id);

			if(user.isPresent())
			{
				user.get().setStatus(!user.get().isStatus());
				Utilisateur userSave=userRepository.save(user.get());
				UserDtoResponse	userDtoResponse =modelMapper.map(userSave, UserDtoResponse.class);
				if(userSave.getGroupeID() != null){ daoGroupe.findByID(userSave.getGroupeID()).ifPresent(p-> userDtoResponse.setGroupe(p)); }
				reponse.setResult(userDtoResponse);

				reponse.setMessage("Ce compte a été bloqué avec succès");
				reponse.setCode(200);

			}
			else
			{
				reponse.setMessage("Ce compte n'existe pas");
				reponse.setCode(201);

			}


		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue");
			reponse.setCode(500);

		}

		return reponse;
	}


	@Override
	public Reponse getUserByEmail(String email) {
		Reponse reponse = new Reponse();
		try
		{
			Optional<Utilisateur> user = userRepository.findByEmail(email);
			user.ifPresentOrElse(
					(userP)->{

						var userDtoResponse=modelMapper.map(userP, UserDtoResponse.class);
						if(userP.getGroupeID() != null){ daoGroupe.findByID(userP.getGroupeID()).ifPresent(p-> userDtoResponse.setGroupe(p)); }

						reponse.setResult(userDtoResponse);
						reponse.setMessage("Ce compte a été retrouvé avec succès");
						reponse.setCode(200);


					},
					()->{reponse.setMessage("Ce compte n'existe pas");
						reponse.setCode(201);}
			);



		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue");
			reponse.setCode(500);

		}

		return reponse;
	}
	@Override
	public Reponse getAllUsers() {
		
		Reponse reponse = new Reponse();
		try
		{
			
		  List< UserDtoResponse> users= userRepository.findByStatus(true)
														.stream()
														.map(p->
																{
																	UserDtoResponse	userDtoResponse=modelMapper.map(p,UserDtoResponse.class);
																	if(p.getGroupeID() != null){ daoGroupe.findByID(p.getGroupeID()).ifPresent(v-> userDtoResponse.setGroupe(v)); }
                                                                    return  userDtoResponse;
																}




																				).collect(Collectors.toList());
														
		        reponse.setResult(users);
				reponse.setMessage("Liste des utilisareurs !");
				reponse.setCode(200);

			

		}
		catch (Exception e) {
			reponse.setMessage("Une erreur interne est survenue" +e.getMessage());
			reponse.setCode(500);

		}

		return reponse;
	}

	@Override
	public Reponse changePassword(ChangePasswordDtoRequest changePasswordDtoRequest) {

		Reponse response = new Reponse();
		try
		{
			Optional<Utilisateur>  user = userRepository.findById(changePasswordDtoRequest.getIdUser());
			if(user.isPresent())
			{	
				
					userRepository.save(user.get());
					response.setCode(200);
					response.setMessage("Le mot de passe a été modifié avec succès : !");
					response.setResult(changePasswordDtoRequest.getNewPassword());

				
				
			}
			else
			{
				response.setCode(201);
				response.setMessage("Ce compte n'existe pas  !");

			}


		}
		catch (Exception e)
		{
			
			response.setCode(500);
			response.setMessage("Une erreur serveur est survenue !");
		}    


		return response ;
	
	}


}

