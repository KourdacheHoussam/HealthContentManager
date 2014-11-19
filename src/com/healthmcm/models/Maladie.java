package com.healthmcm.models;

import java.util.ArrayList;

public class Maladie {
	private String Nom_Maladie;
	private String Type_Maladie;
	private ArrayList<String> Symptomes;
	
	
	public Maladie(String nom_Maladie, String type_Maladie) {
		super();
		Nom_Maladie = nom_Maladie;
		Type_Maladie = type_Maladie;
	}
	public Maladie(String nom_Maladie, String type_Maladie,
			ArrayList<String> symptomes) {
		super();
		Nom_Maladie = nom_Maladie;
		Type_Maladie = type_Maladie;
		Symptomes = symptomes;
	}
	public String getNom_Maladie() {
		return Nom_Maladie;
	}
	public void setNom_Maladie(String nom_Maladie) {
		Nom_Maladie = nom_Maladie;
	}
	public String getType_Maladie() {
		return Type_Maladie;
	}
	public void setType_Maladie(String type_Maladie) {
		Type_Maladie = type_Maladie;
	}
	public ArrayList<String> getSymptomes() {
		return Symptomes;
	}
	public void setSymptomes(ArrayList<String> symptomes) {
		Symptomes = symptomes;
	}
}
