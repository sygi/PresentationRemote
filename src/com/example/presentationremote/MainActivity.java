package com.example.presentationremote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private String serverIP;
	private int port;
	private Intent serverInfo;
	private void connect(){
		try {
		s = new Socket(serverIP, port);
		} catch (UnknownHostException e){
			System.out.printf("Unknown host\n");
		} catch (IOException e) {
			System.out.printf("IOException");
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("Inny dziwny blad");
		}
		try {
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e){
			System.out.printf("Error creating i/o from socket\n");
		}
		out.println("connected");
	}
	private void setButtons(){
		Button next = (Button)this.findViewById(R.id.button2);
		Button prev = (Button)this.findViewById(R.id.button1);

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				out.println("next");
				
			}
		});
		prev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				out.println("prev");
			}
		});
		
		//debug
		try {
			String get = in.readLine();
			Log.d("sygi", "Client get:" + get);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		serverInfo = new Intent(this, ConnectActivity.class);
		startActivityForResult(serverInfo, 41);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	protected void onDestroy(){
		super.onDestroy();
		Log.d("sygi", "finishing");
		if (out != null) //connected
			out.println("disconnect");
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("sygi", "onActivityResult");
		if (requestCode == 41){ //from ConnectActivity
			Log.d("sygi", "reqCode matches");
			if (data == null){
				Log.d("sygi", "no data - finishing");
				return;
			}
			Log.d("sygi", "im here");
			serverIP = data.getStringExtra("IP");
			Log.d("sygi", "getIp");
			if (serverIP.equals("..."))
				serverIP = "192.168.1.105"; //debug option
			port = data.getIntExtra("port", 4444);
			connect();
			Log.d("sygi", "Connected, setting buttons");
			setButtons();
		}
	}
	

}
