package com.app.ecole.utils;
import java.text.SimpleDateFormat;
import java.util.*;


public final class Utility 
{

	// CONSTANT POUR LA SECURITE
	public static final long EXPIRATION_TIME = 365*24* 60 * 60 ;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SECRET = "javainuse";
	public static final String DERNIER_PAYE_INITIAL = "9";
	public static final String PAIEMENT_PAYE = "PAYE";
	public static final String PAIEMENT_ANNULE = "ANNULE";
	public static final String LOGO = "logo_folder";
	public static final String FAVICON = "favicon_folder";
	public static final String TYPE_CLASSE = "CLASSE";



	public static final String MATRICULE = "E";


	public static final String LOGIN = "/login";
	public static final String USER_BY_ID = "/users/{id}";
	public static final String USERS = "/users";
	public static final String CHANGE_STATUS_USER_BY_ID = "/users/status/{id}";


	public static final String REDUCTION_BY_ID = "/reductions/{id}";
	public static final String REDUCTIONS = "/reductions";
	public static final String CHANGE_STATUS_REDUCTION_BY_ID = "/reductions/status/{id}";
	public static final String STRUCTURE_BY_ID = "/structures/{id}";
	public static final String STRUCTURES = "/structures";
	public static final String CHANGE_STATUS_STRUCTURE_BY_ID = "/structures/status/{id}";

	public static final String GROUPE_BY_ID = "/groupes/{id}";
	public static final String GROUPES = "/groupes";
	public static final String CHANGE_STATUS_GROUPES_BY_ID = "/groupes/status/{id}";

	public static final String BANQUE_BY_ID = "/banques/{id}";
	public static final String BANQUES = "/banques";
	public static final String CHANGE_STATUS_BANQUE_BY_ID = "/banques/status/{id}";


	public static final String MATIERE_BY_ID = "/matieres/{id}";
	public static final String MATIERES = "/matieres";
	public static final String CHANGE_STATUS_MATIERES_BY_ID = "/matieres/status/{id}";

	public static final String ANNEE_BY_ID = "/annees/{id}";
	public static final String ANNEES = "/annees";
	public static final String CHANGE_STATUS_ANNEE_BY_ID = "/annees/status/{id}";


	public static final String ELEVE_BY_ID = "/eleves/{id}";
	public static final String ELEVES = "/eleves";
	public static final String ELEVES_DETAILS = "/eleves/details/{id}";
	public static final String ELEVES_SOLDE = "/eleves/solde/{id}";
	public static final String ELEVES_STATISTIQUE = "/eleves/statistique";

	public static final String ELEVES_SEARCH = "/eleves/search/{el}";
	public static final String ELEVES_BY_CLASSE = "/classe/eleve/{id}";
	public static final String CHANGE_STATUS_ELEVE_BY_ID = "/eleves/status/{id}";


	public static final String INSCRIPTION_BY_ID = "/inscriptions/{id}";
	public static final String INSCRIPTIONS_SEARCH = "/inscriptions/search/{el}";

	public static final String INSCRIPTIONS_SEARCH_ID = "/inscriptions/search/{type}/{id}";
	public static final String INSCRIPTIONS = "/inscriptions";
	public static final String CLASSE_ELEVES_NON_EN_REGLE = "/classes/impayes/{id}";

	public static final String CLASSE_BY_ID = "/classes/{id}";
	public static final String CHANGE_STATUS_CLASSE_BY_ID = "/classes/status/{id}";

	public static final String SOUS_CLASSE_BY_ID = "/classes/sousclasses/{id}";
	public static final String CLASSES = "/classes";
	public static final String PARENT_BY_ID = "/parents/{id}";
	public static final String PARENTS = "/parents";
	public static final String CHANGE_STATUS_PARENT_BY_ID = "/parents/status/{id}";

	public static final String TYPE_PAIEMENT_BY_ID = "/typepaiements/{id}";
	public static final String CHANGE_STATUS_TYPE_PAIEMENT_BY_ID = "/typepaiements/status/{id}";

	public static final String TYPE_PAIEMENTS = "/typepaiements";
	public static final String MODE_PAIEMENT_BY_ID = "/modepaiements/{id}";
	public static final String MODE_PAIEMENTS = "/modepaiements";
	public static final String CHANGE_STATUS_MODE_PAIEMENT_BY_ID = "/modepaiements/status/{id}";

	public static final String PAIEMENT_BY_ID = "/paiements/{id}";
	public static final String PAIEMENTS_CAISSIER = "/paiements/caisse";
	public static final String PAIEMENTS_CUMUL_SERVICE = "/paiements/cumul/services";

	public static final String CHANGE_STATUS_PAIEMENT_BY_ID = "/paiements/status/{id}";

	public static final String PAIEMENTS = "/paiements";
	
	//GENERATE CALENDAR
	public static int getMonthNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
	}
	public static int getMonthNumberForJob(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.MONTH)+1 > Integer.valueOf(Utility.DERNIER_PAYE_INITIAL) )? calendar.get(Calendar.MONTH)+1 :calendar.get(Calendar.MONTH)+13 ;
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

	public static String genererMatricule(int num, String motif)
	{
		var idString=String.valueOf(num);
		var numero="";

		if(idString.length() < 4)
		{
			int tailleRestant = 4 - idString.length();
			for (int i = 0; i < tailleRestant; i++)
			{
				idString = "0".concat(idString) ;
			}
			numero = motif.concat(idString);
		}
		else
		{
			numero = motif.concat(idString) ;
		}

		return numero;
	}
	public static String genererNumeroPaiement(int num)
	{
		var idString=String.valueOf(num);
		var numero="";
        var motif="P-";
		if(idString.length() < 4)
		{
			int tailleRestant = 4 - idString.length();
			for (int i = 0; i < tailleRestant; i++)
			{
				idString = "0".concat(idString) ;
			}
			numero = motif.concat(idString);
		}
		else
		{
			numero = motif.concat(idString) ;
		}

		return numero;

	}
	public static  String today()
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}
	public static boolean isNumeric(String str){
		return str != null && str.matches("[0-9.]+");
	}



}
