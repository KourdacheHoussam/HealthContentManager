package com.healthmcm.controllers;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.healthmcm.R;
import com.healthmcm.controllers.Download.Events;


public class DownloadMain extends Activity implements Events {
    
	Download	dl;
	ProgressBar view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        view = (ProgressBar) findViewById(R.id.progress);
        dl   = new Download(this,"http://earthobservatory.nasa.gov/images/imagerecords/45000/45618/ISS024-E-012425_lrg.jpg","lrg.jpg",this);
        dl.start();
        
        Button b=(Button)findViewById(R.id.bluetooth);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent(DownloadMain.this,SendBybluetooth.class );
				startActivity(in);
				
			}
		});
    }

    @Override
    protected void onDestroy() {
    	dl.stop();
    	super.onDestroy();
    }

	@Override
	public void onProgress(int sent, int length) {		
		view.setProgress((int)((float)sent/(float)length*100));
	}

	@Override
	public void onStart(int length) {		
		view.setProgress(0);
	}

	@Override
	public void onFinish(File file) {
		//(ImageView)findViewById(R.id.image));
	((ImageView)findViewById(R.id.image)).setImageBitmap(BitmapFactory.decodeFile(dl.getDataFile().getAbsolutePath()));		
	}
    
}