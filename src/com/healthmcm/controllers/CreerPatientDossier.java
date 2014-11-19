package com.healthmcm.controllers;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.healthmcm.R;
import com.healthmcm.adapters.SessionManagerAdapter;
import com.healthmcm.db.operations.OperationsDossierMedicalDB;
import com.healthmcm.db.operations.OperationsPatientDB;
import com.healthmcm.models.DossierMedical;
import com.healthmcm.models.Patient;

public class CreerPatientDossier extends Activity {

	private TabHost mytabhost;
	private OperationsPatientDB dbo_patient;
	private OperationsDossierMedicalDB dbo_dossier;
	private Context context;
	private EditText nomP, prenomP, datenaissP, adresseP, emailP, numeroSecuP, telP, telurgenceP, nomtuteurP, prenomtuteurP, teltuteurP;
	private EditText id_patient, id_dossier, date_arrivee, date_sortie, date_consultation, medecin_intervenant, nombre_jours_sejour,etat_actuel_patient, liste_medicament_administres, maladie_soignee, alergies, type_sanguin;
	private Patient patient;
	private DossierMedical dossierMedical;
	private Dialog dialog;
	private Button cancel,save_patient, save_dossier;
	private TextView msg;
	private SessionManagerAdapter session; //qui gere le stockage mes vars de session/methods associees
	private ProgressDialog progress, progress1;
	Calendar calendrier;
	private int year,month, day;
	static final int DATE_PICKER_ID = 1111;
	private AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tabhost_create_dossier_patient);

		// Creation et ajout des onglets:
		mytabhost=(TabHost) findViewById(R.id.TabHost01);
		mytabhost.setup();		
		TabSpec spec_create_patient =mytabhost.newTabSpec("creation patient");
		spec_create_patient.setIndicator("Créer patient", 	getResources().getDrawable(R.drawable.ic_menu_invite));
		spec_create_patient.setContent(R.id.Onglet1);
		mytabhost.addTab(spec_create_patient);		
		TabSpec spec_create_dossier=mytabhost.newTabSpec("creation dossier");
		spec_create_dossier.setIndicator("Créer dossier", getResources().getDrawable(R.drawable.folderplus32));		
		spec_create_dossier.setContent(R.id.Onglet2);
		mytabhost.addTab(spec_create_dossier);

		//*******************RECUPEREATION de quelques instances********************
		calendrier= Calendar.getInstance();
		year=calendrier.get(Calendar.YEAR);
		month=calendrier.get(Calendar.MONTH);
		day=calendrier.get(Calendar.DAY_OF_MONTH);
		this.context=getApplicationContext();//set the context
		dialog=new Dialog(CreerPatientDossier.this);// Dilaog for show the errors
		dialog.setContentView(R.layout.layout_error_dialog);
		msg=(TextView)dialog.findViewById(R.id.error_message);
		cancel=(Button)dialog.findViewById(R.id.goback);
		save_patient=(Button)findViewById(R.id.enregistre_patient);
		//*******************RECUPEREATION DU  FORMULAIRE AJOUT PATIENT********************

		nomP=(EditText)findViewById(R.id.nom_Patient);
		prenomP=(EditText)findViewById(R.id.prenom_Patient);
		datenaissP=(EditText)findViewById(R.id.naissance_Patient);
		datenaissP.setText(day+"/"+month+"/"+year);
		adresseP=(EditText)findViewById(R.id.adresse_Patient);
		emailP=(EditText)findViewById(R.id.email_Patient);
		numeroSecuP=(EditText)findViewById(R.id.numero_secu_Patient);
		telP=(EditText)findViewById(R.id.NumeroTelphone_Patient);
		telurgenceP=(EditText)findViewById(R.id.TelephoneUrgence_Patient);
		nomtuteurP=(EditText)findViewById(R.id.nomTuteur_Patient);
		prenomtuteurP=(EditText)findViewById(R.id.prenomTuteur_Patient);
		teltuteurP=(EditText)findViewById(R.id.Telephone_Tuteur);		
		//******************RECUPERATION DU FORMULAIRE AJOUT DOSSIER************************
		save_dossier=(Button)findViewById(R.id.enregistre_dossier);
		date_arrivee=(EditText)findViewById(R.id.date_entre);
		date_arrivee.setText(day+"/"+month+"/"+year);
		date_sortie=(EditText)findViewById(R.id.date_sortie);
		date_sortie.setText(day+"/"+month+"/"+year);
		date_consultation=(EditText)findViewById(R.id.date_consultation);
		date_consultation.setText("00/00/0000");
		medecin_intervenant=(EditText)findViewById(R.id.medecin_intervenant);
		nombre_jours_sejour=(EditText)findViewById(R.id.nombre_jours_sejour);
		etat_actuel_patient=(EditText)findViewById(R.id.etat_actuel_patient);
		liste_medicament_administres=(EditText)findViewById(R.id.liste_medicament_administres);
		maladie_soignee=(EditText)findViewById(R.id.maladie_soignee);
		alergies=(EditText)findViewById(R.id.alergies);
		type_sanguin=(EditText)findViewById(R.id.type_sanguin);
		//************************************************************************************
		dbo_dossier=new OperationsDossierMedicalDB(CreerPatientDossier.this); //instanciate the db accesseur
		dbo_patient=new OperationsPatientDB(CreerPatientDossier.this); //instanciate the db accesseur
		session=new SessionManagerAdapter(this.getApplicationContext());		
		dbo_patient.openDB();	
		//******************************DATE PICKER **********************************************
		date_arrivee.setOnTouchListener(new OnTouchListener() {	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int [] res=GetDateFromDatePicker(R.id.date_entre, getCurrentFocus());
				date_arrivee.setText(res[0]+"/"+res[1]+"/"+res[2]);				
				return false;
			}
		});
		date_sortie.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int [] res=GetDateFromDatePicker(R.id.date_entre, getCurrentFocus());
				date_sortie.setText(res[0]+"/"+res[1]+"/"+res[2]);				
				return false;
			}
		});
		date_consultation.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int [] res=GetDateFromDatePicker(R.id.date_consultation, getCurrentFocus());
				date_consultation.setText(res[0]+"/"+res[1]+"/"+res[2]);				
				return false;
			}
		});
		//*****************************************************************************************
		save_patient.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(	nomP.getText().toString().trim().length()==0 || prenomP.getText().toString().trim().length()==0	||
						datenaissP.getText().toString().trim().length()==0 || numeroSecuP.getText().toString().trim().length()==0 ){
					msg.setText(" Tous les champs doivent etre remplis");
					dialog.setTitle("Champs vides ");
					dialog.setCancelable(true);
					dialog.show();
				}
				else{
					patient = new Patient(nomP.getText().toString(),prenomP.getText().toString(), datenaissP.getText().toString(),
							adresseP.getText().toString(),emailP.getText().toString(), numeroSecuP.getText().toString(),telP.getText().toString() ,
							telurgenceP.getText().toString(),nomtuteurP.getText().toString(),prenomtuteurP.getText().toString(),teltuteurP.getText().toString());			
					Patient[] data=new Patient[1];
					data[0]=patient;
					AjouterPatientAsynTask task=new AjouterPatientAsynTask();
					task.execute(data);
					mytabhost.setCurrentTab(mytabhost.getCurrentTab()+1);
					
				}
			}
		});

		save_dossier.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(etat_actuel_patient.getText().toString().trim().length()==0|| 
						maladie_soignee.getText().toString().trim().length()==0 || 
						type_sanguin.getText().toString().trim().length()==0){
					msg.setText(" Tous les champs doivent etre remplis");
					dialog.setTitle("Champs vides ");
					dialog.setCancelable(true);
					dialog.show();
				}
				else {
					final int actuelIdPatient=session.RecupererIdentifiantPatientActuel();//à utiliser lors de l'enregistrement du dossiers
					String id_d=maladie_soignee.getText().toString().concat(Integer.toString(actuelIdPatient));
					dossierMedical = new DossierMedical(actuelIdPatient,id_d,
							date_arrivee.getText().toString(), 	date_sortie.getText().toString(),
							date_consultation.getText().toString(),liste_medicament_administres.getText().toString(),
							medecin_intervenant.getText().toString(),nombre_jours_sejour.getText().toString(),
							etat_actuel_patient.getText().toString(),maladie_soignee.getText().toString(),
							alergies.getText().toString(),type_sanguin.getText().toString());
					DossierMedical [] data=new DossierMedical[1];
					data[0]=dossierMedical;
					if(actuelIdPatient !=0 ){
						AjouterDossierAsynTask task=new AjouterDossierAsynTask();
						task.execute(data);
					}
					
				}
			}
		});

		cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	//**********************  METHOD GET DATE ************************************
	public int[] GetDateFromDatePicker(final int i, final View v){		
		LayoutInflater inflater=(LayoutInflater)getLayoutInflater();
		View vue=inflater.inflate(R.layout.layout_date_picker,	null);
		final DatePicker datepicker=(DatePicker)vue.findViewById(R.id.datePicker1);
		builder=new AlertDialog.Builder(this);
		builder.setView(vue);
		builder.setTitle("Choisir date");
		builder.show();
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				year=datepicker.getYear();
				month=datepicker.getMonth();
				day=datepicker.getDayOfMonth();
				updateDate(new StringBuilder().append(day).append("/").append(month).append("/").append(year), i, v);
				dialog.dismiss();
			}
		});			
		return new  int[]{day, month, year};
	}
	private void updateDate(StringBuilder s, int i, View v){
		EditText t=(EditText) v.findViewById(i);
		t.setText(s);
	}
	//*********************************************************************************

	private class AjouterPatientAsynTask extends AsyncTask<Patient, Void, Void> {
		public  AjouterPatientAsynTask(){}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progress=new ProgressDialog(CreerPatientDossier.this);
			progress.setMessage("sauvegarde en cours ...");
			progress.show();
		}
		@Override
		protected Void doInBackground(Patient...  patient) {
			dbo_patient.AjouterUnPatient(patient[0]);
			int idp=dbo_patient.RecupererIdPatient(patient[0].getNom(), patient[0].getPrenom());
			session.EnregistreIdentifiantPatientActuel(idp);
			dbo_patient.closeDB();
			return null;			
		}
		@Override
		protected void onPostExecute(Void result){
			super.onPostExecute(result);
			progress.dismiss();
			dbo_patient.closeDB();
			mytabhost.setCurrentTab(2);
		}
	}

	private class AjouterDossierAsynTask extends AsyncTask<DossierMedical, Void, Void> {

		public  AjouterDossierAsynTask(){}
		@Override
		protected void onPreExecute() {
			progress1=new ProgressDialog(CreerPatientDossier.this);
			progress1.setCancelable(true);
			progress1.setMessage("sauvegarde en cours ...");
			progress1.show();
		}
		@Override
		protected Void doInBackground(DossierMedical...  data) {
			dbo_dossier.AjouterUnDossier(data[0], data[0].getId_patient());
			dbo_dossier.closeDB();
			return null;			
		}
		@Override
		protected void onPostExecute(Void result){
			super.onPostExecute(result);			
			dbo_dossier.closeDB();
			progress1.dismiss();
		}
	}
}




