package com.gestion_ecole.ecole.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.modelmapper.ModelMapper;

import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.dto.request.ParentDtoRequest;
import com.gestion_ecole.ecole.dto.request.PersonnalDtoRequest;
import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.dto.request.StudentDtoRequest;
import com.gestion_ecole.ecole.dto.request.TeacherDtoRequest;
import com.gestion_ecole.ecole.dto.request.UserDtoRequest;
import com.gestion_ecole.ecole.dto.response.ClasseDtoResponse;
import com.gestion_ecole.ecole.dto.response.DepartementDtoResponse;
import com.gestion_ecole.ecole.dto.response.FiliereDtoResponse;
import com.gestion_ecole.ecole.dto.response.ParentDtoResponse;
import com.gestion_ecole.ecole.dto.response.PersonnalDtoResponse;
import com.gestion_ecole.ecole.dto.response.SeanceDtoResponse;
import com.gestion_ecole.ecole.dto.response.StudentDtoResponse;
import com.gestion_ecole.ecole.dto.response.TeacherDtoResponse;
import com.gestion_ecole.ecole.dto.response.UserDtoResponse;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Parent;
import com.gestion_ecole.ecole.entities.Personnal;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.Teacher;
import com.gestion_ecole.ecole.entities.User;

import net.bytebuddy.utility.RandomString;

public final class Utility {
	// CONSTANT POUR LA SECURITE
	public static final long EXPIRATION_TIME =365 * 24 * 60 * 60 ;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SECRET = "javainuse";
	public static final String NOTREEMAIL ="je.reserve@connecsen.com";
	public static final String dev_phone_number ="221774602901";

	// LES URLS D'ACCESS
	public static final String ADD_INDISPONIBILITE = "/indisponibilite/add";
	public static final String ADD_INDISPONIBILITE_TO_User = "/indisponibilite/add/User/{id}";
	public static final String UPDATE_INDISPONIBILITE = "/indisponibilite/update";
	public static final String GET_INDISPONIBILITE_BY_ID = "/indisponibilite/indisponibilites/{id}";
	public static final String DELETE_INDISPONIBILITE_BY_ID = "/indisponibilite/indisponibilites/delete/{id}";
	public static final String GET_ALL_INDISPONIBILITES = "/indisponibilite/indisponibilites";
	
	
	public static final String ADD_MULTIMEDIA = "/multimedia/add";
	public static final String UPDATE_MULTIMEDIA = "/multimedia/update";
	public static final String GET_MULTIMEDIA_BY_ID = "/multimedia/multimedias/{id}";
	public static final String DELETE_MULTIMEDIA_BY_ID = "/multimedia/multimedias/delete/{id}";
	public static final String GET_ALL_MULTIMEDIAS = "/multimedia/multimedias";
	
	public static final String ADD_TICKET = "/ticket/add";
	public static final String UPDATE_TICKET = "/ticket/update";
	public static final String UPDATE_TICKET_PAYE = "/ticket/update/paye/{id}";
	public static final String GET_TICKET_BY_ID = "/ticket/tickets/{id}";
	public static final String GET_TICKET_BY_REFERENCE = "/ticket/tickets/reference/{reference}";
	public static final String DELETE_TICKET_BY_ID = "/ticket/tickets/delete/{id}";
	public static final String GET_ALL_TICKETS = "/ticket/tickets";
	
	
	public static final String ADD_LIGNE = "/ligne/add";
	public static final String UPDATE_LIGNE = "/ligne/update";
	public static final String GET_LIGNE_BY_ID = "/ligne/lignes/{id}";
	public static final String DESACTIVE_LIGNE_BY_ID = "/ligne/desactive/{id}";
	public static final String ACTIVE_LIGNE_BY_ID = "/ligne/active/{id}";
	public static final String GET_ALL_LIGNES_ACTIVE = "/ligne/lignes/active";
	public static final String GET_ALL_LIGNES = "/ligne/lignes";
	public static final String ADD_VOYAGE_TO_LIGNE = "/ligne/add/voyage";
	public static final String ADD_VOYAGE_TRANCHE_TO_LIGNE = "/ligne/add/tranche/voyage";
	public static final String DESACTIVE_VOYAGE_TO_LIGNE = "/ligne/desactive/voyage/{idVoyage}";
	public static final String ACTIVE_VOYAGE_TO_LIGNE = "/ligne/active/voyage/{idVoyage}";
	public static final String GET_SATISTIQUE = "/ligne/statistique/{id}";
	public static final String POST_TICKETS_BY_INTERVALDATE = "/ligne/tickets/datedebut/datefin";

	public static final String UPDATE_VOYAGE = "/voyage/update";
	public static final String GET_VOYAGE_BY_ID = "/voyage/voyages/{id}";
	public static final String GET_ALL_VOYAGES = "/voyage/voyages";
	public static final String ADD_TICKET_TO_VOYAGE = "/voyage/add/ticket";
	public static final String ADD_TRANSACTION_TO_VOYAGE = "/voyage/add/transaction";
	public static final String RECHERCHE_VOYAGE = "/voyage/recherche";

	
	public static final String ADD_RESERVATION = "/reservation/add";
	public static final String GET_SOLDE_RESERVATION_BY_PROPRIETAIRE = "/reservation/solde/{idProprietaire}";
	public static final String UPDATE_RESERVATION = "/reservation/update";
	public static final String UPDATE_RESERVATION_BY_STATUS = "/reservation/update/status";
	public static final String GET_RESERVATION_BY_ID = "/reservation/reservations/{id}";
	public static final String DELETE_RESERVATION_BY_ID = "/reservation/reservations/delete/{id}";
	public static final String GET_ALL_RESERVATIONS = "/reservation/reservations";
	public static final String GET_ALL_RESERVATIONS_BETWEEN_DATE = "/reservation/reservations/date";
	
	public static final String ADD_PUB = "/pub/add";
	public static final String UPDATE_PUB = "/pub/update";
	public static final String GET_PUB_BY_ID = "/acceuil/pub/pubs/{id}";
	public static final String DELETE_PUB_BY_ID = "/pub/pubs/delete/{id}";
	public static final String GET_ALL_PUBS = "/acceuil/pub/pubs";
	
	public static final String ADD_TRANSACTION = "/transaction/add";
	public static final String UPDATE_TRANSACTION = "/transaction/update";
	public static final String GET_TRANSACTION_BY_ID = "/transaction/transactions/{id}";
	public static final String DELETE_TRANSACTION_BY_ID = "/transaction/transactions/delete/{id}";
	public static final String GET_ALL_TRANSACTIONS = "/transaction/transactions";
	public static final String ADD_TRANSACTION_TO_USER_AND_User = "/user/add/transaction/user/{idUser}/User/{idUser}";

	public static final String ADD_PRIX = "/prix/add";
	public static final String UPDATE_PRIX = "/prix/update/{idUser}";
	public static final String GET_PRIX_BY_ID = "/prix/prixs/{id}";
	public static final String DELETE_PRIX_BY_ID = "/prix/prixs/delete/{id}";
	public static final String GET_ALL_PRIX = "/prix/prixs";
	public static final String ADD_PRIX_TO_User = "/user/add/prix/User/{idUser}";

	
	public static final String ADD_VERSEMENT = "/versement/add";
	public static final String UPDATE_VERSEMENT = "/versement/update";
	public static final String GET_VERSEMENT_BY_ID = "/versement/versements/{id}";
	public static final String GET_VERSEMENT_BY_ID_USER = "/versement/versements/user/{id}";
	public static final String DELETE_VERSEMENT_BY_ID = "/versement/versements/delete/{id}";
	public static final String GET_ALL_VERSEMENTS = "/versement/versements";
	public static final String ADD_VERSEMENT_TO_USER = "/user/add/versement/user/{idUser}";
	
	public static final String ADD_CATEGORIE = "/categorie/add";
	public static final String UPDATE_CATEGORIE = "/categorie/update";
	public static final String GET_CATEGORIE_BY_ID = "/categorie/categories/{id}";
	public static final String DELETE_CATEGORIE_BY_ID = "/categorie/categories/delete/{id}";
	public static final String GET_ALL_CATEGORIES = "/categorie/categories";
	public static final String ADD_User_TO_CATEGORIE = "/categorie/add/User/{id}";
	public static final String ADD_SOUSCATEGORIE_TO_CATEGORIE = "/categorie/add/souscategorie/{id}";
	public static final String REMOVE_User_TO_CATEGORIE = "/categorie/remove/User/{id}";
	public static final String  REMOVE_SOUSCATEGORIE_TO_CATEGORIE = "/categorie/remove/souscategorie/{id}";

	public static final String ADD_SOUSCATEGORIE = "/souscategorie/add";
	public static final String UPDATE_SOUSCATEGORIE = "/souscategorie/update";
	public static final String GET_SOUSCATEGORIE_BY_ID = "/souscategorie/souscategories/{id}";
	public static final String DELETE_SOUSCATEGORIE_BY_ID = "/souscategorie/souscategories/delete/{id}";
	public static final String GET_ALL_SOUSCATEGORIES = "/souscategorie/souscategories";
	public static final String ADD_User_TO_SOUSCATEGORIE = "/souscategorie/{idS}/add/User/{idC}";
	public static final String REMOVE_User_TO_SOUSCATEGORIE = "/souscategorie/remove/User";
	
	public static final String ADD_FICHIER = "/fichier/add";
	public static final String UPDATE_FICHIER = "/fichier/update";
	public static final String GET_FICHIER_BY_ID = "/acceuil/fichier/fichiers/{id}";
	public static final String DELETE_FICHIER_BY_ID = "/fichier/fichiers/delete/{id}";
	public static final String GET_ALL_FICHIERS = "/acceuil/fichier/fichiers";
	
	public static final String ADD_SERVICE = "/service/add";
	public static final String UPDATE_SERVICE = "/service/update";
	public static final String GET_SERVICE_BY_ID = "/acceuil/service/services/{id}";
	public static final String DELETE_SERVICE_BY_ID = "/service/services/delete/{id}";
	public static final String GET_ALL_SERVICES = "/acceuil/service/services";
	
	public static final String ADD_PROPOS = "/propos/add";
	public static final String UPDATE_PROPOS = "/propos/update";
	public static final String GET_PROPOS_BY_ID = "/acceuil/propos/propos/{id}";
	public static final String DELETE_PROPOS_BY_ID = "/propos/propos/delete/{id}";
	public static final String GET_ALL_PROPOS = "/acceuil/propos/propos";
	
	public static final String ADD_HOMEONE = "/homeone/add";
	public static final String UPDATE_HOMEONE = "/homeone/update";
	public static final String GET_HOMEONE_BY_ID = "/acceuil/homeone/homeones/{id}";
	public static final String DELETE_HOMEONE_BY_ID = "/homeone/homeones/delete/{id}";
	public static final String GET_ALL_HOMEONES = "/acceuil/homeone/homeones";
	
	
	public static final String ADD_User = "/User/add/{id}";
	public static final String ADD_RESERVATION_TO_User = "/User/add/reservation/{id}";
	public static final String UPDATE_User = "/User/update/{id}";
	public static final String GET_User_BY_ID = "/User/Users/{id}";
	public static final String DELETE_User_BY_ID = "/User/Users/delete/{id}";
	public static final String ENABLE_User_BY_ID = "/User/Users/enable/{id}";
	public static final String GET_ALL_UserS = "/User/Users";
	public static final String GET_ALL_RESERVATIONS_BY_ID_User = "/User/reservations";
	public static final String GET_ALL_DAYS_AVAILABLE_ID_User = "/User/days";

	public static final String ADD_USER = "/user/add";
	public static final String ADD_RESERVATION_TO_USER = "/user/add/reservation/{id}";
	public static final String ADD_RESERVATION_TO_USER_AND_User = "/user/add/reservation/user/{idUser}/User/{idUser}";
	public static final String UPDATE_USER = "/user/update";
	public static final String GET_USER_BY_ID = "/user/users/{id}";
	public static final String DELETE_USER_BY_ID = "/user/users/delete/{id}";
	public static final String GET_ALL_USERS = "/user/users";

	
	public static final String ADD_ROLE = "/user/role/add";
	public static final String UPDATE_ROLE = "/user/role/update";
	public static final String GET_ROLE_BY_ID = "/user/role/roles/{id}";
	public static final String DELETE_ROLE_BY_ID = "/user/role/roles/delete/{id}";
	public static final String GET_ALL_ROLES = "/user/role/roles";
	
	public static final String DO_CONTACTED = "/acceuil/user/contacter";
	public static final String DO_REGISTER = "/user/register";
	public static final String DO_REGISTER_BY_ADMIN = "/user/register/admin";
	public static final String DO_LOGIN = "/user/login";
	public static final String DO_FORGOT_PASSWORD = "/user/forgot";
	public static final String DO_UPDATE_PASSWORD = "/user/updatePassword";
	public static final String DO_UPDATE_PASSWORD_USER = "/user/updatePassword/user";
	
	public static final String ADD_DEPARTEMENT = "/departement/add";
	public static final String UPDATE_DEPARTEMENT = "/departement/update";
	public static final String GET_DEPARTEMENT_BY_ID = "/acceuil/departement/departements/{id}";
	public static final String DELETE_DEPARTEMENT_BY_ID = "/departement/departements/delete/{id}";
	public static final String GET_ALL_DEPARTEMENT = "/acceuil/departement/departements";
	
	public static final String ADD_CLASSE = "/classe/add";
	public static final String UPDATE_CLASSE = "/classe/update";
	public static final String GET_CLASSE_BY_ID = "/acceuil/classe/classes/{id}";
	public static final String DELETE_CLASSE_BY_ID = "/classe/classes/delete/{id}";
	public static final String GET_ALL_CLASSE = "/acceuil/classe/classes";
	
	public static final String ADD_FILIERE = "/filiere/add";
	public static final String UPDATE_FILIERE = "/filiere/update";
	public static final String GET_FILIERE_BY_ID = "/acceuil/filiere/filieres/{id}";
	public static final String DELETE_FILIERE_BY_ID = "/filiere/filieres/delete/{id}";
	public static final String GET_ALL_FILIERE = "/acceuil/filiere/filieres";
	
	public static final String ADD_SEANCE = "/seance/add";
	public static final String UPDATE_SEANCE = "/seance/update";
	public static final String GET_SEANCE_BY_ID = "/acceuil/seance/seances/{id}";
	public static final String DELETE_SEANCE_BY_ID = "/seance/seances/delete/{id}";
	public static final String GET_ALL_SEANCE = "/acceuil/seance/seances";
	
	//GENERATE CALENDAR
	public static int getMonthNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
	}
	public static int getTodayNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}
	
	public static int getAnnneNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	//CHECK OBJET IS NULL
	public static boolean checkNull(Object obj) {
		return (obj !=null)? true : false;
	}
	//DATE OF TODAY
	public static String getToday() {
		Date yourDate = new Date();

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		String date = DATE_FORMAT.format(yourDate);
		return date;
	}
	//GENERATEUR DE TOKEN POUR RECUPERER LE MOT DE PASSE
	public static String getTokenResetPassword() {
		return RandomString.make(10);
	}
	 
	//LES MAPPERS DTO A DAO USER
		public static  UserDtoResponse UserConvertToUserDtoResponse(User userSave)
		{
			ModelMapper modelMapper = new ModelMapper();
			UserDtoResponse UserDtoResponse = new UserDtoResponse() ;

			if(userSave instanceof Personnal )
            {
			    return (userSave != null)? modelMapper.map(userSave, PersonnalDtoResponse.class) : UserDtoResponse;

            	
            }
            else if(userSave instanceof Parent )
            {
    		    return (userSave != null)? modelMapper.map(userSave, ParentDtoResponse.class) : UserDtoResponse;

           	}
            else if(userSave instanceof Student)
            {
    		    return (userSave != null)? modelMapper.map(userSave, StudentDtoResponse.class) : UserDtoResponse;

            }
            else 
            {
    		    return (userSave != null)? modelMapper.map(userSave, TeacherDtoResponse.class) : UserDtoResponse;

            }
			
			
		}
		
		public static  UserDtoRequest UserConvertToUserDtoRequest(User UserDtoRequest) {
			ModelMapper modelMapper = new ModelMapper(); 
			UserDtoRequest UserDtoResponse = new UserDtoRequest() ;
			if(UserDtoRequest instanceof Personnal )
            {
			    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, PersonnalDtoRequest.class) : UserDtoResponse;

            	
            }
            else if(UserDtoRequest instanceof Parent )
            {
    		    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, ParentDtoRequest.class) : UserDtoResponse;

           	}
            else if(UserDtoRequest instanceof Student)
            {
    		    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, StudentDtoRequest.class) : UserDtoResponse;

            }
            else 
            {
    		    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, TeacherDtoRequest.class) : UserDtoResponse;

            }		
			
			
		}
		
		public static  User UserDtoResponseConvertToUser(UserDtoResponse UserDtoResponse) {
			ModelMapper modelMapper = new ModelMapper(); 
			if(UserDtoResponse instanceof  PersonnalDtoResponse )
            {
			    return (UserDtoResponse != null)? modelMapper.map(UserDtoResponse, Personnal.class) :  new Personnal();

            	
            }
            else if(UserDtoResponse instanceof ParentDtoResponse )
            {
    		    return (UserDtoResponse != null)? modelMapper.map(UserDtoResponse, Parent.class) : new Parent();

           	}
            else if(UserDtoResponse instanceof StudentDtoResponse)
            {
    		    return (UserDtoResponse != null)? modelMapper.map(UserDtoResponse, Student.class) : new Student();

            }
            else 
            {
    		    return (UserDtoResponse != null)? modelMapper.map(UserDtoResponse, Teacher.class) : new Teacher();

            }
			
		}
		public static User UserDtoRequestConvertToUser(UserDtoRequest UserDtoRequest)  {
			ModelMapper modelMapper = new ModelMapper();
			if(UserDtoRequest instanceof PersonnalDtoRequest )
            {
			    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, Personnal.class) : new Personnal();

            	
            }
            else if(UserDtoRequest instanceof ParentDtoRequest )
            {
    		    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, Parent.class) : new Parent();

           	}
            else if(UserDtoRequest instanceof StudentDtoRequest)
            {
    		    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, Student.class) : new Student();

            }
            else 
            {
    		    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, Teacher.class) : new Teacher();

            }
			
		}
		
		//LES MAPPERS Departement
		static 	ModelMapper modelMapper = new ModelMapper();
		
	    public static  DepartementDtoRequest toDtoDepartementDtoRequest(Departement departement) 
	    {
	    	
		return modelMapper.map(departement, DepartementDtoRequest.class);
	    }
	    
	    public static  DepartementDtoResponse toDtoDepartementDtoResponse(Departement departement) 
	    {
	    	
		return modelMapper.map(departement, DepartementDtoResponse.class);
	    }
	    
		public static  Departement toEntityDepartementFromRequest(DepartementDtoRequest departementDtoRequest) 
		{
		return modelMapper.map(departementDtoRequest, Departement.class);
			
		}	
		
		public static  Departement toEntityDepartementFromResponse(DepartementDtoRequest departementDtoRequest) 
		{
		return modelMapper.map(departementDtoRequest, Departement.class);
			
		}	
		
		//LES MAPPERS classe
				
			    public static  ClasseDtoResponse toClasseDtoResponse(Classe classe) 
			    {
			    	
				return modelMapper.map(classe, ClasseDtoResponse.class);
			    }
			    
			    public static Classe toClasse(ClasseDtoRequest classeDto) {
			    	return modelMapper.map(classeDto, Classe.class);
					
				}
			    
		//Les mappers Filiere
			    public static Filiere toFiliere(FiliereDtoRequest filiereDtoRequest) {
			    	return modelMapper.map(filiereDtoRequest, Filiere.class);
			    }
			    public static FiliereDtoResponse toFiliereDtoResponse (Filiere filiere) {
			    	return modelMapper.map(filiere, FiliereDtoResponse.class);
			    }
		
		// Les mappers Seance
			    public static Seance toSeance(SeanceDtoRequest seanceDtoRequest) {
			    	return modelMapper.map(seanceDtoRequest, Seance.class);
			    }
			    public static SeanceDtoResponse toSeanceDtoResponse(Seance seance) {
			    	return modelMapper.map(seance, SeanceDtoResponse.class);
			    }
		
}
