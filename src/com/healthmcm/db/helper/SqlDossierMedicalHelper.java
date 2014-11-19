package com.healthmcm.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlDossierMedicalHelper extends SQLiteOpenHelper{

	static final String table_name="dossiersmedicaux.db";
	static final String DB_Version="1";
	static final String tag_id_Patient="id_patient";
	static final String tag_id_Dossier="id_dossier";
	static final String tag_date_Arrive="date_arrivee";
	static final String tag_date_Sortie="date_sortie";
	static final String tag_derniere_consultation="derniere_consultation";
	static final String tag_Medecin_intervenant="medecin_intervenant";
	static final String tag_Nombre_Jours_Sejour="nombre_jours_sejour";
	static final String tag_Etat_Actuel_Patient="etat_actuel_patient";
	static final String tag_Liste_Medicament_Administres="liste_medicament_administres";
	static final String tag_Maladie_Soignee="maladie_soignee";
	static final String tag_Alergies="alergies";
	static final String tag_Type_Sanguin="type_sanguin";
	
	static final String requete=
			"create table dossiersmedicaux ("
			+ tag_id_Patient + " integer not null,"
			+ tag_id_Dossier + " integer primary key , " 
			+ tag_date_Arrive + " text,"
			+ tag_date_Sortie + " text,"
			+ tag_derniere_consultation + " text,"
			+ tag_Medecin_intervenant + " text,"
			+ tag_Nombre_Jours_Sejour + " text,"
			+ tag_Etat_Actuel_Patient + " text,"
			+ tag_Liste_Medicament_Administres + " text,"
			+ tag_Maladie_Soignee + " text,"
			+ tag_Alergies + " text,"
			+ tag_Type_Sanguin + " text not null"
			+ ");";
	public SqlDossierMedicalHelper(Context context){
		super(context, table_name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(requete);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists" + table_name);
		onCreate(db);
	}

}
