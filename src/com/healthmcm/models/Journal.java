package com.healthmcm.models;

public class Journal {

	private int id;
	private String numero;
	String nom;

	public Journal(){}
	public Journal(String num, String nom){
		this.numero=num;
		this.nom=nom;
		
	}
	 
public int getId()
{
	return this.id;
	}
 

public void setId(int id)
{
	 this.id =id;
	}

public String getNumero()
{
	return this.numero;
}


public void setNumero(String nmero)
{
	 this.numero=nmero;
}


public String getNom()
{
return this.nom;

}

public void setNom(String nom)
{
this.nom=nom;

}



}
