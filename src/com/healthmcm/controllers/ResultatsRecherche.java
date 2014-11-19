package com.healthmcm.controllers;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.healthmcm.R;
import com.healthmcm.adapters.ListePatientAdapter;
import com.healthmcm.adapters.SessionManagerAdapter;
import com.healthmcm.db.operations.OperationsDossierMedicalDB;
import com.healthmcm.db.operations.OperationsPatientDB;
import com.healthmcm.db.operations.OperationsUtilisateurDB;
import com.healthmcm.models.DossierMedical;
import com.healthmcm.models.Patient;

public class ResultatsRecherche extends Fragment{
	private Context context;
	private OperationsUtilisateurDB dbo;
	private OperationsPatientDB dbo_patients;
	private OperationsDossierMedicalDB dbo_dm;
	private ListePatientAdapter listadapter;
	private ListView listView_res;
	private String[] data_search;
	public ArrayList<Patient> lespatients=new ArrayList<Patient>();
	private SessionManagerAdapter sm;
	@Override			   
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View vue=inflater.inflate(R.layout.layout_resultats_recherche, container, false);
		context=getActivity().getApplicationContext();
		sm=new SessionManagerAdapter(context);
		dbo=new OperationsUtilisateurDB(context); //instanciate the db accesseur
		dbo_patients=new OperationsPatientDB(context);
		dbo_dm=new OperationsDossierMedicalDB(context);
		dbo.openDB(); 							//open the db
		dbo_patients.openDB();
		dbo_dm.openDB();
		//on recupere la liste view 
		listView_res=(ListView)vue.findViewById(R.id.liste_resultats_recherche);		
		listView_res.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View vue, int pos,long arg3) {
				Intent i=new Intent(context, ProfilPatient.class);
				//int nomp=Integer.parseInt(lespatients.get(pos).getIdpatient());
				String[] dr=sm.getDataCurrentSearch();
				int idp=dbo_patients.RecupererIdPatient(dr[0], dr[1]);
				DossierMedical dm=dbo_dm.RecupererDossierPatient(idp);
				Log.e("Dossier medical date :", dm.getDate_Arrivee());
				sm.setDataDossierM(dm);
				i.putExtra("patient_actuel", lespatients.get(pos));
				startActivity(i);
			}			
		});

		return vue;
	}
	//lancer la recherche si le boutton du fragment A est appuye  :: appellée automatiquement
	public void launchRecherche(Boolean b, String [] data){
		if(b){
			sm.setDataCurrentSearch(data[0], data[1]);
			RechercheTask rtask=new RechercheTask();
			rtask.execute(data);
		}
	}

	private class RechercheTask extends AsyncTask<String,Void,ArrayList<Patient>>{
		private ProgressDialog progress;

		public RechercheTask(){
			progress=new ProgressDialog(getActivity());
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			progress.setMessage("Searching ...");
			progress.show();
		}

		@Override
		protected ArrayList<Patient> doInBackground(String  ...params) {
			// TODO Auto-generated method stub
			Log.e(params[0]+"->", params[1]);
			return dbo_patients.RecupererPatientX(params[0], params[1]);// .RecupererUtilisateurXAyant(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(ArrayList<Patient> data) {
			progress.dismiss();
			Log.e("les data:", Integer.toString(data.size()));
			if(data!=null){
				lespatients=data;
			}
			if(isCancelled()){
				data=null;
			}
			try{
				listadapter=new ListePatientAdapter(context, data);
				listView_res.setAdapter(listadapter);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			dbo.closeDB();
		}		
	}
}
