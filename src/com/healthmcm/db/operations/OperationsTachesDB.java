package com.healthmcm.db.operations;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.healthmcm.db.helper.SqlTachesHelper;
import com.healthmcm.models.Patient;
import com.healthmcm.models.Tache;

public class OperationsTachesDB {

	private Context context;
	private SqlTachesHelper SqlTachesAdapter;
	private SQLiteDatabase db;
	private Cursor cursor;

	public OperationsTachesDB(Context context){
		this.context=context;
		SqlTachesAdapter=new  SqlTachesHelper(context);
	}
	//open the db
	public OperationsTachesDB openDB(){
		db=SqlTachesAdapter.getWritableDatabase();
		return this;
	}
	//fermer la base de données;
	public void closeDB(){
		db.close();
	}
	//supprimer tout dans la base de données
	public void TruncateDb(){
		db=SqlTachesAdapter.getWritableDatabase();
		db.execSQL("DELETE * FROM taches");
	}
	

	public boolean AjouterUneTache(Tache p){
		db=SqlTachesAdapter.getWritableDatabase();
		ContentValues vals=new ContentValues();
		vals.put("date_tache",p.getDate().toString());
		vals.put("heure_tache", p.getHeure().toString());
		vals.put("contenu_tache", p.getContenu().toString());
		return db.insert("taches", null, vals)>0;
	}
	
	public boolean SupprimerUneTache(Tache p){
		return db.delete("taches", "heure_tache="+p.getHeure()+"and date_tache="+p.getDate(), null)>0;
	}
	
	public ArrayList<Tache> RecupererListeTaches(){
		db=SqlTachesAdapter.getReadableDatabase();
		cursor=db.query("taches", new String[]{"date_tache","heure_tache","contenu_tache"}, //on recupere les resultat dans 
						null, null, null, null, null, null);//tableau de 3 champs qu'on lira avec
		ArrayList<Tache> res=new ArrayList<Tache>();
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Tache t=new Tache(cursor.getString(cursor.getColumnIndex("date_tache")),
					cursor.getString(cursor.getColumnIndex("heure_tache")),
					cursor.getString(cursor.getColumnIndex("contenu_tache"))
					);
			res.add(t);
		}
		return res;//a l'aide d'un cursor
	}

}
