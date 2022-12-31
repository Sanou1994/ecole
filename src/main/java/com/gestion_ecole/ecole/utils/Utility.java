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
import com.gestion_ecole.ecole.entities.Sceance;
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

	
	public static final String ADD_USER = "/user/add";
	public static final String UPDATE_USER = "/user/update";
	public static final String GET_USER_BY_ID = "/user/users/{id}";
	public static final String DELETE_USER_BY_ID = "/user/users/delete/{id}";
	public static final String GET_ALL_USERS = "/user/users";

	
		
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
	static 	ModelMapper modelMapper = new ModelMapper();

	//LES MAPPERS DTO A DAO USER
		public static  UserDtoResponse UserConvertToUserDtoResponse(User userSave)
		{
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
		
		//LES MAPPERS DEPARTEMENT
		
	    public static  DepartementDtoRequest toDtoDepartementDtoRequest(Departement departement) 
	    {
	    	DepartementDtoRequest departementDtoRequest = new DepartementDtoRequest() ;

		    return(departementDtoRequest != null)? modelMapper.map(departement, DepartementDtoRequest.class) :departementDtoRequest ;
	    }
	    
	    public static  DepartementDtoResponse toDtoDepartementDtoResponse(Departement departement) 
	    {
	    	DepartementDtoResponse departementDtoResponse = new DepartementDtoResponse() ;

		return (departementDtoResponse != null)? modelMapper.map(departement, DepartementDtoResponse.class):departementDtoResponse;
	    }
	    
		public static  Departement toEntityDepartementFromRequest(DepartementDtoRequest departementDtoRequest) 
		{
		  return (departementDtoRequest != null) ? modelMapper.map(departementDtoRequest, Departement.class):new Departement();
			
		}	
		
		public static  Departement toEntityDepartementFromResponse(DepartementDtoResponse departementDtoResponse) 
		{
		 return(departementDtoResponse != null) ? modelMapper.map(departementDtoResponse, Departement.class):new Departement();
			
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
			    public static Sceance toSceance(SeanceDtoRequest seanceDtoRequest) {
			    	return modelMapper.map(seanceDtoRequest, Sceance.class);
			    }
			    public static SeanceDtoResponse toSceanceDtoResponse(Sceance seance) {
			    	return modelMapper.map(seance, SeanceDtoResponse.class);
			    }
		
}
