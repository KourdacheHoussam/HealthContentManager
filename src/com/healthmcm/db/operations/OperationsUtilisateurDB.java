package com.healthmcm.db.operations;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.healthmcm.db.helper.SqlUtilisateurHelper;
import com.healthmcm.models.Utilisateur;

public class OperationsUtilisateurDB {

	private Context context;
	private SqlUtilisateurHelper SqlUserAdapter;
	private SQLiteDatabase db;
	private Cursor cursor;

	//constructeur
	public OperationsUtilisateurDB(Context context){
		SqlUserAdapter=new SqlUtilisateurHelper(context);		
		this.context=context;
	}
	//open the db
	public OperationsUtilisateurDB openDB(){
		db=SqlUserAdapter.getWritableDatabase();
		return this;
	}
	//fermer la base de données;
	public void closeDB(){
		db.close();
	}
	//supprimer tout dans la base de données
	public void TruncateDb(){
		db.execSQL("DELETE * FROM utilisateurs");
	}
	//ajouter un utilisateur
	public boolean AjouterUnUtilisateur(Utilisateur u){
		db=SqlUserAdapter.getWritableDatabase();
		ContentValues vals=new ContentValues();
		vals.put("login", u.getLogin());
		vals.put("mdp", u.getMotdepasse());
		vals.put("statut", u.getStatut());
		return db.insert("utilisateurs", null, vals)>0;//on insere le produit
	}
	//supprimer un utilisateur
	public boolean SupprimerUnUtilisateur(Utilisateur u){
		db=SqlUserAdapter.getWritableDatabase();
		return db.delete("utilisateurs", "login="+u.getLogin(), null)>0;
	}

	//recuperer la liste des utilsiateurs
	public Cursor RecupererListeUtilisateur(){
		db=SqlUserAdapter.getReadableDatabase();
		cursor=db.query("utilisateurs", new String[]{"login","mdp","statut"}, //on recupere les resultat dans 
				null, null, null, null, null, null);					//tableau de 3 champs qu'on lira avec
		return cursor;													//a l'aide d'un cursor
	}

	//recuperer un utilisateur x	
	public ArrayList<Utilisateur> RecupererUtilisateurXAyant(String login, String statut){
		ArrayList<Utilisateur> res=new ArrayList<Utilisateur>();
		db=SqlUserAdapter.getReadableDatabase();
		String req="select * from utilisateurs where login ='"+login+"' and statut='"+statut+"'";
		Cursor cursor=db.rawQuery(req, null);
		
		int nbres=cursor.getCount();
		int i=0;
		while(i<nbres){
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
		}
		cursor.close();
		return res;
	}

	//recuperer un utilisateur en fonction de son login et son mot de passe
	public boolean VerifierLoginEtMdp(String login, String mdp){
		db=SqlUserAdapter.getReadableDatabase();
		if(login != null && mdp !=null){
			String request="Select * From utilisateurs where login = '" + login +"' and mdp= '" + mdp +"'";
			cursor=db.rawQuery(request, null);
		}
		if(cursor.getCount()==0) return false;
		else return true;
	}
}
