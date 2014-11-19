package com.healthmcm.controllers;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.healthmcm.R;



public class JournalAppelsFragment extends Fragment{

	//EditText mEditText_number = null;
	//LinearLayout mLinearLayout_no_button = null;
	//Button mButton_dial = null;
	private EditText ed;
	private Button b;
	private Context context;
	private TelListener tellistener;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.context=getActivity();
		TelephonyManager manager=(TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		tellistener=new TelListener();
		manager.listen(tellistener, PhoneStateListener.LISTEN_CALL_STATE);
			
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.layout_fragment_appels,container, false);
		ed= (EditText)view.findViewById(R.id.editText1);
		b= (Button)view.findViewById(R.id.appeller);

		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Editable n=ed.getText();
				Toast.makeText(context, n.toString() , Toast.LENGTH_SHORT).show();
				if(n.toString().equals(""))
				{
					Toast.makeText(context, "mauvais numero", Toast.LENGTH_SHORT).show();
					return;
				}

				Intent intent= new  Intent(Intent.ACTION_CALL, Uri.parse("tel:" + n.toString()));
				// performDial();
				startActivity(intent);
			}
		});

		return view;
	}
	
	public class TelListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String numero ) {
			if(TelephonyManager.CALL_STATE_RINGING == state) 
				Log.i("LTM", "RINGING, numéro: " + numero);

			if(TelephonyManager.CALL_STATE_OFFHOOK == state) 
				Log.i("LTM", "OFFHOOK");

			if(TelephonyManager.CALL_STATE_IDLE == state) 
				Log.i("LTM", "IDLE");

			super.onCallStateChanged(state, numero);
		}
	}

}
