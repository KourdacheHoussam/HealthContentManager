package com.healthmcm.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthmcm.R;
import com.healthmcm.adapters.SessionManagerAdapter;
import com.healthmcm.db.operations.OperationsTachesDB;
import com.healthmcm.models.Tache;
import com.healthmcm.notificationservices.PlanifierTacheService;
import com.healthmcm.ui.controls.ActionItem;

public class Dashboard extends Activity{

	private SessionManagerAdapter session;
	//attributs for quickactions
	private ActionItem appel_quick_action, sms_quick_action, delete_quick_action;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dashboard);

		//**************  First  :  lancer le systeme de notification de taches *************************
		Intent lancerservice=new Intent(this, PlanifierTacheService.class);
		this.startService(lancerservice);
		//Intent notifservice=new Intent(this, RecevoirNotificationTaches.class);
		//this.startService(notifservice);
		//***********************************************************************************************

		/**
		//on instancie les items du menu quickaction
		this.appel_quick_action=new ActionItem();
		this.sms_quick_action=new ActionItem();
		this.delete_quick_action=new ActionItem();
		this.appel_quick_action.setTitle("Appel");
		this.appel_quick_action.setIcon(getResources().getDrawable(R.drawable.ic_menu_answer_call));
		this.sms_quick_action.setTitle("Sms");
		this.sms_quick_action.setIcon(getResources().getDrawable(R.drawable.ic_menu_start_conversation));
		this.delete_quick_action.setTitle("Enlever");
		this.delete_quick_action.setIcon(getResources().getDrawable(R.drawable.userminus32));
		//on instancie le quickAction
		final QuickAction quick_action=new QuickAction(getApplicationContext());
		quick_action.addActionItem(appel_quick_action);
		quick_action.addActionItem(delete_quick_action);
		quick_action.addActionItem(sms_quick_action);
		 */


		Button consultdossier=(Button)findViewById(R.id.consulter_dossier);//get consult dossier button
		Button saisir_dossier=(Button)findViewById(R.id.saisir_dossier);

		saisir_dossier.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Dashboard.this, CreerPatientDossier.class);
				startActivity(i);
			}
		});
		Button telechargerbutton=(Button)findViewById(R.id.telecharger_dossier);        
		telechargerbutton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(Dashboard.this, DownloadMain.class);
				startActivity(i);
			}	        	
		});

		Button messagerie=(Button)findViewById(R.id.consulter_messagerie);
		messagerie.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(Dashboard.this, Messagerie.class);
				startActivity(i);
			}	        	
		});
		
		consultdossier.setOnClickListener(new OnClickListener(){//listen to the buttons actions
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Dashboard.this,Recherche.class);
				startActivity(i);				
			}	        	
		});	    
		((ImageButton)findViewById(R.id.action_deconnecter)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				session= new SessionManagerAdapter(getApplicationContext());
				session.Deconnecter();
			}
		});
		//---------------------------------------------------------------
		Bundle b=getIntent().getExtras();
		TextView nlu=(TextView)findViewById(R.id.nom_ou_login_user);
		TextView mdp_prenom=(TextView)findViewById(R.id.mdp_prenom_user);
		nlu.setText(b.getString("login"));
		mdp_prenom.setText(b.getString("mdp"));
		//---------------------------------------------------------------
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		//recuperer ici les valeurs enregistres dans onSaveInstanceState
	}

	/**
	 * cette methode sert a sauvegarder les données de la vue au moment de la destruction
	 * de l'activite en question.
	 * Les données sont enregistrées dans le bundle sous de cle-valeur.
	 * 
	 * dans la methode onCreate() : on pourrait recuperer les valeurs sauvegardes lors de la 
	 * derniere destrcution de l'activité, en faisant:
	 * 
	 * if(savedInstanceState != null).
	 * 
	 * Mais au lieu de faire ça on peut :
	 * Appeller tout simplement la methode :
	 * onRestoreInstanceState(Bundle savedInstanceState), pour récuperer les valeurs 
	 * enregistrées dans onSaveInstanceState().
	 * 
	 */
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);

		//enregistrer ici les valeurs à récuperer prochainement lors de la recreation
		//de l'activite par : onCreate(), ou onRestoreInstanceState()
	}
	// GERER L'ACTOIN BAR DE L ACTIVITE CONSULTE DOSSIER
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu_consulte_dossier, menu);
		return true;		
	}

	// GESTION DES CLISQUES SUR LES ITEMS DU MENU
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.ajout_tache_planifiee:
			CreateTache();
			break;
		case R.id.action_settings:
			break;
		default:
			break;
		}
		return true;
	}

	public void CreateTache(){
		LayoutInflater inflater=(LayoutInflater)getLayoutInflater();
		View vue=inflater.inflate(R.layout.layout_planifier_tache,	null);
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		final EditText content=(EditText) vue.findViewById(R.id.contenu_tache);
		final EditText date=(EditText)vue.findViewById(R.id.date_tache);
		final EditText heure=(EditText)vue.findViewById(R.id.heure_tache);
		builder.setView(vue);
		builder.setTitle("Planifier tâche");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				PlanifierTacheService service_tache=new PlanifierTacheService();
				OperationsTachesDB db=new OperationsTachesDB(Dashboard.this);
				db.openDB();
				Tache t=new Tache(date.getText().toString(), heure.getText().toString(), content.getText().toString());
				db.AjouterUneTache(t);
				dialog.dismiss();				
			}			
		}); 
		builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				builder.create().dismiss();
			}
		});
		builder.create().show();
		Toast.makeText(getApplicationContext(), "Tache ajoutée",Toast.LENGTH_SHORT).show();
	}
}
