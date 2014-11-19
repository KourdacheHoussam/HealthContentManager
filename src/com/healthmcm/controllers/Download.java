package com.healthmcm.controllers;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;


public class Download {
	
	public interface Events {
		public void onStart(int length);
		public void onProgress(int sent, int length);
		public void onFinish(File file);
	}
	
	private String 	mUrl;
	private long   	mCurrent;
	private long	mLength;
	private Context mContext;
	private boolean mRunning;
	private Handler mHandler;
	private Events  mEvents;
	private String  mName;
	
    public File getDataFile(){
    	return new File(mContext.getFilesDir().getAbsolutePath(),mName);
    	//return new File(Environment.getExternalStorageDirectory(),mName);    	    
    }
    
    public long getDataFileSize(){
    	File file = getDataFile();
    	return(file.exists())?file.length():0;
    }
	
	public Download(Context context, String url, String name, Events events){
		mName    = name;
		mHandler = new Handler(){
			@Override
			public void dispatchMessage(Message msg) {
				if(mEvents==null)return;
				switch(msg.what){
				case 0:
					mEvents.onStart(msg.arg2);
					break;
				case 1:
					mEvents.onProgress(msg.arg1, msg.arg2);
					break;
				case 2:
					mEvents.onFinish((File)msg.obj);
					break;
				}
			}
		};
		mEvents  = events;
		mRunning = false;
		mContext = context;
		mUrl     = url;
		mCurrent = getDataFileSize();
		mLength  = 0;		
	}
	
	public void start(){
		new Thread(){    			
			@Override
			public void run() {
				if(mRunning)return;				
	    		try {    	    			
		    		DefaultHttpClient		http     = new DefaultHttpClient();
		    		HttpGet					request  = new HttpGet(mUrl);
		    		if(mCurrent>0){
		    			request.addHeader("Range", "bytes=" + mCurrent + "-");
		    		}
		    		request.addHeader("Accept-Encoding","gzip, deflate");
		    		HttpResponse 			response = http.execute(request);
		    		
		    		Header[] headers = response.getHeaders("Content-Encoding");
		    		InputStream is = response.getEntity().getContent();
		    		if (headers.length>0){
		    			if (headers[0].getValue().toLowerCase().equals("gzip")){
		    				is = new GZIPInputStream(is);
		    			}
		    		}		    		
		    		
		    		Header[] range = response.getHeaders("Content-Range");
		    		if (range.length>0){
		    			String s = range[0].getValue();
		    			mLength  = Integer.valueOf(s.subSequence(s.indexOf("/")+1, s.length()).toString());
		    		}else{
			    		Header[] length = response.getHeaders("Content-Length");
			    		if (length.length>0){    			
			    			mLength = Integer.valueOf(length[0].getValue());
			    		}
		    		}
		    		
		    		byte[] 	buffer 		= new byte[1024];
		    		int 	len 		= 0;
		    		long    nexttime    = 0;
		    		mRunning = true;    		    				    		
		    		mHandler.sendMessage(mHandler.obtainMessage(0, 0, (int)mLength));   		    		
		    		RandomAccessFile fos = new RandomAccessFile(getDataFile(),"rw");
		    		fos.seek(mCurrent);
		    		while ((len = is.read(buffer)) != -1){
		    			mCurrent +=len;
		    			long time = SystemClock.elapsedRealtime();
		    			if(nexttime<time){
		    				mHandler.sendMessage(mHandler.obtainMessage(1, (int)mCurrent, (int)mLength));
		    				nexttime = time + 1000;
		    			}
		    			fos.write(buffer, 0, len);
		    			if(mRunning==false){
		    				fos.close();  	    		    		
		    				break;
		    			}
		    		}	    		    		    		
		    		fos.close();
		    		mHandler.sendMessage(mHandler.obtainMessage(1, (int)mCurrent, (int)mLength));
		    		
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		
	    		mHandler.sendMessage(mHandler.obtainMessage(2, getDataFile() ));	    		
	    		
			}
		}.start();    	
	}
	
	public void stop(){
		mRunning=false;
	}
	
}
