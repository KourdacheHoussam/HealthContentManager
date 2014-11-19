package com.healthmcm.db.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
 

public class SQLiteTextosDB extends SQLiteOpenHelper {

	private static final String TABLE_TEXTOS = "MESSAGES";
	private static final String COL_ID = "ID";
	private static final String COL_NUMERO = "NUMERO";
	private static final String COL_MESSAGE = "MESSAGE";
// 
//	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TEXTOS + " ("
//	+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NUMERO + " TEXT NOT NULL,"
//	+ COL_MESSAGE + " TEXT NOT NULL, FOREIGN KEY(" +
//	COL_NUMERO+"REFERENCES HMCM.db(tel) );";
// 
	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TEXTOS + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NUMERO + " TEXT NOT NULL,"
			+ COL_MESSAGE + ")";
	
	
	public SQLiteTextosDB(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_BDD);
	}

	@Override
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + TABLE_TEXTOS + ";");
		onCreate(db);
	}
	
	
}
