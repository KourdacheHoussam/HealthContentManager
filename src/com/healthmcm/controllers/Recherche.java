package com.healthmcm.controllers;

import android.app.Activity;
import android.os.Bundle;

import com.example.healthmcm.R;
import com.healthmcm.db.helper.CommunicatorBetweenFragments;

public class Recherche extends Activity implements CommunicatorBetweenFragments{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_recherche_gen);
	}
	@Override
	public void onButtonPressed(Boolean bol, String[] data) {
		// TODO Auto-generated method stub
		ResultatsRecherche res= (ResultatsRecherche)getFragmentManager().findFragmentById(R.id.fragment2);
		res.launchRecherche(bol, data);
	}
}
