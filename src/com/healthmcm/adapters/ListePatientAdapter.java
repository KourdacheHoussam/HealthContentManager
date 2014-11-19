package com.healthmcm.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.healthmcm.R;
import com.healthmcm.models.Patient;
import com.healthmcm.models.Utilisateur;

public class ListePatientAdapter extends BaseAdapter implements OnClickListener{

	private ArrayList<Patient> patients;
	private LayoutInflater layInflater;
	private Context context;
	private int current_page;
	private int pagination;

	public ListePatientAdapter(Context context, ArrayList<Patient> utilisateurs){
		this.context=context;
		this.layInflater=LayoutInflater.from(context);
		this.patients=utilisateurs;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  this.patients.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.patients.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public static class ViewHolder{
		TextView item_nom, item_prenom, item_statut;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;		
		/*if(position==current_page*pagination-1){
			ImageButton expand=new ImageButton(context);
			//expand.setImageResource(R.drawable.buttonadd1);
			expand.setOnClickListener(this);
			return expand;
		}
		else{*/			
			convertView = this.layInflater.inflate(R.layout.layout_item_resultat_recherche, null);
			holder = new ViewHolder();

			holder.item_nom=(TextView) convertView.findViewById(R.id.res_item_nom);		
			holder.item_nom.setText((CharSequence)this.patients.get(position).getNom());

			holder.item_prenom=(TextView) convertView.findViewById(R.id.res_item_prenom);
			holder.item_prenom.setText((CharSequence)this.patients.get(position).getPrenom());

			holder.item_statut=(TextView)convertView.findViewById(R.id.res_item_nss);
			holder.item_statut.setText((CharSequence)this.patients.get(position).getNumeroSecuritesociale());

			convertView.setTag(holder);
			return convertView;		
		//}	
	}
	@Override
	public void onClick(View arg0) {
		current_page++;
		notifyDataSetChanged();		
	}

}
