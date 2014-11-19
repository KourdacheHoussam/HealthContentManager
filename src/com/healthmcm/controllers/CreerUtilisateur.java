package com.healthmcm.controllers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthmcm.R;
import com.healthmcm.db.operations.OperationsUtilisateurDB;
import com.healthmcm.models.Utilisateur;

public class CreerUtilisateur extends Activity{

	private OperationsUtilisateurDB dbo;
	private Context context;
	private EditText login, mdp, mdp_confirmation, statut;
	private Utilisateur user;
	private Dialog dialog;
	private Button cancel,save_user;
	private TextView msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_create_user);
		
		this.context=getApplicationContext();//set the context
		dialog=new Dialog(CreerUtilisateur.this);// Dilaog for show the errors
		dialog.setContentView(R.layout.layout_error_dialog);
		msg=(TextView)dialog.findViewById(R.id.error_message);
		cancel=(Button)dialog.findViewById(R.id.goback);
		//Get views attributes
		save_user=(Button)findViewById(R.id.enregistre_user);
		login=(EditText)findViewById(R.id.user_login);
		mdp=(EditText)findViewById(R.id.user_mdp);
		mdp_confirmation=(EditText)findViewById(R.id.user_mdp_confirmation);
		statut=(EditText)findViewById(R.id.statut);

		dbo=new OperationsUtilisateurDB(CreerUtilisateur.this); //instanciate the db accesseur
		dbo.openDB(); //open the db		
		
		//CANCEL BUTTON
		cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		// SAVE BUTTON
		save_user.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(login.getText().toString().trim().length()==0 ||  mdp.getText().toString().trim().length()==0	||
						mdp_confirmation.getText().toString().trim().length()==0 ||  statut.getText().toString().trim().length()==0){
					msg.setText("Il manque le Login, Mdp, Statut ou la Confirmation du mot de passe.");
					dialog.setTitle("Loging Failed");
					dialog.setCancelable(true);
					dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,	R.drawable.emo_failed);
					dialog.show();
				}
				else if(!mdp.getText().toString().equals(mdp_confirmation.getText().toString())){
					msg.setText("Le Mdp & le Mdp de Confirmation sont différents.");
					dialog.setTitle("Loging Failed");
					dialog.setCancelable(true);
					dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,	R.drawable.emo_failed);
					dialog.show();
				}
				else{
					user=new Utilisateur(login.getText().toString(), mdp.getText().toString(), statut.getText().toString());
					if(dbo.AjouterUnUtilisateur(user)){
						Toast.makeText(context,""+login.getText().toString() +"/"+mdp.getText().toString()+" user created" , Toast.LENGTH_LONG).show();
						Intent i=new Intent();
						String l=login.getText().toString();
						String m=mdp.getText().toString();
						i.putExtra("LOGIN",l);
						i.putExtra("MDP",m);
						setResult(Activity.RESULT_OK, i);
						finish();
					}
					else{
						msg.setText("Save failed");
						dialog.setTitle("Impossible d'enregistrer l'utilisateur");
						dialog.show();
					}					
				}
			}
		});
	}

}
