package com.healthmcm.models;

import java.sql.Date;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class Patient implements Parcelable{

	private String Idpatient;
	private String Nom;
	private String Prenom;
	private String Datenaissance;
	private String Adresse;
	private String Email;
	private String NumeroSecuritesociale;
	private String Telephone;
	private String TelephoneUrgence;
	private String Nom_personne_tuteur;
	private String Prenom_personne_tuteur;
	private String Tel_personne_tuteur;


	public Patient(String nom, String prenom,	String datenaissance, String adresse,
			String email,String numeroSecuritesociale,String telephone,String telephoneUrgence,	String nompersonner_tuteur, String prenom_personne_tuteur,
			String tel_personne_tuteur) {	
		this.Nom = nom;
		this.Prenom = prenom;
		this.Datenaissance = datenaissance;
		this.Adresse = adresse;
		this.Email = email;
		this.NumeroSecuritesociale = numeroSecuritesociale;
		this.Telephone = telephone;
		this.TelephoneUrgence = telephoneUrgence;
		this.Nom_personne_tuteur = nompersonner_tuteur;
		this.Prenom_personne_tuteur = prenom_personne_tuteur;
		this.Tel_personne_tuteur = tel_personne_tuteur;
	}


	public String getIdpatient() {
		return Idpatient;
	}





	public void setIdpatient(String idpatient) {
		Idpatient = idpatient;
	}





	public String getNom() {
		return Nom;
	}





	public void setNom(String nom) {
		Nom = nom;
	}





	public String getPrenom() {
		return Prenom;
	}





	public void setPrenom(String prenom) {
		Prenom = prenom;
	}





	public String getDatenaissance() {
		return Datenaissance;
	}





	public void setDatenaissance(String datenaissance) {
		Datenaissance = datenaissance;
	}





	public String getAdresse() {
		return Adresse;
	}





	public void setAdresse(String adresse) {
		Adresse = adresse;
	}





	public String getEmail() {
		return Email;
	}





	public void setEmail(String email) {
		Email = email;
	}





	public String getNumeroSecuritesociale() {
		return NumeroSecuritesociale;
	}





	public void setNumeroSecuritesociale(String numeroSecuritesociale) {
		NumeroSecuritesociale = numeroSecuritesociale;
	}





	public String getTelephone() {
		return Telephone;
	}





	public void setTelephone(String telephone) {
		Telephone = telephone;
	}





	public String getTelephoneUrgence() {
		return TelephoneUrgence;
	}





	public void setTelephoneUrgence(String telephoneUrgence) {
		TelephoneUrgence = telephoneUrgence;
	}





	public String getNom_personne_tuteur() {
		return Nom_personne_tuteur;
	}





	public void setNom_personne_tuteur(String nom_personne_tuteur) {
		Nom_personne_tuteur = nom_personne_tuteur;
	}





	public String getPrenom_personne_tuteur() {
		return Prenom_personne_tuteur;
	}





	public void setPrenom_personne_tuteur(String prenom_personne_tuteur) {
		Prenom_personne_tuteur = prenom_personne_tuteur;
	}





	public String getTel_personne_tuteur() {
		return Tel_personne_tuteur;
	}





	public void setTel_personne_tuteur(String tel_personne_tuteur) {
		Tel_personne_tuteur = tel_personne_tuteur;
	}


	public Patient(Parcel p){
		readFromParcel(p);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.getIdpatient());
		dest.writeString(this.getNom());
		dest.writeString(this.getPrenom());
		dest.writeString(this.getDatenaissance());
		dest.writeString(this.getAdresse());
		dest.writeString(this.getEmail());

		dest.writeString(this.getNumeroSecuritesociale());
		dest.writeString(this.getTelephone());
		dest.writeString(this.getTelephoneUrgence());
		dest.writeString(this.getNom_personne_tuteur());
		dest.writeString(this.getPrenom_personne_tuteur());
		dest.writeString(this.getTel_personne_tuteur());
		
	}
	private void readFromParcel(Parcel in) {   
	
		this.Nom = in.readString();
		this.Prenom = in.readString();
		this.Datenaissance = in.readString();
		this.Adresse = in.readString();
		this.Email = in.readString();
		this.NumeroSecuritesociale = in.readString();
		this.Telephone = in.readString();
		this.TelephoneUrgence = in.readString();
		this.Nom_personne_tuteur = in.readString();
		this.Prenom_personne_tuteur = in.readString();
		this.Tel_personne_tuteur = in.readString();

	}
	 public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { 
		 public Patient createFromParcel(Parcel in) { 
			 return new Patient(in);
			 }   
		 public Patient[] newArray(int size) { 
			 return new Patient[size]; 
			 } 
		 }; 
}
