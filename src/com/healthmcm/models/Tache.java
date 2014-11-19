package com.healthmcm.models;

import java.util.Date;

import android.text.format.Time;

public class Tache {

	public String date;
	public String heure;
	public String contenu;
	public Tache(String string, String string2, String string3) {
		super();
		this.date = string;
		this.heure = string2;
		this.contenu = string3;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}	
}
