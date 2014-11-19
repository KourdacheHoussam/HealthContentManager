package com.healthmcm.controllers;

import java.util.ArrayList;

import com.healthmcm.db.operations.OperationTextos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.healthmcm.models.Textos;

public class SMSReceiver extends BroadcastReceiver {

	private final String ACTION_RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		if (intent.getAction().equals(ACTION_RECEIVE_SMS))
	 	{
			
			Bundle extra = intent.getExtras();
			if(extra !=null)
			{
				
				
				Object[] pdus = (Object[]) extra.get("pdus");
				//String pdus =  extra.getString("extrat");
				 
				 final SmsMessage[] messages = new SmsMessage[pdus.length];
				 for (int i = 0; i < pdus.length; i++) 
				 { 
					
					 messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
					
				 }
				 if (messages.length > -1)
				 {
					 final String messageBody = messages[0].getMessageBody();
					 final String phoneNumber = messages[0].getDisplayOriginatingAddress();
					 
					 Toast.makeText(context, "Expediteur : " + phoneNumber, Toast.LENGTH_LONG).show();
					 Toast.makeText(context, "Message : " + messageBody, Toast.LENGTH_LONG).show();
				 
					 
				 }
					 
					 
			if( verificationNumero( context,messages[0].getDisplayOriginatingAddress())	)
						{
					enregistrerSMS(context,messages[0]);
					
						
						}
				
							 
				 }
			}
		}
		

	
	public Boolean verificationNumero(Context mycontext ,String numero)
	{
		//Boolean test=false;
		OperationTextos textosBdd = new OperationTextos(mycontext); 
		textosBdd.open();
		ArrayList<Textos> textoFromBdd =textosBdd.getAllTextos();
		
		// final String mArray [] = new String[textoFromBdd.size()];
	       for(int i=0; i<textoFromBdd.size(); i++)
	       {
	    	    if(textoFromBdd.get(i).getNumero().equals(numero))
	    	    {
	    	    	return true;
	    	    }
	       }
		
		
		return false;
		
		
	}
	
	
	public void enregistrerSMS(Context mycontext ,SmsMessage texto)
	{
		Textos mytexto=new Textos();
		mytexto.setMessage(texto.getDisplayMessageBody());
		mytexto.setNumero(texto.getDisplayOriginatingAddress());
		
		
		 OperationTextos textosBdd = new OperationTextos(mycontext);
		 textosBdd.open();
		 textosBdd.insertTexto(mytexto);
		
		
	}

}
