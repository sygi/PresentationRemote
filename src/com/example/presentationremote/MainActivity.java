package com.example.presentationremote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private void connect(){
		try {
		s = new Socket("192.168.1.105", 4444);
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
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//TextView t = (TextView)this.findViewById(R.id.napis);
		Button next = (Button)this.findViewById(R.id.button2);
		Button prev = (Button)this.findViewById(R.id.button1);
		connect();
		Log.d("sygi", "Connected, setting listeners");
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
		Log.d("sygi", "Listeners set");
		out.println("connected");
		try {
			String get = in.readLine();
			Log.d("sygi", "Client get:" + get);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
