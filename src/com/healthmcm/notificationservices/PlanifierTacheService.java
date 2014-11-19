package com.healthmcm.notificationservices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.healthmcm.R;
import com.healthmcm.db.operations.OperationsTachesDB;
import com.healthmcm.models.Tache;

public class PlanifierTacheService extends Service {
	private Timer timer;
	private TimerTask tache;
	private OperationsTachesDB db_taches;
	private Context context;
	public static final String EVENT_ACTION="My_service_event";
	private ArrayList<Tache> taches=new ArrayList<Tache>();
	private boolean service_started;
	private ReceiverTache receiver;
	private NotificationManager notificationManager;
	private Notification myNotification;
	private static final int MY_NOTIFICATION_ID=1;

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}
	@Override
	public void onCreate() {
		super.onCreate();
		context=getApplicationContext();
		db_taches=new OperationsTachesDB(context);
		receiver=new ReceiverTache();
	}
	@SuppressWarnings("deprecation")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(), "service Notification started", Toast.LENGTH_LONG).show();
		IntentFilter intentFilter = new IntentFilter(EVENT_ACTION);
		registerReceiver(receiver, intentFilter);
		notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		myNotification = new Notification(R.drawable.application, "Tâche à effectuer", System.currentTimeMillis());
		context=getApplicationContext();
		timer=new Timer();
		//tache=new TimerTask() {			
			//@Override
			//public void run() {
				while(service_started){
					taches=db_taches.RecupererListeTaches();					
					for (Tache tache : taches) {
						Date anotherCurDate=new Date();
						String s="yyyy-MM-dd HH:mm";
						SimpleDateFormat formatter = new SimpleDateFormat(s,Locale.FRANCE);  
						String formattedDateString = formatter.format(anotherCurDate);  
						String[] ds=formattedDateString.split(" ");					
						Log.e("Heruer ac: ", ds[1]);
						Log.e("heure tache:", tache.getHeure());
						if(tache.getDate() ==ds[0] && tache.getHeure()==ds[1]){
							final String notificationTitle ="Tache du : "+ tache.getDate() + "Heere: "+tache.getHeure(); 
							final String notificationDesc = tache.getContenu();
							
							Intent intent1 = new Intent(EVENT_ACTION);
							intent1.putExtra("content_tache", tache.getContenu());	///envoi de données à mon deuxieme service qui
							intent1.putExtra("date", tache.getDate());
							intent1.putExtra("heure", tache.getHeure());
							
							PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0,intent1, Intent.FLAG_ACTIVITY_NEW_TASK);
							myNotification.defaults |= Notification.DEFAULT_SOUND;
							myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
							myNotification.setLatestEventInfo(context, notificationTitle, notificationDesc, pendingIntent); 
							myNotification.vibrate = new long[] {0,200,100,200,100,200}; 
							notificationManager.notify(MY_NOTIFICATION_ID,  myNotification); 
						}
						taches=null;
					}
				}
			//}
		//};
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		if(service_started == false){
			//timer.scheduleAtFixedRate(tache, 0, 3000);
			service_started=true;
		}
		super.onStart(intent, startId);
	}
	public class MyBinder extends Binder{
		PlanifierTacheService getService(){
			return PlanifierTacheService.this;
		}
	}
	@Override
	public void onDestroy() {
		timer.cancel();
		this.unregisterReceiver(receiver);
		super.onDestroy();
	}
	private boolean isMyServiceRunning() {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (PlanifierTacheService.class.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
}
