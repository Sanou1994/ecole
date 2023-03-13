package com.gestion_ecole.ecole.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.modelmapper.ModelMapper;

import com.gestion_ecole.ecole.dto.request.AbsenceDtoRequest;
import com.gestion_ecole.ecole.dto.request.AnneeScolaireDtoRequest;
import com.gestion_ecole.ecole.dto.request.Ass_prinscription_studentDtoRequest;
import com.gestion_ecole.ecole.dto.request.CahierDeTexteDtoRequest;
import com.gestion_ecole.ecole.dto.request.ClasseDtoRequest;
import com.gestion_ecole.ecole.dto.request.ContratDtoRequest;
import com.gestion_ecole.ecole.dto.request.DepartementDtoRequest;
import com.gestion_ecole.ecole.dto.request.EmploiDuTempsDtoRequest;
import com.gestion_ecole.ecole.dto.request.FiliereDtoRequest;
import com.gestion_ecole.ecole.dto.request.InscriptionDtoRequest;
import com.gestion_ecole.ecole.dto.request.LancerPreinscriptionDtoRequest;
import com.gestion_ecole.ecole.dto.request.NiveauEtudeDtoRequest;
import com.gestion_ecole.ecole.dto.request.ParentDtoRequest;
import com.gestion_ecole.ecole.dto.request.PersonnalDtoRequest;
import com.gestion_ecole.ecole.dto.request.PosteDtoRequest;
import com.gestion_ecole.ecole.dto.request.SeanceDtoRequest;
import com.gestion_ecole.ecole.dto.request.StudentDtoRequest;
import com.gestion_ecole.ecole.dto.request.SupportPhysiqueDtoRequest;
import com.gestion_ecole.ecole.dto.request.TeacherDtoRequest;
import com.gestion_ecole.ecole.dto.request.UserDtoRequest;
import com.gestion_ecole.ecole.dto.response.AbsenceDtoResponse;
import com.gestion_ecole.ecole.dto.response.AnneeScolaireDtoResponse;
import com.gestion_ecole.ecole.dto.response.Ass_prinscription_studentDtoResponse;
import com.gestion_ecole.ecole.dto.response.CahierDeTexteDtoResponse;
import com.gestion_ecole.ecole.dto.response.ClasseDtoResponse;
import com.gestion_ecole.ecole.dto.response.ContratDtoResponse;
import com.gestion_ecole.ecole.dto.response.DepartementDtoResponse;
import com.gestion_ecole.ecole.dto.response.EmploiDuTempsDtoResponse;
import com.gestion_ecole.ecole.dto.response.FiliereDtoResponse;
import com.gestion_ecole.ecole.dto.response.InscriptionDtoResponse;
import com.gestion_ecole.ecole.dto.response.LancerPreinscriptionDtoResponse;
import com.gestion_ecole.ecole.dto.response.NiveauEtudeDtoResponse;
import com.gestion_ecole.ecole.dto.response.ParentDtoResponse;
import com.gestion_ecole.ecole.dto.response.PersonnalDtoResponse;
import com.gestion_ecole.ecole.dto.response.PosteDtoResponse;
import com.gestion_ecole.ecole.dto.response.SeanceDtoResponse;
import com.gestion_ecole.ecole.dto.response.StudentDtoResponse;
import com.gestion_ecole.ecole.dto.response.SupportPhysiqueDtoResponse;
import com.gestion_ecole.ecole.dto.response.TeacherDtoResponse;
import com.gestion_ecole.ecole.dto.response.UserDtoResponse;
import com.gestion_ecole.ecole.entities.Absence;
import com.gestion_ecole.ecole.entities.AnneeScolaire;
import com.gestion_ecole.ecole.entities.Ass_prinscription_student;
import com.gestion_ecole.ecole.entities.CahierDeTexte;
import com.gestion_ecole.ecole.entities.Classe;
import com.gestion_ecole.ecole.entities.Contrat;
import com.gestion_ecole.ecole.entities.Departement;
import com.gestion_ecole.ecole.entities.EmploiDuTemps;
import com.gestion_ecole.ecole.entities.Filiere;
import com.gestion_ecole.ecole.entities.Inscription;
import com.gestion_ecole.ecole.entities.LancerPreinscription;
import com.gestion_ecole.ecole.entities.Niveau_etude;
import com.gestion_ecole.ecole.entities.Parent;
import com.gestion_ecole.ecole.entities.Personnal;
import com.gestion_ecole.ecole.entities.Poste;
import com.gestion_ecole.ecole.entities.Seance;
import com.gestion_ecole.ecole.entities.Student;
import com.gestion_ecole.ecole.entities.SupportPysique;
import com.gestion_ecole.ecole.entities.Teacher;
import com.gestion_ecole.ecole.entities.User;

import net.bytebuddy.utility.RandomString;

public final class Utility 
{
	
	// CONSTANT POUR LA SECURITE
	public static final long EXPIRATION_TIME =365 * 24 * 60 * 60 ;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SECRET = "javainuse";
	public static final String NOTREEMAIL ="je.reserve@connecsen.com";
	public static final String dev_phone_number ="221774602901";

	public static final String SUBJECT = "Indentifiants";
	public static final String LINK = "Indentifiants";
	public static final String ATTACHEMENT = "Pluto inscription ";

	
	public static final String PERSONNAL = "PERSONNAL";
	public static final String PARENT = "PARENT";
	public static final String STUDENT = "STUDENT";
	public static final String TEACHER = "TEACHER";
	
	public static final String FILE_PHOTO = "PHOTO";
	public static final String FILE_PIECE = "PIECE";
	public static final String FILE_TP = "TP"; 
	public static final String FILE_TD = "TD";
	public static final String FILE_EVALUATION = "EVALUATION";

	
	public static final String ADD_PERSONNAL = "/user/personnal";
	public static final String ADD_PARENT = "/user/parent";
	public static final String ADD_STUDENT = "/user/student";
	public static final String ADD_TEACHER = "/user/teacher";

	
	
	public static final String GET_USER_BY_ID = "/user/users/type/{type}/id/{id}";
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
	
	public static final String ADD_DEPARTEMENT = "/departements/add";
	public static final String GET_DEPARTEMENT_BY_ID = "/departements/{id}";
	public static final String DELETE_DEPARTEMENT_BY_ID = "/departements/delete/{id}";
	public static final String GET_ALL_DEPARTEMENTS = "/departements/structure/{id}";
	
	public static final String ADD_LANCEMENT_PREINSCRIPTION = "/lancerpreinscriptions/add";
	public static final String GET_LANCEMENT_PREINSCRIPTION_BY_ID = "/lancerpreinscriptions/{id}";
	public static final String DELETE_LANCEMENT_PREINSCRIPTION_BY_ID = "/lancerpreinscriptions/delete/{id}";
	public static final String GET_ALL_LANCEMENT_PREINSCRIPTIONS = "/lancerpreinscriptions/structure/{id}";
	
	
	public static final String ADD_ANNEE_SCOLAIRE = "/anneescolaires/add";
	public static final String GET_ANNEE_SCOLAIRE_BY_ID = "/danneescolaires/{id}";
	public static final String DELETE_ANNEE_SCOLAIRE_BY_ID = "/anneescolaires/delete/{id}";
	public static final String GET_ALL_ANNEE_SCOLAIRES = "/anneescolaires/structure/{id}";

	
	public static final String ADD_NIVEAU_ETUDE = "/niveauetudes/add";
	public static final String GET_NIVEAU_ETUDE_BY_ID = "/niveauetudes/{id}";
	public static final String DELETE_NIVEAU_ETUDE_BY_ID = "/niveauetudes/delete/{id}";
	public static final String GET_ALL_NIVEAU_ETUDES = "/niveauetudes/structure/{id}";
	
	
	
	public static final String ADD_POSTE = "/postes/add";
	public static final String GET_POSTE_BY_ID = "/postes/{id}";
	public static final String DELETE_POSTE_BY_ID = "/postes/delete/{id}";
	public static final String GET_ALL_POSTE = "/postes/structure/{id}";

	public static final String ADD_CONTRAT = "/contrats/add";
	public static final String GET_CONTRAT_BY_ID = "/contrats/{id}";
	public static final String DELETE_CONTRAT_BY_ID = "/contrats/delete/{id}";
	public static final String GET_ALL_CONTRAT = "/contrats/structure/{id}";

	
	public static final String ADD_CLASSE = "/classes/add";
	public static final String UPDATE_CLASSE = "/classes/update";
	public static final String GET_CLASSE_BY_ID = "/classe/classes/{id}";
	public static final String DELETE_CLASSE_BY_ID = "/classe/classes/delete/{id}";
	public static final String GET_ALL_CLASSE = "/classes/structure/{id}";
	
	public static final String ADD_FILIERE = "/filieres/add";
	public static final String GET_FILIERE_BY_ID = "/filieres/{id}";
	public static final String DELETE_FILIERE_BY_ID = "/filieres/delete/{id}";
	public static final String GET_ALL_FILIERE = "/filieres/structure/{id}";
	
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
	
	public static final String UPLOAD_FILE = "/file/add";
	public static final String GET_PHOTO = "/photo/{name}"; 

	
	
	
	
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
	
	
	public static String genererMatricule(long idUser, long idStructure)
    {
		String annee =Integer.toString(getAnnneNumber(new Date())) ;
		String idUserS = Long.toString(idUser) ;
		String idStructureS = Long.toString(idStructure) ; 
		String matricule = "E"+ annee + idUserS+ idStructureS;
        return matricule;
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
		
		public static  UserDtoRequest UserConvertToUserDtoRequest(User userDtoRequest) {
			UserDtoRequest UserDtoResponse = new UserDtoRequest() ;
			if(userDtoRequest instanceof Personnal)
            {
			    return (userDtoRequest != null)? modelMapper.map(userDtoRequest, PersonnalDtoRequest.class) : UserDtoResponse;

            	
            }
            else if(userDtoRequest instanceof Parent )
            {
    		    return (userDtoRequest != null)? modelMapper.map(userDtoRequest, ParentDtoRequest.class) : UserDtoResponse;

           	}
            else if(userDtoRequest instanceof Student)
            {
    		    return (userDtoRequest != null)? modelMapper.map(userDtoRequest, StudentDtoRequest.class) : UserDtoResponse;

            }
            else 
            {
    		    return (userDtoRequest != null)? modelMapper.map(userDtoRequest, TeacherDtoRequest.class) : UserDtoResponse;

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
      //LES MAPPERS ANNEE SCOLAIRE
		
	    public static  AnneeScolaireDtoRequest toDtoAnneeScolaireDtoRequest(AnneeScolaire departement) 
	    {
	    	AnneeScolaireDtoRequest departementDtoRequest = new AnneeScolaireDtoRequest() ;

		    return(departement != null)? modelMapper.map(departement, AnneeScolaireDtoRequest.class) :departementDtoRequest ;
	    }
	    
	    public static  AnneeScolaireDtoResponse toDtoAnneeScolaireDtoResponse(AnneeScolaire departement) 
	    {
	    	AnneeScolaireDtoResponse departementDtoResponse = new AnneeScolaireDtoResponse() ;

		return (departement != null)? modelMapper.map(departement, AnneeScolaireDtoResponse.class):departementDtoResponse;
	    }
	    
		public static  AnneeScolaire toEntityAnneeScolaireFromRequest(AnneeScolaireDtoRequest departementDtoRequest) 
		{
		  return (departementDtoRequest != null) ? modelMapper.map(departementDtoRequest, AnneeScolaire.class):new AnneeScolaire();
			
		}	
		
		public static  AnneeScolaire toEntityAnneeScolaireFromResponse(AnneeScolaireDtoResponse departementDtoResponse) 
		{
		 return(departementDtoResponse != null) ? modelMapper.map(departementDtoResponse, AnneeScolaire.class):new AnneeScolaire();
			
		}
		
		
       //LES MAPPERS Ass_prinscription
		
	    public static  Ass_prinscription_studentDtoRequest toDtoAss_prinscription_studentDtoRequest(Ass_prinscription_student ass_prinscription_student) 
	    {
	    	Ass_prinscription_studentDtoRequest ass_prinscription_studentDtoRequest = new Ass_prinscription_studentDtoRequest() ;

		    return(ass_prinscription_student != null)? modelMapper.map(ass_prinscription_student, Ass_prinscription_studentDtoRequest.class) :ass_prinscription_studentDtoRequest ;
	    }
	    
	    public static  Ass_prinscription_studentDtoResponse toDtoAss_prinscription_studentDtoResponse(Ass_prinscription_student ass_prinscription_student) 
	    {
	    	Ass_prinscription_studentDtoResponse ass_prinscription_studentDtoResponse = new Ass_prinscription_studentDtoResponse() ;

		return (ass_prinscription_student != null)? modelMapper.map(ass_prinscription_student, Ass_prinscription_studentDtoResponse.class):ass_prinscription_studentDtoResponse;
	    }
	    
		public static  Ass_prinscription_student toEntityAss_prinscription_studentFromRequest(Ass_prinscription_studentDtoRequest ass_prinscription_studentDtoRequest) 
		{
		  return (ass_prinscription_studentDtoRequest != null) ? modelMapper.map(ass_prinscription_studentDtoRequest, Ass_prinscription_student.class):new Ass_prinscription_student();
			
		}	
		
		public static  Ass_prinscription_student toEntityAss_prinscription_studentFromResponse(Ass_prinscription_studentDtoResponse departementDtoResponse) 
		{
		 return(departementDtoResponse != null) ? modelMapper.map(departementDtoResponse, Ass_prinscription_student.class):new Ass_prinscription_student();
			
		}
		
		
      //LES MAPPERS LANCERDEPARTEMEN
		
	    public static  LancerPreinscriptionDtoRequest toDtoLancerPreinscriptionDtoRequest(LancerPreinscription departement) 
	    {
	    	LancerPreinscriptionDtoRequest departementDtoRequest = new LancerPreinscriptionDtoRequest() ;

		    return(departementDtoRequest != null)? modelMapper.map(departement, LancerPreinscriptionDtoRequest.class) :departementDtoRequest ;
	    }
	    
	    public static  LancerPreinscriptionDtoResponse toDtoLancerPreinscriptionDtoResponse(LancerPreinscription departement) 
	    {
	    	LancerPreinscriptionDtoResponse departementDtoResponse = new LancerPreinscriptionDtoResponse() ;

		return (departementDtoResponse != null)? modelMapper.map(departement, LancerPreinscriptionDtoResponse.class):departementDtoResponse;
	    }
	    
		public static  LancerPreinscription toEntityLancerPreinscriptionFromRequest(LancerPreinscriptionDtoRequest departementDtoRequest) 
		{
			LancerPreinscription departement = new LancerPreinscription();
		  return (departement != null) ? modelMapper.map(departementDtoRequest, LancerPreinscription.class):new LancerPreinscription();
			
		}	
		
		public static  LancerPreinscription toEntityLancerPreinscriptionFromResponse(LancerPreinscriptionDtoResponse departementDtoResponse) 
		{
		 return(departementDtoResponse != null) ? modelMapper.map(departementDtoResponse, LancerPreinscription.class):new LancerPreinscription();
			
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
		
       //LES MAPPERS NIVEAU ETUDE
		
	    public static  NiveauEtudeDtoRequest toDtoNiveau_etudeDtoRequest(Niveau_etude niveau_etude) 
	    {
	    	NiveauEtudeDtoRequest niveau_etudeDtoRequest = new NiveauEtudeDtoRequest() ;

		    return(niveau_etude != null)? modelMapper.map(niveau_etude, NiveauEtudeDtoRequest.class) :niveau_etudeDtoRequest ;
	    }
	    
	    public static  NiveauEtudeDtoResponse toDtoNiveau_etudeDtoResponse(Niveau_etude niveau_etude) 
	    {
	    	NiveauEtudeDtoResponse niveau_etudeDtoResponse = new NiveauEtudeDtoResponse() ;

		   return (niveau_etude != null)? modelMapper.map(niveau_etude, NiveauEtudeDtoResponse.class):niveau_etudeDtoResponse;
	    }
	    
		public static  Niveau_etude toEntityNiveau_etudeFromRequest(NiveauEtudeDtoRequest niveau_etudeDtoRequest) 
		{
		  return (niveau_etudeDtoRequest != null) ? modelMapper.map(niveau_etudeDtoRequest, Niveau_etude.class):new Niveau_etude();
			
		}	
		
		public static  Niveau_etude toEntityNiveau_etudeFromResponse(NiveauEtudeDtoResponse niveau_etudeDtoResponse) 
		{
		 return(niveau_etudeDtoResponse != null) ? modelMapper.map(niveau_etudeDtoResponse, Niveau_etude.class):new Niveau_etude();
			
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
			  //LES MAPPERS POSTE
				
			    public static  PosteDtoRequest toDtoPosteDtoRequest(Poste poste) 
			    {
			    	PosteDtoRequest posteDtoRequest = new PosteDtoRequest() ;

				    return(posteDtoRequest != null)? modelMapper.map(poste, PosteDtoRequest.class) :posteDtoRequest ;
			    }
			    
			    public static  PosteDtoResponse toDtoPosteDtoResponse(Poste poste) 
			    {
			    	PosteDtoResponse posteDtoResponse = new PosteDtoResponse() ;

				return (posteDtoResponse != null)? modelMapper.map(poste, PosteDtoResponse.class):posteDtoResponse;
			    }
			    
				public static  Poste toEntityPosteFromRequest(PosteDtoRequest posteDtoRequest) 
				{
					Poste poste = new Poste();
				  return (poste != null) ? modelMapper.map(posteDtoRequest, Poste.class):new Poste();
					
				}	
				
				public static  Poste toEntityPosteFromResponse(PosteDtoResponse departementDtoResponse) 
				{
				 return(departementDtoResponse != null) ? modelMapper.map(departementDtoResponse, Poste.class):new Poste();
					
				}	
                //LES MAPPERS POSTE
				
			    public static  ContratDtoRequest toDtoContratDtoRequest(Contrat contrat) 
			    {
			    	ContratDtoRequest contratDtoRequest = new ContratDtoRequest() ;

				    return(contratDtoRequest != null)? modelMapper.map(contrat, ContratDtoRequest.class) :contratDtoRequest ;
			    }
			    
			    public static  ContratDtoResponse toDtoContratDtoResponse(Contrat contrat) 
			    {
			    	ContratDtoResponse contratDtoResponse = new ContratDtoResponse() ;

				return (contratDtoResponse != null)? modelMapper.map(contrat, ContratDtoResponse.class):contratDtoResponse;
			    }
			    
				public static  Contrat toEntityContratFromRequest(ContratDtoRequest contratDtoRequest) 
				{
					Contrat contrat = new Contrat();
				  return (contrat != null) ? modelMapper.map(contratDtoRequest, Contrat.class):new Contrat();
					
				}	
				
				public static  Contrat toEntityContratFromResponse(ContratDtoResponse contratDtoResponse) 
				{
				 return(contratDtoResponse != null) ? modelMapper.map(contratDtoResponse, Contrat.class):new Contrat();
					
				}	
              //LES MAPPERS SUPPORT PHYSIQUE
				
			    public static  SupportPhysiqueDtoRequest toDtoSupportPysiqueDtoRequest(SupportPysique supportPysique) 
			    {
			    	SupportPhysiqueDtoRequest supportPysiqueDtoRequest = new SupportPhysiqueDtoRequest() ;

				    return(supportPysiqueDtoRequest != null)? modelMapper.map(supportPysique, SupportPhysiqueDtoRequest.class) :supportPysiqueDtoRequest ;
			    }
			    
			    public static  SupportPhysiqueDtoResponse toDtoSupportPysiqueDtoResponse(SupportPysique supportPysique) 
			    {
			    	SupportPhysiqueDtoResponse supportPysiqueDtoResponse = new SupportPhysiqueDtoResponse() ;

				return (supportPysiqueDtoResponse != null)? modelMapper.map(supportPysique, SupportPhysiqueDtoResponse.class):supportPysiqueDtoResponse;
			    }
			    
				public static  SupportPysique toEntitySupportPysiqueFromRequest(SupportPhysiqueDtoRequest supportPysiqueDtoRequest) 
				{
					SupportPysique supportPysique = new SupportPysique();
				  return (supportPysique != null) ? modelMapper.map(supportPysiqueDtoRequest, SupportPysique.class):new SupportPysique();
					
				}	
				
				public static  SupportPysique toEntitySupportPysiqueFromResponse(SupportPhysiqueDtoResponse supportPysiqueDtoResponse) 
				{
				 return(supportPysiqueDtoResponse != null) ? modelMapper.map(supportPysiqueDtoResponse, SupportPysique.class):new SupportPysique();
					
				}	
}
