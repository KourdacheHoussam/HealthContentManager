package com.healthmcm.notificationservices;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.healthmcm.R;
import com.healthmcm.models.Tache;

public class ReceiverTache extends BroadcastReceiver{

	/**
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.e("Service reception ->", "created");		
		ReceiverTache receiver=new ReceiverTache();
		IntentFilter filter=new IntentFilter(PlanifierTacheService.EVENT_ACTION);
		registerReceiver(receiver, filter);
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onCreate() {
		super.onCreate();		
	}*/

	@Override
	public void onReceive(Context context, Intent intent) {										//reception de l'evenement
		if(intent.getAction().equals(PlanifierTacheService.EVENT_ACTION)){						//envoye par le service planifier
			Bundle bundle=intent.getExtras();	
			Log.e("Inside OnReceive Method:"," YEAH");											//je recupere les donness envoye
			if(bundle !=null){																	//j'affiche un popup
				String contenu=bundle.getString("content_tache");
				showDialogTache(context, contenu, bundle.getString("date"), bundle.getString("heure"));
			}
		}
	}


	public void showDialogTache(Context context, String contenu, String date, String heure){
		LayoutInflater inflater=LayoutInflater.from(context);
		View vue=inflater.inflate(R.layout.layout_description_tache,	null);
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		builder.setView(vue);
		builder.setTitle("Tache du : "+ date + " |" + heure);
		TextView desc=(TextView) vue.findViewById(R.id.description_task);
		desc.setText(contenu);
		builder.create().show();
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});		
	}


}
