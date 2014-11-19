package com.healthmcm.controllers;

import java.util.List;

import com.example.healthmcm.R;
import com.healthmcm.db.operations.OperationJournal;
import com.healthmcm.models.Journal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class Journal_controller extends Activity {

	private ListView mListView;
	private AdapterJournal mExpandableAdapterJournal;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listjournal);
        mListView=(ListView)findViewById(R.id.expandable_listview_journal);
       
        
     
      //---------------------------------------
    	//Création d'une instance de ma classe OperationTextos
    	OperationJournal journalBdd = new OperationJournal(this);

//    Journal journal=new Journal();
//    journal.setNom("ely");
//    Log.i("", "insertion");
//    journal.setNumero("06666");
//
//    Journal journal1=new Journal();
//    journal1.setNom("elymalik");
//    journal1.setNumero("06666");
//    Journal journal2=new Journal();
//    journal2.setNom("elyndiaye");
//    journal2.setNumero("06666");
//    
//    Journal journal3=new Journal();
//    journal3.setNom("elyndiaye");
//    journal3.setNumero("06666");
    
    	journalBdd.open();
     
//    	journalBdd.insertJournal(journal);
//    	journalBdd.insertJournal(journal1);
//    	journalBdd.insertJournal(journal2);
//    	journalBdd.insertJournal(journal3);
    	


       // Textos textoFromBdd = textosBdd.getTextoWithTitre(texto.getMessage()) ;
       List<Journal> textoFromBdd =journalBdd.getAllJournal() ;
       final String mArray [] = new String[textoFromBdd.size()];
       for(int i=0; i<textoFromBdd.size(); i++)
       {
    	   mArray[i] = textoFromBdd.get(i).getNumero();
       }
       
       
       mExpandableAdapterJournal = new AdapterJournal(this, mArray, 10); 
        mListView.setAdapter(mExpandableAdapterJournal); 

       
        mListView.setOnItemClickListener(new OnItemClickListener() {
        	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
			//	  int itemPosition     = arg2;
				  Log.i("", "onItemSelected");
                  
                  // ListView Clicked item value
                  String  l    = (String) mListView.getItemAtPosition(arg2);
                   
                   // Show Alert 
//                   Toast.makeText(getApplicationContext(),
//                     "Position :"+arg2+"  ListItem : " +l , Toast.LENGTH_LONG)
//                     .show();
				
				
				
				
			// String l =  ((TextView)arg1).getText().toString();
				//String l= (String)arg0.getItemIdAtPosition(arg2);
			//	 Toast toast=Toast.makeText(getApplicationContext(), l, Toast.LENGTH_SHORT);
		      //      toast.show();
				//String l=(String)arg0.getItemAtPosition(arg2);
			//	String l= mArray[arg2];
				// Toast.makeText(this, l + " selected", Toast.LENGTH_LONG).show();
			//	 Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
				 
				Intent t = new Intent(Journal_controller.this, ContenueMessage.class);
				t.putExtra("item",l);
				startActivity(t);
				//Log.println(Log.INFO, "View = ", ""+arg2);
				
			}

		//	@Override
			//public void onNothing(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
		//	}
		});
    }
}
