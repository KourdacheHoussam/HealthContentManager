package com.healthmcm.models;

public class DossierMedical {

	private int Id_patient;
	private String Id_dossier;
	private String Date_Arrivee;
	private String Date_Sortie;
	private String Date_derniere_Consultation;
	private String Medicaments_Administres;	
	private String Medecin_intervenant;
	private String Nombre_Jours_Sejour;
	private String Etat_Actuel_Patient;
	private String Maladie_Soignee;
	private String Alergies;
	private String Type_Sanguin;
	
	
	public DossierMedical(int id_patient, 
			String id_dossier,
			String date_Arrivee,
			String date_Sortie,
			String date_Derniere_Consultation,
			String medicaments_Administres,
			String medecin_intervenant,
			String nombre_Jours_Sejour,
			String etat_Actuel_Patient,
			String maladie_Soignee,
			String alergies,
			String type_Sanguin) {
		super();
		Id_patient = id_patient;
		Id_dossier = id_dossier;
		Date_Arrivee = date_Arrivee;
		Date_Sortie = date_Sortie;
		Date_derniere_Consultation = date_Derniere_Consultation;
		Medicaments_Administres = medicaments_Administres;
		Medecin_intervenant = medecin_intervenant;
		Nombre_Jours_Sejour = nombre_Jours_Sejour;
		Etat_Actuel_Patient = etat_Actuel_Patient;
		Maladie_Soignee = maladie_Soignee;
		Alergies = alergies;
		Type_Sanguin = type_Sanguin;
	}


	
//------------------------------------------------------------------------------------------------------	
// Deuxieme constructeur avec moin d'attributs java differencie les deux au nomnbre d'attribut passe en parametre//
	public DossierMedical(int id_patient, String id_dossier, String date_Arrivee,
			String date_Sortie, String date_Derniere_Consultation,
			String maladie_Soignee,
			String alergies) {
		super();
		Id_patient = id_patient;
		Id_dossier = id_dossier;
		Date_Arrivee = date_Arrivee;
		Date_Sortie = date_Sortie;
		Date_derniere_Consultation = date_Derniere_Consultation;
		Maladie_Soignee = maladie_Soignee;
		Alergies = alergies;
	}
	
// ------------------------------------------------------------------------------------------------------
	
	public String getMedecin_intervenant() {
		return Medecin_intervenant;
	}

	public void setMedecin_intervenant(String medecin_intervenant) {
		Medecin_intervenant = medecin_intervenant;
	}

	public int getId_patient() {
		return Id_patient;
	}

	public void setId_patient(int id_patient) {
		Id_patient = id_patient;
	}

	public String getId_dossier() {
		return Id_dossier;
	}

	public void setId_dossier(String id_dossier) {
		Id_dossier = id_dossier;
	}

	public String getDate_Arrivee() {
		return Date_Arrivee;
	}

	public void setDate_Arrivee(String date_Arrivee) {
		Date_Arrivee = date_Arrivee;
	}

	public String getDate_Sortie() {
		return Date_Sortie;
	}

	public void setDate_Sortie(String date_Sortie) {
		Date_Sortie = date_Sortie;
	}

	public String getDate_Derniere_Consultation() {
		return Date_derniere_Consultation;
	}

	public void setDate_Derniere_Consultation(String date_Derniere_Consultation) {
		Date_derniere_Consultation = date_Derniere_Consultation;
	}


	public String getMedicaments_Administres() {
		return Medicaments_Administres;
	}

	public void setMedicaments_Administres(String medicaments_Administres) {
		Medicaments_Administres = medicaments_Administres;
	}

	public String getNombre_Jours_Sejour() {
		return Nombre_Jours_Sejour;
	}

	public void setNombre_Jours_Sejour(String nombre_Jours_Sejour) {
		Nombre_Jours_Sejour = nombre_Jours_Sejour;
	}

	

	public String getEtat_Actuel_Patient() {
		return Etat_Actuel_Patient;
	}

	public void setEtat_Actuel_Patient(String etat_Actuel_Patient) {
		Etat_Actuel_Patient = etat_Actuel_Patient;
	}


	public String getMaladie_Soignee() {
		return Maladie_Soignee;
	}

	public void setMaladie_Soignee(String maladie_Soignee) {
		Maladie_Soignee = maladie_Soignee;
	}

	public String getAlergies() {
		return Alergies;
	}

	public void setAlergies(String alergies) {
		Alergies = alergies;
	}

	public String getType_Sanguin() {
		return Type_Sanguin;
	}

	public void setType_Sanguin(String type_Sanguin) {
		Type_Sanguin = type_Sanguin;
	}



	public String getListe_Medicament_Administres() {
		// TODO Auto-generated method stub
		return this.Medicaments_Administres;
	}
	
	
}
