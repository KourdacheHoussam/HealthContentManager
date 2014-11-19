package com.healthmcm.models;

public class Utilisateur {
	private int id_utilisateur;
	private String Login;
	private String Motdepasse;
	private String Statut;
	
	
	public Utilisateur(String login, String motdepasse) {
		super();
		Login = login;
		Motdepasse = motdepasse;
	}
	
	public Utilisateur() {
		super();
	}
	
	public Utilisateur(String login, String motdepasse, String statut) {
		super();
		Login = login;
		Motdepasse = motdepasse;
		Statut = statut;
	}
	
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getMotdepasse() {
		return Motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		Motdepasse = motdepasse;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	
}
