package com.healthmcm.controllers;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthmcm.R;

public class JournalSmsFragment extends Fragment{


	public JournalSmsFragment (){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.layout_fragment_sms,container, false);
		final EditText numero = (EditText) view.findViewById(R.id.numerotel);
		final EditText message = (EditText) view.findViewById(R.id.messagetel);
		final Button btnenvoyer = (Button) view.findViewById(R.id.envoyer);

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

					Intent i=new Intent(getActivity(), Messagerie.class);
					startActivity(i);
				}else{
					//On affiche un petit message d'erreur dans un Toast
					Toast.makeText(getActivity(), "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show();
				}
			}		
		});
		return view;
	}
}
