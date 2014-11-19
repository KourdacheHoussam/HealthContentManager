package com.healthmcm.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlTachesHelper extends SQLiteOpenHelper{
	
	private static final String Taches_table="taches.db";
	private static final int DATABASE_VERSION = 2;
	private static final String tag_Id_tache = "id_tache";
	private static final String tag_date_tache = "date_tache";
	private static final String tag_heure = "heure_tache";
	private static final String tag_contenu = "contenu_tache";
	Context context;
	
	private static final String create_table_taches=
			"create table taches ("
					+ tag_Id_tache + "	integer primary key autoincrement," 
					+ tag_date_tache + " text,"
					+ tag_heure + " text,"
					+ tag_contenu + " text"
					+ ");";
	
	public SqlTachesHelper(Context context){
		super(context, Taches_table, null, DATABASE_VERSION);
		this.context=context;
	}	
	/**
	 * Creation de la base de données 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(create_table_taches);
	}
	/**
	 * Suppression de la base de données et réecreation
	 */	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldveriob, int newversion) {
		// TODO Auto-generated method stub
		if(newversion>oldveriob){
			db.execSQL("Drop table if exists "+ Taches_table); //on supprime la table patient
			onCreate(db);//on la recrée
		}

	}

}
