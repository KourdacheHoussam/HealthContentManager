package com.healthmcm.models;

public class Textos {

	private int id;
	private String numero;
	String message;

	public Textos(){}
	public Textos(String num, String msg){
		this.numero=num;
		this.message=msg;
		
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


public String getMessage()
{
return this.message;

}

public void setMessage(String message)
{
this.message=message;

}



}
