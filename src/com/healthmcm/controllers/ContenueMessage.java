package com.healthmcm.controllers;
import java.util.Date;

import com.example.healthmcm.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ContenueMessage extends Activity{



	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.single_item);

		TextView txtProduct = (TextView) findViewById(R.id.product_label);

		TextView detail = (TextView) findViewById(R.id.date_label);
		Intent i = getIntent();
		// getting attached intent data
		String B = i.getStringExtra("item");
		String currentDateTimeString = DateFormat.getTimeFormat(this).format(new Date());




		// displaying selected product name
		txtProduct.setText(B);
		detail.setText(currentDateTimeString);

	}

}
