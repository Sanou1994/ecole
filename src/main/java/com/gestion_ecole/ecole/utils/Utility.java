package com.gestion_ecole.ecole.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.modelmapper.ModelMapper;

import com.gestion_ecole.ecole.dto.request.AbsenceDtoRequest;
import com.gestion_ecole.ecole.dto.request.CahierDeTexteDtoRequest;
import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.dto.request.EmploiDuTempsDtoRequest;
import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.dto.request.InscriptionDtoRequest;
import com.gestion_ecole.ecole.dto.request.ParentDtoRequest;
import com.gestion_ecole.ecole.dto.request.PersonnalDtoRequest;
import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.dto.request.StudentDtoRequest;
import com.gestion_ecole.ecole.dto.request.TeacherDtoRequest;
import com.gestion_ecole.ecole.dto.request.UserDtoRequest;
import com.gestion_ecole.ecole.dto.response.AbsenceDtoResponse;
import com.gestion_ecole.ecole.dto.response.CahierDeTexteDtoResponse;
import com.gestion_ecole.ecole.dto.response.ClasseDtoResponse;
import com.gestion_ecole.ecole.dto.response.DepartementDtoResponse;
import com.gestion_ecole.ecole.dto.response.EmploiDuTempsDtoResponse;
import com.gestion_ecole.ecole.dto.response.FiliereDtoResponse;
import com.gestion_ecole.ecole.dto.response.InscriptionDtoResponse;
import com.gestion_ecole.ecole.dto.response.ParentDtoResponse;
import com.gestion_ecole.ecole.dto.response.PersonnalDtoResponse;
import com.gestion_ecole.ecole.dto.response.SeanceDtoResponse;
import com.gestion_ecole.ecole.dto.response.StudentDtoResponse;
import com.gestion_ecole.ecole.dto.response.TeacherDtoResponse;
import com.gestion_ecole.ecole.dto.response.UserDtoResponse;
import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.CahierDeTexte;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.EmploiDuTemps;
import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Inscription;
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

	
	public static final String ADD_USER = "/user/add";
	public static final String UPDATE_USER = "/user/update";
	public static final String GET_USER_BY_ID = "/user/users/{id}";
	public static final String DELETE_USER_BY_ID = "/user/users/delete/{id}";
	public static final String GET_ALL_USERS = "/user/users/type/{type}";

	
		
	public static final String DO_CONTACTED = "/acceuil/user/contacter";
	public static final String DO_REGISTER = "/user/register";
	public static final String DO_REGISTER_BY_ADMIN = "/user/register/admin";
	public static final String DO_LOGIN = "/user/login";
	public static final String DO_ACTIVATION = "/user/activation";

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
	
	public static final String ADD_ABSENCE = "/absence/add";
	public static final String UPDATE_ABSENCE = "/absence/update";
	public static final String GET_ABSENCE_BY_ID = "/acceuil/absence/absences/{id}";
	public static final String DELETE_ABSENCE_BY_ID = "/absence/absences/delete/{id}";
	public static final String GET_ALL_ABSENCE = "/acceuil/absence/absences";
	
	public static final String ADD_CAHIERDETEXTE = "/cahierDeTexte/add";
	public static final String UPDATE_CAHIERDETEXTE = "/cahierDeTexte/update";
	public static final String GET_CAHIERDETEXTE_BY_ID = "/acceuil/cahierDeTexte/cahierDeTextes/{id}";
	public static final String DELETE_CAHIERDETEXTE_BY_ID = "/cahierDeTexte/cahierDeTextes/delete/{id}";
	public static final String GET_ALL_CAHIERDETEXTE = "/acceuil/cahierDeTexte/cahierDeTextes";
	
	public static final String ADD_EMPLOIDUTEMPS = "/emploiDuTemps/add";
	public static final String UPDATE_EMPLOIDUTEMPS = "/emploiDuTemps/update";
	public static final String GET_EMPLOIDUTEMPS_BY_ID = "/acceuil/emploiDuTemps/emploiDuTemps/{id}";
	public static final String DELETE_EMPLOIDUTEMPS_BY_ID = "/emploiDuTemps/emploiDuTemps/delete/{id}";
	public static final String GET_ALL_EMPLOIDUTEMPS = "/acceuil/emploiDuTemps/emploiDuTemps";
	
	public static final String ADD_INSCRIPTION = "/inscription/add";
	public static final String UPDATE_INSCRIPTION = "/inscription/update";
	public static final String GET_INSCRIPTION_BY_ID = "/acceuil/inscription/inscriptions/{id}";
	public static final String DELETE_INSCRIPTION_BY_ID = "/inscription/incriptions/delete/{id}";
	public static final String GET_ALL_INSCRIPTION = "/acceuil/inscription/inscriptions";
	
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
			if(UserDtoRequest.getTypeUser().equals("PERSONNAL") )
            {
			    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, Personnal.class) : new Personnal();

            	
            }
            else if(UserDtoRequest.getTypeUser().equals("PARENT"))
            {
    		    return (UserDtoRequest != null)? modelMapper.map(UserDtoRequest, Parent.class) : new Parent();

           	}
            else if(UserDtoRequest.getTypeUser().equals("STUDENT"))
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
			Departement departement = new Departement();
		  return (departement != null) ? modelMapper.map(departementDtoRequest, Departement.class):new Departement();
			
		}	
		
		public static  Departement toEntityDepartementFromResponse(DepartementDtoResponse departementDtoResponse) 
		{
		 return(departementDtoResponse != null) ? modelMapper.map(departementDtoResponse, Departement.class):new Departement();
			
		}	
		
		//LES MAPPERS classe
				
			    public static  ClasseDtoResponse toClasseDtoResponse(Classe classe) 
			    {
			    	ClasseDtoResponse classeDtoResponse = new ClasseDtoResponse();
				return (classeDtoResponse!=null)?modelMapper.map(classe, ClasseDtoResponse.class):classeDtoResponse;
			    }
			    
			    public static Classe toClasse(ClasseDtoRequest classeDto) {
			    	Classe classe = new Classe();
			    	return (classe!=null)?modelMapper.map(classeDto, Classe.class):classe;
					
				}
			    
		//Les mappers Filiere
			    public static Filiere toFiliere(FiliereDtoRequest filiereDtoRequest) {
			    	Filiere filiere = new Filiere();
			    	return(filiere!=null)? modelMapper.map(filiereDtoRequest, Filiere.class):filiere;
			    }
			    public static FiliereDtoResponse toFiliereDtoResponse (Filiere filiere) {
			    	FiliereDtoResponse filiereDtoResponse = new FiliereDtoResponse();
			    	return(filiere !=null)? modelMapper.map(filiere, FiliereDtoResponse.class):filiereDtoResponse;
			    }
		
		// Les mappers Seance

			    public static Seance toSeance(SeanceDtoRequest seanceDtoRequest) {
			    	Seance seance = new Seance();
			    	return (seanceDtoRequest!=null)?modelMapper.map(seanceDtoRequest, Seance.class):seance;
			    }

			    public static SeanceDtoResponse toSceanceDtoResponse(Seance seance) {
			    	SeanceDtoResponse seanceDtoResponse = new SeanceDtoResponse();
			    	return (seance!=null)? modelMapper.map(seance, SeanceDtoResponse.class):seanceDtoResponse;
			    }
			    
			 // Les mappers Absence

			    public static Absence toAbsence(AbsenceDtoRequest absenceDtoRequest) {
			    	Absence absence = new Absence();
			    	return (absenceDtoRequest!=null)?modelMapper.map(absenceDtoRequest, Absence.class):absence;
			    }

			    public static AbsenceDtoResponse toAbsenceDtoResponse(Absence absence) {
			    	AbsenceDtoResponse absenceDtoResponse = new AbsenceDtoResponse();
			    	return (absence!=null)? modelMapper.map(absence, AbsenceDtoResponse.class):absenceDtoResponse;
			    }
			    
			    // Les mappers CahierDeTexte

			    public static CahierDeTexte toCahierDeTexte(CahierDeTexteDtoRequest cahierDeTexteDtoRequest) {
			    	CahierDeTexte cahierDeTexte = new CahierDeTexte();
			    	return (cahierDeTexteDtoRequest!=null)?modelMapper.map(cahierDeTexteDtoRequest, CahierDeTexte.class):cahierDeTexte;
			    }

			    public static CahierDeTexteDtoResponse toCahierDeTexteDtoResponse(CahierDeTexte cahierDeTexte) {
			    	CahierDeTexteDtoResponse cahierDeTexteDtoResponse = new CahierDeTexteDtoResponse();
			    	return (cahierDeTexte!=null)? modelMapper.map(cahierDeTexte, CahierDeTexteDtoResponse.class):cahierDeTexteDtoResponse;
			    }
			    
			    // Les mappers EmploiDuTemps

			    public static EmploiDuTemps toEmploiDuTemps(EmploiDuTempsDtoRequest emploiDuTempsDtoRequest) {
			    	EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
			    	return (emploiDuTempsDtoRequest!=null)?modelMapper.map(emploiDuTempsDtoRequest, EmploiDuTemps.class):emploiDuTemps;
			    }

			    public static EmploiDuTempsDtoResponse toEmploiDuTempsDtoResponse(EmploiDuTemps emploiDuTemps) {
			    	EmploiDuTempsDtoResponse emploiDuTempsDtoResponse = new EmploiDuTempsDtoResponse();
			    	return (emploiDuTemps!=null)? modelMapper.map(emploiDuTemps, EmploiDuTempsDtoResponse.class):emploiDuTempsDtoResponse;
			    }
			    
			    // Les mappers Inscription

			    public static Inscription toInscription(InscriptionDtoRequest inscriptionDtoRequest) {
			    	Inscription inscription = new Inscription();
			    	return (inscriptionDtoRequest!=null)?modelMapper.map(inscriptionDtoRequest, Inscription.class):inscription;
			    }

			    public static InscriptionDtoResponse toInscriptionDtoResponse(Inscription inscription) {
			    	InscriptionDtoResponse inscriptionDtoResponse = new InscriptionDtoResponse();
			    	return (inscription!=null)? modelMapper.map(inscription, InscriptionDtoResponse.class):inscriptionDtoResponse;
			    }
		
}
