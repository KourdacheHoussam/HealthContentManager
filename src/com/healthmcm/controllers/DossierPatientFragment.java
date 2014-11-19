package com.healthmcm.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.healthmcm.R;
import com.healthmcm.adapters.SessionManagerAdapter;
import com.healthmcm.models.DossierMedical;

public class DossierPatientFragment extends Fragment {
	private EditText id_patient, id_dossier, date_arrivee, date_sortie, date_consultation, medecin_intervenant, nombre_jours_sejour,etat_actuel_patient, liste_medicament_administres, maladie_soignee, alergies, type_sanguin;
	private Button cancel,save_patient, save_dossier, update;
	private SessionManagerAdapter sm;
	public DossierPatientFragment (){}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.layout_fragment_dossier_patient,container, false);
		sm=new SessionManagerAdapter(getActivity());
		//******************RECUPERATION DU FORMULAIRE AJOUT DOSSIER************************
		save_dossier=(Button)view.findViewById(R.id.enregistre_dossier);
		save_dossier.setEnabled(true);
		date_arrivee=(EditText)view.findViewById(R.id.date_entre);
		date_sortie=(EditText)view.findViewById(R.id.date_sortie);
		date_consultation=(EditText)view.findViewById(R.id.date_consultation);
		date_consultation.setText("00/00/0000");
		medecin_intervenant=(EditText)view.findViewById(R.id.medecin_intervenant);
		nombre_jours_sejour=(EditText)view.findViewById(R.id.nombre_jours_sejour);
		etat_actuel_patient=(EditText)view.findViewById(R.id.etat_actuel_patient);
		liste_medicament_administres=(EditText)view.findViewById(R.id.liste_medicament_administres);
		maladie_soignee=(EditText)view.findViewById(R.id.maladie_soignee);
		alergies=(EditText)view.findViewById(R.id.alergies);
		type_sanguin=(EditText)view.findViewById(R.id.type_sanguin);
		update=(Button)view.findViewById(R.id.update_dossier);
		save_dossier.setText("Update Dossier");
		// TODO Auto-generated method stub
		DossierMedical dm=sm.getDataDossierM();

		date_arrivee.setText(dm.getDate_Arrivee());
		date_sortie.setText(dm.getDate_Sortie());
		date_consultation.setText(dm.getDate_Derniere_Consultation());
		medecin_intervenant.setText(dm.getMedecin_intervenant());
		nombre_jours_sejour.setText(dm.getNombre_Jours_Sejour());
		etat_actuel_patient.setText(dm.getEtat_Actuel_Patient());
		liste_medicament_administres.setText(dm.getListe_Medicament_Administres());
		maladie_soignee.setText(dm.getMaladie_Soignee());
		alergies.setText(dm.getAlergies());
		
		save_dossier.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DossierMedical dm=sm.getDataDossierM();
				date_arrivee.setText(dm.getDate_Arrivee());
				date_sortie.setText(dm.getDate_Sortie());
				date_consultation.setText(dm.getDate_Derniere_Consultation());
				medecin_intervenant.setText(dm.getMedecin_intervenant());
				nombre_jours_sejour.setText(dm.getNombre_Jours_Sejour());
				etat_actuel_patient.setText(dm.getEtat_Actuel_Patient());
				liste_medicament_administres.setText(dm.getListe_Medicament_Administres());
				maladie_soignee.setText(dm.getMaladie_Soignee());
				alergies.setText(dm.getAlergies());
			}
		});
		return view;
	}

}
