package com.example.presentationremote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView t = (TextView)this.findViewById(R.id.napis);
		try {
		s = new Socket("192.168.1.105", 4444);
		} catch (UnknownHostException e){
			System.out.printf("Unknown host\n");
		} catch (IOException e) {
			System.out.printf("IOException");
			e.printStackTrace();
		}
		try {
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e){
			System.out.printf("Error creating i/o from socket\n");
		}
		out.write("costam\n");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
