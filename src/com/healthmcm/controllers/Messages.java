package com.healthmcm.controllers;

import java.util.List;

import com.example.healthmcm.R;
import com.healthmcm.db.operations.OperationTextos;
import com.healthmcm.models.Textos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Messages extends Activity {

	private ListView mListView;
	private ExpandableAdaptermsm mExpandableAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmessages);
        mListView=(ListView)findViewById(R.id.expandable_listview);
       
        
     
      //---------------------------------------
    	//Création d'une instance de ma classe OperationTextos
    	OperationTextos textosBdd = new OperationTextos(this);

    

        textosBdd.open();
     Textos texto=new Textos();
     texto.setMessage("test broadcast");
     texto.setNumero("15555215554");
       textosBdd.insertTexto(texto);
       Textos texto2=new Textos();
       texto2.setMessage("test broadcast");
       texto2.setNumero("5554");
         textosBdd.insertTexto(texto2);
         Textos texto3=new Textos();
         texto3.setMessage("test broadcast");
         texto3.setNumero("5556");
           textosBdd.insertTexto(texto3);
           
           Textos texto4=new Textos();
           texto4.setMessage("test broadcast");
           texto4.setNumero("15555215556");
             textosBdd.insertTexto(texto4);
         
         
         
       // textosBdd.insertTexto(texto2);
       // textosBdd.insertTexto(texto3);
       // textosBdd.insertTexto(texto4);


       // Textos textoFromBdd = textosBdd.getTextoWithTitre(texto.getMessage()) ;
       List<Textos> textoFromBdd =textosBdd.getAllTextos() ;
       final String mArray [] = new String[textoFromBdd.size()];
       for(int i=0; i<textoFromBdd.size(); i++)
       {
    	   mArray[i] = textoFromBdd.get(i).getMessage();
       }
       
       
       mExpandableAdapter = new ExpandableAdaptermsm(this, mArray, 10); 
        mListView.setAdapter(mExpandableAdapter); 

       
        mListView.setOnItemClickListener(new OnItemClickListener() {
        	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
			
                  String  l    = (String) mListView.getItemAtPosition(arg2);
            
				Intent t = new Intent(Messages.this, ContenueMessage.class);
				t.putExtra("item",l);
				startActivity(t);
				
				
			}

		});
    }
}
