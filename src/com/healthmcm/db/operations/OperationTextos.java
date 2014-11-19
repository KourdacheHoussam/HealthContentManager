package com.healthmcm.db.operations;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.healthmcm.db.helper.SQLiteTextosDB;
import com.healthmcm.models.Journal;
import com.healthmcm.models.Textos;

public class OperationTextos {
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "test.db";
	
	
	private static final String TABLE_TEXTOS = "MESSAGES";
	
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_NUMERO = "NUMERO";
	private static final int NUM_COL_NUMERO = 1;
	private static final String COL_MESSAGE = "MESSAGE";
	private static final int NUM_COL_MESSAGE = 2;
	private SQLiteDatabase bdd;
 
	private SQLiteTextosDB maBaseSQLite;
 
	public OperationTextos(Context context){
		//On créer la BDD et sa table
		maBaseSQLite = new SQLiteTextosDB(context, NOM_BDD, null, VERSION_BDD);
	}
	
	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
	
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public long insertTexto(Textos textos){
		
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_NUMERO, textos.getNumero());
		values.put(COL_MESSAGE, textos.getMessage());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(TABLE_TEXTOS, null, values);
	}
	
	public ArrayList<Textos> getAllTextos() {
		Cursor c = bdd.query(TABLE_TEXTOS, new
		String[] {
		COL_ID, COL_NUMERO, COL_MESSAGE }, null, null, null,
		null, null);
		return cursorToTextos(c);
		}
	
	private ArrayList<Textos> cursorToTextos(Cursor c) {
			// Si la requête ne renvoie pas de résultat
			if (c.getCount() == 0)
			return new ArrayList<Textos>(0);
			ArrayList<Textos> rettextos = new ArrayList<Textos>(c.getCount());
			c.moveToFirst();
			do {
				Textos textes = new Textos();
				textes.setId(c.getInt(NUM_COL_ID));
				textes.setNumero(c.getString(NUM_COL_NUMERO));
				textes.setMessage(c.getString(NUM_COL_MESSAGE));
				rettextos.add(textes);
			} while (c.moveToNext());
			// Ferme le curseur pour libérer les ressources
			c.close();
			return rettextos;
			}
	
//	public List GetAll() {
//		// Récupération de la liste des messages
//		//Cursor c = bdd.Query("select " + SQLiteTextosDB + " from " + TABLE_TEXTOS + " where message > ?", new String[]{"1"});
//		Cursor cursor = bdd.query(TABLE_TEXTOS,
//				new String[] { COL_ID,COL_NUMERO,COL_MESSAGE,					
//						 }, null, null, null,null, null);
//
//		return  ConvertCursorToListObject(cursor);
//	}

	
	
	public int removeTextoWithID(int id){
		//Suppression d'un texto de la BDD grâce à l'ID
		return bdd.delete(TABLE_TEXTOS, COL_ID + " = " +id, null);
	}
 
	
	public Textos getTextoWithTitre(String message){
		//Récupère dans un Cursor les valeur correspondant à un texto contenu dans la BDD (ici on sélectionne le texto grâce à son message)
		Cursor c = bdd.query(TABLE_TEXTOS, new String[] {COL_ID, COL_NUMERO, COL_MESSAGE}, COL_MESSAGE + " LIKE \"" + message +"\"", null, null, null, null);
		return cursorToTexto(c);
	}
	
	
	private Textos cursorToTexto(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un livre
		Textos msg = new Textos();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		msg.setId(c.getInt(NUM_COL_ID));
		msg.setNumero(c.getString(NUM_COL_NUMERO));
		msg.setMessage(c.getString(NUM_COL_MESSAGE));
		//On ferme le cursor
		c.close();
 
		//On retourne le livre
		return msg;
	}
//	Public  List ConvertCursorToListObject(Cursor c) {
//		List liste = new ArrayList();
//
//		// Si la liste est vide
//		if (c.getCount() == 0)
//			return liste;
//
//		// position sur le premeir item
//		c.moveToFirst();
//
//		// Pour chaque item
//		do {
//
//			Textos texto = ConvertCursorToObject(c);
//
//			liste.add(texto);
//		} while (c.moveToNext());
//
//		// Fermeture du curseur
//		c.close();
//
//		return liste;
//	}
	
//	public List<ScoreSheet> getScoreSheetList() { 
//	    SQLiteDatabase db = mOpenHelper.getReadableDatabase(); 
//	    SQLiteQueryBuilder qb = new SQLiteQueryBuilder(); 
//	    qb.setTables(ScoreSheet.TABLE_NAME); 
//
//	    List<ScoreSheet> scoresheets = new ArrayList<ScoreSheet>(); 
//	    Cursor c = null; 
//	    try { 
//	        String[] colonnes = new String[] { 
//	            ScoreSheet.COLUMN_NAME_ID,ScoreSheet.COLUMN_NAME_DATE, 
//	            ScoreSheet.COLUMN_NAME_ID_SPORT, 
//	            ScoreSheet.COLUMN_NAME_SCORE_HOME, 
//	            ScoreSheet.COLUMN_NAME_SCORE_VISITOR }; 
//
//	        c = qb.query(db, colonnes, null, null, null, null, Sport.COLUMN_NAME_ID + " DESC");
//
//	        if (c != null && c.getCount() > 0) { 
//	            c.moveToFirst(); 
//	            do { 
//	                ScoreSheet scoreSheet = new ScoreSheet(); 
//	                scoreSheet.setId(c.getLong(0)); 
//	                scoreSheet.setDate(c.getString(1)); 
//	                scoreSheet.setSport(getSport(c.getLong(2))); 
//	                scoreSheet.setScoreHome(c.getLong(3)); 
//	                scoreSheet.setScoreVisitor(c.getLong(4)); 
//	                scoresheets.add(scoreSheet); 
//	            } while (c.moveToNext()); 
//	        } 
//	    } finally { 
//	        if (c != null) { 
//	            c.close(); 
//	        } 
//	        db.close(); 
//	    } 
//	    return scoresheets; 
//	}
	
	
	
//	
	
	
	public int updateLivre(int id, Journal l){
		
		ContentValues values = new ContentValues();
		values.put(COL_NUMERO, l.getNumero());
		values.put(COL_MESSAGE, l.getNom());
		return bdd.update(TABLE_TEXTOS, values, COL_ID + " = " +id, null);
	}
 
	public int removeLivreWithID(int id){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(TABLE_TEXTOS, COL_ID + " = " +id, null);
	}
	
}
