package com.healthmcm.adapters;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.healthmcm.controllers.Main;
import com.healthmcm.models.DossierMedical;

public class SessionManagerAdapter {

	//on declare une preference qui stockera nous données
	SharedPreferences mesprefs;
	//on declare un editor pour ecrire le "content" sharedpreferences
	Editor editor;
	//un context d'appli
	Context context;
	//type d'enregistrement dans le content prefrencess
	int PRIVATE_MODE=0;
	//donner un nom à ma prefrences
	private String NOM_PREF="HealthMCM";

	// Ci-dessous les quelques variables que je stockerai dans mon conteneur de prefrences
	private static final String IS_LOGIN="isLogged";
	public static final String LOGIN="login";
	public static final String MDP="mdp";
	public static final String NOM_PATIENT="nom_patient";
	public static final String PRENOM_PATIENT="prenom_patient";
	public static final String ID_PATIENT="id_patient";
	public static final String Nom_Recherche="nom_recherche";
	public static final String Prenom_Recherche="prenom_recherche";
	
	
	//creation de la session et de l'editor
	public SessionManagerAdapter(Context context){
		this.context=context;
		mesprefs=context.getSharedPreferences(NOM_PREF, PRIVATE_MODE);
		editor=mesprefs.edit();
	}
	//fonction qui enregistre les données concernat le login
	public void createLoginSession(String login, String mdp){
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(LOGIN, login);
		editor.putString(MDP, mdp);
		editor.commit();
	}
	//set recherche
	public void setDataCurrentSearch(String nom, String prenom){
		editor.putString(Nom_Recherche, nom);
		editor.putString(Prenom_Recherche, prenom);
	}
	//get data search
	public String[] getDataCurrentSearch(){
		String[] data=new String[2];
		data[0]= mesprefs.getString(Nom_Recherche, null);
		data[1]= mesprefs.getString(Prenom_Recherche, null);
		return data;
	}
	//set dossier
	public void setDataDossierM(DossierMedical dm){
		editor.putInt("idpatient", dm.getId_patient());
		editor.putString("iddossier", dm.getId_dossier());
		editor.putString("datearrivee", dm.getDate_Arrivee());
		editor.putString("datesortie", dm.getDate_Sortie());
		editor.putString("derniereconsulte", dm.getDate_Derniere_Consultation());
		editor.putString("maladiesoignee", dm.getMaladie_Soignee());
		editor.putString("medicamentsadministree", dm.getListe_Medicament_Administres());
		editor.putString("typesanguin", dm.getType_Sanguin());
		editor.putString("medecinintevenant", dm.getMedecin_intervenant());
		editor.putString("etatactuelpatient", dm.getEtat_Actuel_Patient());
		editor.putString("nombrejour", dm.getNombre_Jours_Sejour());
		editor.putString("alergie", dm.getAlergies());
	}
	public DossierMedical getDataDossierM(){
		DossierMedical dm= new DossierMedical(
				mesprefs.getInt("idpatient", 0), 
				mesprefs.getString("iddossier", null), 
				mesprefs.getString("datearrivee", null), 
				mesprefs.getString("datesortie", null), 
				mesprefs.getString("derniereconsulte", null), 
				mesprefs.getString("medicamentsadministree", null), 
				mesprefs.getString("medecinintevenant", null), 
				mesprefs.getString("nombrejour", null), 
				mesprefs.getString("etatactuelpatient", null), 
				mesprefs.getString("maladiesoignee", null), 
				mesprefs.getString("alergie", null), 
				mesprefs.getString("typesanguin", null));
		return dm;
	}
	//fonction qui recupere les données enregistrees concernant le login
	public HashMap<String, String> getLoginSessionData(){
		HashMap<String, String> data=new HashMap<String, String>();
		data.put(LOGIN, mesprefs.getString(LOGIN, null));
		data.put(MDP, mesprefs.getString(MDP, null));
		return data;
	}
	//fonction qui verifie si l'utilisateur est logue ou pas/ sinon on redirige vers le form de login
	public void VerifierLogin(){
		if( ! mesprefs.getBoolean(IS_LOGIN, false)){
			Intent i = new Intent(context, Main.class);
			//close all activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//un flag pour demarrer une activitie
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
	}
	public boolean VerifierConnection(){
		boolean res= mesprefs.getBoolean(IS_LOGIN, false);		 
		return res;
	}
	//enregistrer un identifiant du patient actuel
	public void EnregistreIdentifiantPatientActuel(int value){
		editor.putInt(ID_PATIENT, value);		
	}
	//recuerper l'identifiant du patient actuel
	public int RecupererIdentifiantPatientActuel(){
		int res;
		return mesprefs.getInt(ID_PATIENT, 0);
	}
	
	//fonction qui delogue un user
	public void Deconnecter(){
		editor.clear();
		editor.commit();
		Intent i = new Intent(context, Main.class);
		//close all activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//un flag pour demarrer une activitie
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}
