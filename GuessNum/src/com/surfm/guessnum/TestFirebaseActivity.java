package com.surfm.guessnum;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestFirebaseActivity extends Activity implements OnClickListener,ValueEventListener {

	private Firebase firebase = null;
	private Button sendDataButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Firebase.setAndroidContext(this);
		 firebase = new Firebase(
					"https://tutorialclass.firebaseio.com/"
							+ this.getClass().getSimpleName());
		 
		 firebase.addValueEventListener(this);
		setContentView(R.layout.activity_test_firebase);
		initViews();
	}

	private void initViews() {
		sendDataButton = (Button) findViewById(R.id.sendButton);
		sendDataButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		firebase.setValue("Test");
	}

	@Override
	public void onCancelled(FirebaseError arg0) {
		
	}

	@Override
	public void onDataChange(DataSnapshot arg0) {
		 Toast.makeText(this, arg0.getValue().toString(), Toast.LENGTH_LONG).show();
	}
}
