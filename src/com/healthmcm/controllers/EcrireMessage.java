package com.healthmcm.controllers;

import com.example.healthmcm.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EcrireMessage extends Activity{
//	final String EXTRA_NUMERO = "numero";
//	final String EXTRA_MESSAGE = "message";
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.editetexto);
	        
	        final EditText numero = (EditText) findViewById(R.id.numerotel);
	        final EditText message = (EditText) findViewById(R.id.messagetel);
	        final Button btnenvoyer = (Button) findViewById(R.id.envoyer);
	        
	        btnenvoyer.setOnClickListener(new OnClickListener(){
				@Override	
				
				
				public void onClick(View v) {
					//On récupère ce qui a été entré dans les EditText
					String num = numero.getText().toString();
					String msg = message.getText().toString();
					//Si le numéro est supérieur à 4 charactère et que le message n'est pas vide on lance la procédure d'envoi
					if(num.length()>= 4 && msg.length() > 0){
						//Grâce à l'objet de gestion de SMS (SmsManager) que l'on récupère grâce à la méthode static getDefault()
						//On envoit le SMS à l'aide de la méthode sendTextMessage
						SmsManager.getDefault().sendTextMessage(num, null, msg, null, null);
						//On efface les deux EditText
						numero.setText("");
						message.setText("");
						
					Intent i=new Intent(EcrireMessage.this, Messagerie.class);
				    startActivity(i);
						
					}else{
						//On affiche un petit message d'erreur dans un Toast
						Toast.makeText(EcrireMessage.this, "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show();
					}
	 
				}
				
				
				
	        });
	        
	 }
}
