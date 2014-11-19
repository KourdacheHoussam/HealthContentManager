package com.healthmcm.db.operations;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.healthmcm.db.helper.SqlPatientHelper;
import com.healthmcm.models.DossierMedical;
import com.healthmcm.models.Patient;
import com.healthmcm.models.Tache;
import com.healthmcm.models.Utilisateur;

public class OperationsPatientDB {
	private Context context;
	private SqlPatientHelper SqlPatientAdapter;
	private SQLiteDatabase db;
	private Cursor cursor;

	//constructeur
	public OperationsPatientDB(Context context){
		this.context=context;
		SqlPatientAdapter=new  SqlPatientHelper(context);
	}

	//open the db
	public OperationsPatientDB openDB(){
		db=SqlPatientAdapter.getWritableDatabase();
		return this;
	}
	//fermer la base de données;
	public void closeDB(){
		db.close();
	}
	//supprimer tout dans la base de données
	public void TruncateDb(){
		db=SqlPatientAdapter.getWritableDatabase();
		db.execSQL("DELETE * FROM patients");
	}
	//ajouter un utilisateur
	public boolean AjouterUnPatient(Patient p){
		db=SqlPatientAdapter.getWritableDatabase();
		ContentValues vals=new ContentValues();
		vals.put("nom_patient",p.getNom());
		vals.put("prenom_patient", p.getPrenom());
		vals.put("date_naissance", p.getDatenaissance());
		vals.put("adresse", p.getAdresse());
		vals.put("email", p.getEmail());
		vals.put("nb_securite_social", p.getNumeroSecuritesociale());
		vals.put("tel", p.getTelephone());
		vals.put("tel_urgence", p.getTelephoneUrgence());
		vals.put("nom_personne_tuteur", p.getNom_personne_tuteur());
		vals.put("prenom_personne_tuteur", p.getPrenom_personne_tuteur());
		vals.put("tel_personne_tuteur", p.getTel_personne_tuteur());
		return db.insert("patients", null, vals)>0;
	}
	public int RecupererIdPatient(String nom, String prenom){
		db=SqlPatientAdapter.getReadableDatabase();
		String query="Select id_patient from patients where nom_patient = '" + nom + "' and  prenom_patient ='" + prenom +"'";
		cursor=db.rawQuery(query, null);
		return cursor.getColumnIndex("id_patient");
	}
	//supprimer un utilisateur
	public boolean SupprimerUnPatient(Patient p){
		return db.delete("patients", "nom_patient="+p.getNom()+"and prenom_patient="+p.getPrenom(), null)>0;
	}
	//recuperer la liste des utilsiateurs
	public Cursor RecupererListePatients(){
		db=SqlPatientAdapter.getReadableDatabase();
		cursor=db.query("patients", new String[]{"prenom_patient","nom_patient","date_naissance",
						"adresse", "nb_securite_social"}, //on recupere les resultat dans 
						null, null, null, null, null, null);//tableau de 3 champs qu'on lira avec
		return cursor;//a l'aide d'un cursor
	}
	public ArrayList<Patient> RecupererPatientX(String nom, String prenom){
		ArrayList<Patient> res=new ArrayList<Patient>();
		db=SqlPatientAdapter.getReadableDatabase();
		String req="select * from patients where nom_patient ='"+nom+"' and prenom_patient='"+prenom+"'";
		Cursor cursor=db.rawQuery(req, null);
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Patient pn=new Patient(cursor.getString(cursor.getColumnIndex("nom_patient")),cursor.getString(cursor.getColumnIndex("prenom_patient")), 
					cursor.getString(cursor.getColumnIndex("date_naissance")),cursor.getString(cursor.getColumnIndex("adresse")), 
					cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("nb_securite_social")), 
					cursor.getString(cursor.getColumnIndex("tel")), cursor.getString(cursor.getColumnIndex("tel_urgence")), 
					cursor.getString(cursor.getColumnIndex("nom_personne_tuteur")), 
					cursor.getString(cursor.getColumnIndex("prenom_personne_tuteur")), 
					cursor.getString(cursor.getColumnIndex("tel_personne_tuteur")));
			res.add(pn);
		}
		/**while(i<nbres){
			Utilisateur u=new Utilisateur();
			if(cursor.moveToFirst()){				
				u.setLogin(cursor.getString(1));
				u.setMotdepasse(cursor.getString(2));
				u.setStatut(cursor.getString(3));
				res.add(u);
				Log.e("Login", cursor.getString(1));
				Log.e("statut", cursor.getString(3));
			}
			cursor.moveToNext();
			i++;
		}*/
		cursor.close();
		return res;
	}
	//recuperer un utilisateur en fonction de son login et son mot de passe
	public boolean VerifierExistencePatient(String nom, String prenom, double nss){
		db=SqlPatientAdapter.getReadableDatabase();
		if(nom != null && prenom !=null){
			String request="Select * From patients where nom_patient = '" + nom +"'and prenom_patient='"
					+prenom+"' nb_securite_social='"+nss+"'";
			cursor=db.rawQuery(request, null);
		}
		if(cursor.getCount()==0) return false;
		else return true;
	}
	/**
	public DossierMedical GetDossierM(String nom, String prenom, int id_patient){
		db=SqlPatientAdapter.getReadableDatabase();
		String req="select * from dossiersmedicaux dm INNER JOIN patients p  " +
				" ON dm.id_patient=p.'"+id_patient+"'"+
				"where nom_patient ='"+nom+"' and prenom_patient='"+prenom+"'";
		Cursor cursor=db.rawQuery(req, null);
		if(cursor.getCount() != 0 && cursor.getCount() == 1){
			DossierMedical p=new DossierMedical(cursor.getString(cursor.getColumnIndex("nom_patient")));
		}
	}*/
}
