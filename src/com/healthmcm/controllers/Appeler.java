package com.healthmcm.controllers;

import com.example.healthmcm.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Appeler extends Activity {
	EditText ed;
	Button b;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appeler);

		TelephonyManager manager=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		manager.listen(new TelListener(), PhoneStateListener.LISTEN_CALL_STATE);
		ed= (EditText)findViewById(R.id.editText1);
		b= (Button)findViewById(R.id.appeller);

		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Editable n=ed.getText();
				Toast.makeText(Appeler.this, n.toString() , Toast.LENGTH_SHORT).show();
				if(n.toString().equals(""))
				{
					Toast.makeText(Appeler.this, "mauvais numero", Toast.LENGTH_SHORT).show();
					return;
				}

				Intent intent= new  Intent(Intent.ACTION_CALL, Uri.parse("tel:" + n.toString()));
				// performDial();
				startActivity(intent);
			}
		});
	}


	class TelListener extends PhoneStateListener {
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
