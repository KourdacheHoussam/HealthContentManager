package com.healthmcm.controllers;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.healthmcm.R;
import com.healthmcm.db.helper.CommunicatorBetweenFragments;

public class RecherchePatient extends Fragment{
	private EditText nom,prenom;
	private Context context;
	private ImageButton b_recherche;
	private CommunicatorBetweenFragments communiquer;
	private String[] data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View root= (View) inflater.inflate(R.layout.layout_recherche_form,container, false);
		communiquer=(CommunicatorBetweenFragments)getActivity();	
		context=getActivity().getApplicationContext();	
		data=new String[2]; 		
		b_recherche=(ImageButton)root.findViewById(R.id.button_recherche);	
		nom=(EditText)root.findViewById(R.id.nom_patient);
		prenom=(EditText)root.findViewById(R.id.prenom_patient);
		
		b_recherche.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String data_nom=nom.getText().toString();
				String data_prenom=prenom.getText().toString();
				data[0]=data_prenom;
				data[1]=data_nom;
				
				communiquer.onButtonPressed(true, data);
			}		
		});
		//init(root);
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		context=getActivity().getApplicationContext();		
	}
	/**@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		communiquer=(CommunicatorBetweenFragments)getActivity();		
	}*/
	public void init(View root){
		b_recherche=(ImageButton)root.findViewById(R.id.button_recherche);	
		nom=(EditText)root.findViewById(R.id.nom_patient);
		prenom=(EditText)root.findViewById(R.id.prenom_patient);
		String data_nom=nom.getText().toString();
		String data_prenom=prenom.getText().toString();
		Log.e("the value of nom", data_nom);
		data[0]="F";
		data[1]="f";
		
		b_recherche.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				communiquer.onButtonPressed(true, data);
			}		
		});
	}
}
