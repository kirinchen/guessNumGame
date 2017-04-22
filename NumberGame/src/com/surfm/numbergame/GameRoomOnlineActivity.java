package com.surfm.numbergame;

import java.util.HashMap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.provider.Settings.Secure;

import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameRoomOnlineActivity extends Activity implements
		OnClickListener, ValueEventListener {
	private int finalAns = 51;
	private EditText inputText;
	private Button okButton;
	private TextView hintText, deviceText;
	private int min = 0, max = 99;
	private Firebase myFirebase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_room);
		Firebase.setAndroidContext(this);
		myFirebase = new Firebase(Constant.FILEBASE_URL);
		Bundle b = this.getIntent().getExtras();
		finalAns = b.getInt("finalAns");
		hintText = (TextView) findViewById(R.id.hintText);
		deviceText = (TextView) findViewById(R.id.deviceId);
		inputText = (EditText) findViewById(R.id.inputText);
		okButton = (Button) findViewById(R.id.okButton);
		okButton.setOnClickListener(this);
		myFirebase.child("currentPlayer").addValueEventListener(this);

		deviceText.setText(Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID));

		refleshHintText();
	}

	private void refleshHintText() {
		hintText.setText(min + " ~ " + max);

	}

	@Override
	public void onClick(View v) {
		int playerAns = Integer.parseInt(inputText.getText().toString());
		if (checkAns(playerAns)) {
			HashMap<String, Object> testMap = new HashMap<String, Object>();
			testMap.put("currentAns", playerAns);
			testMap.put("deviceId", deviceText.getText().toString());
			myFirebase.child("currentPlayer").setValue(testMap);
		}
		// Toast.makeText(this, playerAns + "", Toast.LENGTH_LONG).show();
	}

	private boolean checkAns(int playerInput) {
		if (playerInput > max) {
			inputText.setText("");
			return false;
		} else if (playerInput < min) {
			inputText.setText("");
			return false;
		}
		return true;
	}

	@Override
	public void onCancelled(FirebaseError arg0) {
	}

	@Override
	public void onDataChange(DataSnapshot arg0) {
		Object o = arg0.getValue();
		if (o != null) {
			Toast.makeText(this, "onDataChange " + o, Toast.LENGTH_LONG).show();
			int remotePlayerAns = Integer.parseInt(arg0.child("currentAns")
					.getValue().toString());
			if (finalAns == remotePlayerAns) {
				String currentDeviceId = arg0.child("deviceId").getValue()
						.toString();
				if (currentDeviceId.equals(deviceText.getText().toString())) {
					DialogKit.showAlertDialog(this, "您太神了", null);
				} else {
					DialogKit.showAlertDialog(this, "you lose", null);
				}
			} else if (remotePlayerAns > finalAns) {
				max = remotePlayerAns;
			} else if (remotePlayerAns < finalAns) {
				min = remotePlayerAns;
			}
			refleshHintText();
		}

	}

}
