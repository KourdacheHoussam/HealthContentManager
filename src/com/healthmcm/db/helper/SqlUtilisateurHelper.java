package com.healthmcm.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlUtilisateurHelper extends SQLiteOpenHelper{

	private static final String data_base_name="utilisateurs.db"; 
	private static final String tag_login="login";
	private static final String tag_mdp="mdp";
	private static final String tag_id_utilisateur="id_utilisateur";
	private static final String tag_statut="statut";
	private static final String LOGCAT = null;
	private Context context;
	
	//Requete
	private static final String requete_create_db_utilisateurs=
			"create table utilisateurs ("
			+ tag_id_utilisateur + " integer primary key autoincrement,"
			+ tag_login + " text not null,"
			+ tag_mdp + " text not null,"
			+ tag_statut +" text"
			+ ");";
	
	//Constructor
	public SqlUtilisateurHelper(Context context) {
			super(context, data_base_name, null, 1);
			this.context=context;
	}
	
	//Method to create table by executing the request
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(requete_create_db_utilisateurs);
	}

	//Methode to upgrad the table
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists" + data_base_name);
		onCreate(db);
	}

}
