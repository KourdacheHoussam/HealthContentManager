package com.healthmcm.controllers;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthmcm.R;
import com.healthmcm.adapters.SessionManagerAdapter;
import com.healthmcm.db.operations.OperationsUtilisateurDB;
import com.healthmcm.notificationservices.PlanifierTacheService;

public class Main extends Activity{

	private SessionManagerAdapter session; //qui gere le stockage mes vars de session/methods associees
	private OperationsUtilisateurDB dbo;
	private Context context;
	private EditText login;
	private EditText mdp;
	private Button loginbutton;
	private Dialog dialog;
	private Button cancel;
	private TextView msg;
	public static final int CODE_RETOUR = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_connexion);
		
		//set type transition
		//getWindow().getAttributes().windowAnimations = R.style.Fade;
		dbo=new OperationsUtilisateurDB(this); //instanciate the db accesseur
		dbo.openDB(); 							//open the db
		session=new SessionManagerAdapter(this.getApplicationContext());

		loginbutton=(Button)findViewById(R.id.btnlogin);
		login=(EditText)findViewById(R.id.login_id);
		mdp=(EditText)findViewById(R.id.mdp_id);
		dialog=new Dialog(Main.this);
		dialog.setContentView(R.layout.layout_error_dialog);
		msg=(TextView)dialog.findViewById(R.id.error_message);
		cancel=(Button)dialog.findViewById(R.id.goback);

		loginbutton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(mdp.getText().toString().trim().length() !=0 && login.getText().toString().trim().length()!=0){	//test if mdp/login exists				
					try{
						LogUserToApp runasync=new LogUserToApp();					
						runasync.execute();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				else{ // print error msg
					msg.setText("Mot de passe et Login obligatoires");
					dialog.setTitle("Mdp & Login");
					dialog.show();
				}
			}			
		});		

		((Button)findViewById(R.id.link_to_register)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i =new Intent(Main.this, CreerUtilisateur.class);
				startActivityForResult(i,CODE_RETOUR);
			}
		});
		cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**	 
	 * @author houssam
	 * La classe qui logue l'utilisateur
	 */

	private class LogUserToApp extends AsyncTask<String, String, Boolean>{
		private ProgressDialog progress;

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			progress=new ProgressDialog(Main.this);
			progress.setMessage("Loging progress ...");
			progress.show();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			boolean exist;
			//connect to data base
			String txt_login=login.getText().toString();
			String txt_mdp=mdp.getText().toString();
			exist=dbo.VerifierLoginEtMdp(txt_login, txt_mdp);//user exists?
			return exist;
		}		

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			progress.dismiss(); //close the progressdialog
			dbo.closeDB(); //close the db

			if(result==true){	//si le mot/login existent
				loginbutton.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						Intent i=new Intent(Main.this, Dashboard.class);
						i.putExtra("login", login.getText().toString());
						i.putExtra("mdp", mdp.getText().toString());
						// si tout est bon, j'enregistre le login et le mdp dans une variables de session
						session.createLoginSession(login.getText().toString(), mdp.getText().toString());
						startActivity(i);
					}	        	
				});
			}
		}
	}

	// Au retour de la sous activite CreerUtilisateur.java, on recupere le MDP/LOGIN
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(data != null){
			if(requestCode==CODE_RETOUR){
				if(resultCode==RESULT_OK){
					login.setText((CharSequence) data.getExtras().get("LOGIN"));
					mdp.setText((CharSequence) data.getExtras().get("MDP"));
					//Toast.makeText(context, ""+data.getStringExtra("LOGIN"), Toast.LENGTH_LONG).show();
				}
			}
		}
	}

}
