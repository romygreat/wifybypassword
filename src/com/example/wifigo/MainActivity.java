package com.example.wifigo;

import com.example.wifigo.WifiAutoConnectManager.WifiCipherType;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnConnect;
	WifiManager wifiManager;
	WifiAutoConnectManager wac;
	TextView textView1;
	EditText editPwd;
	EditText editSSID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnConnect = (Button) findViewById(R.id.btnConnect);
		textView1 = (TextView) findViewById(R.id.txtMessage);
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wac = new WifiAutoConnectManager(wifiManager);
		
		 editPwd=(EditText) findViewById(R.id.editPwd);
		 editSSID=(EditText) findViewById(R.id.editSSID);
		
		wac.mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// ²Ù×÷½çÃæ
				textView1.setText(textView1.getText()+"\n"+msg.obj+"");
				super.handleMessage(msg);
			}
		};
		btnConnect.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					wac.connect(editSSID.getText().toString(), editPwd.getText().toString(),
							editPwd.getText().toString().equals("")?WifiCipherType.WIFICIPHER_NOPASS:WifiCipherType.WIFICIPHER_WPA);
				} catch (Exception e) {
					textView1.setText(e.getMessage());
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
