package com.healthmcm.models;

import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class Medecin {

	private int Id_Medecin;
	private String Nom_Medecin;
	private String Prenim_Medecin;
	private Phone Telephone_Medecin;
	private Email Email_Medecin;
	
	public Medecin(int id_Medecin, String nom_Medecin, String prenim_Medecin,
			Phone telephone_Medecin, Email email_Medecin) {
		super();
		Id_Medecin = id_Medecin;
		Nom_Medecin = nom_Medecin;
		Prenim_Medecin = prenim_Medecin;
		Telephone_Medecin = telephone_Medecin;
		Email_Medecin = email_Medecin;
	}
	

	public int getId_Medecin() {
		return Id_Medecin;
	}

	public void setId_Medecin(int id_Medecin) {
		Id_Medecin = id_Medecin;
	}

	public String getNom_Medecin() {
		return Nom_Medecin;
	}

	public void setNom_Medecin(String nom_Medecin) {
		Nom_Medecin = nom_Medecin;
	}

	public String getPrenim_Medecin() {
		return Prenim_Medecin;
	}

	public void setPrenim_Medecin(String prenim_Medecin) {
		Prenim_Medecin = prenim_Medecin;
	}

	public Phone getTelephone_Medecin() {
		return Telephone_Medecin;
	}

	public void setTelephone_Medecin(Phone telephone_Medecin) {
		Telephone_Medecin = telephone_Medecin;
	}

	public Email getEmail_Medecin() {
		return Email_Medecin;
	}

	public void setEmail_Medecin(Email email_Medecin) {
		Email_Medecin = email_Medecin;
	}
	

	
}
