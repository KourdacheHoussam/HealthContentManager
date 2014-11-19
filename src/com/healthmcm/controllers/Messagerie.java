package com.healthmcm.controllers;

import com.example.healthmcm.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Messagerie extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.messagerie);
	        
	        
	        Button b=(Button)findViewById(R.id.sms);
	        b.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					Intent i=new Intent(Messagerie.this, Messages.class);
					startActivity(i);
				}	        	
	        });
	        
	        
	        Button bb=(Button)findViewById(R.id.ecriretexto);
	        bb.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					Intent i=new Intent(Messagerie.this, EcrireMessage.class);
					startActivity(i);
				}	        	
	        });
	        
	        
	        
	        Button bbb=(Button)findViewById(R.id.effectuerappel);
	        bbb.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					Intent i=new Intent(Messagerie.this, Appeler.class);
					startActivity(i);
				}	        	
	        });
	        
	        
	        
	        Button bbbb=(Button)findViewById(R.id.journalappel);
	        bbbb.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					Intent i=new Intent(Messagerie.this, Journal_controller.class);
					startActivity(i);
				}	        	
	        });
	        
	        
	    }


		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.menu_messagerie, menu);
			return true;
		}
		// GESTION DES CLISQUES SUR LES ITEMS DU MENU
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch(item.getItemId()){
			case R.id.go_home:
				Intent i=new Intent(this, Dashboard.class);
				startActivity(i);
				break;
			case R.id.action_settings:
				break;
			default:
				break;
			}
			return true;
		}
}
