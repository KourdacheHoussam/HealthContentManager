/**
 * 
 */
package com.healthmcm.controllers;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.healthmcm.R;
import com.healthmcm.adapters.SliderPageAdapter;


public class ConsulterDossier extends FragmentActivity{

	private PagerAdapter monPagerAdapter;
	private ViewPager monViewPager;

	@Override
	protected void onCreate(Bundle instanceState) {
		super.onCreate(instanceState);
		super.setContentView(R.layout.layout_consulte_dossier);

		//creer la liste des fragments
		List<Fragment> fragments=new Vector<Fragment>();

		//ajout des fragments à la liste
		fragments.add(Fragment.instantiate(this, RecherchePatient.class.getName()));
		fragments.add(Fragment.instantiate(this, ResultatsRecherche.class.getName()));

		//creation de l'adapter qui affichera l'adapter.
		this.monPagerAdapter=new SliderPageAdapter(super.getSupportFragmentManager(), fragments);

		this.monViewPager=(ViewPager)findViewById(R.id.viewpager);
		//affecter à ma page viewer un adapter qui adapte l'affichage des fragments(vues)
		this.monViewPager.setAdapter(this.monPagerAdapter);
		//this.monViewPager.setCurrentItem(1);
	
	}
}
