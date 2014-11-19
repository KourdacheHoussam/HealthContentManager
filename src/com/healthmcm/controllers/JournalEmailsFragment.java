package com.healthmcm.controllers;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.healthmcm.R;
import com.healthmcm.adapters.SessionManagerAdapter;
import com.healthmcm.models.DossierMedical;

public class JournalEmailsFragment extends Fragment{
	private SessionManagerAdapter sm;
	
	public JournalEmailsFragment (){}

	
	
	Button buttonSend;
	EditText textTo;
	EditText textSubject;
	EditText textMessage;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.layout_fragment_emails,container, false);
		sm=new SessionManagerAdapter(getActivity());
		buttonSend = (Button) view.findViewById(R.id.buttonSend);
		textTo = (EditText) view.findViewById(R.id.editTextTo);
		textSubject = (EditText) view.findViewById(R.id.editTextSubject);
		textMessage = (EditText) view.findViewById(R.id.editTextMessage);
		//DossierMedical dm=sm.get();
		//textTo.setText(dm.get)
		
		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String to = textTo.getText().toString();
				String subject = textSubject.getText().toString();
				String message = textMessage.getText().toString();

				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
				//email.putExtra(Intent.EXTRA_CC, new String[]{ to});
				//email.putExtra(Intent.EXTRA_BCC, new String[]{to});
				email.putExtra(Intent.EXTRA_SUBJECT, subject);
				email.putExtra(Intent.EXTRA_TEXT, message);

				//need this to prompts email client only
				email.setType("message/rfc822");

				startActivity(Intent.createChooser(email, "Choose an Email client :"));

			}
		});
		return view;
	}

}



