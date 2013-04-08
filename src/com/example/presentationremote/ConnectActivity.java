package com.example.presentationremote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectActivity extends Activity {

	Intent my;
	private void packAndSend(){
	//	my = new Intent();
		String ip = "";
		EditText e1 = (EditText)this.findViewById(R.id.ip1),
				 e2 = (EditText)this.findViewById(R.id.ip2),
				 e3 = (EditText)this.findViewById(R.id.ip3),
				 e4 = (EditText)this.findViewById(R.id.ip4);
		ip = e1.getText() + "." + e2.getText() + "." + e3.getText() + "." + e4.getText();
		EditText pt = (EditText)this.findViewById(R.id.port);
		my.putExtra("IP", ip);
		String port = pt.getText() + "";
		if (port.equals(""))
			port = "4444";
		my.putExtra("port", Integer.parseInt(port)); //casting :P
		Log.d("sygi", "set extra");
	}
	protected void onDestroy(){
		super.onDestroy();
		if (my.getStringExtra("IP") == null){
			Log.d("sygi", "cancelled");
			setResult(RESULT_CANCELED, my);
		}
		Log.d("sygi", "connectA destroy");
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		my = new Intent();
		setContentView(R.layout.activity_connect);
		Button b = (Button)this.findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				packAndSend();
				setResult(RESULT_OK, my);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_connect, menu);
		return true;
	}

}
