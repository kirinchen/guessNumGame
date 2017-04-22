package com.surfm.numbergame;
import java.util.Date;
import java.util.HashMap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TestFireBaseActivity extends Activity implements ValueEventListener  {
	
	private Button testInsertRowButton;
	private Button listButton;
	private Firebase myFirebase;
	private TextView outputText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_firebase);
		outputText = (TextView) findViewById(R.id.output);
		Firebase.setAndroidContext(this);
		myFirebase = new Firebase("https://testtest1111.firebaseio.com/");
		testInsertRowButton = (Button) findViewById(R.id.insertRowButton);
		testInsertRowButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myFirebase.setValue("HI124");
				Toast.makeText(TestFireBaseActivity.this, "HI~~~onClick", Toast.LENGTH_LONG).show();
				HashMap<String, Object> testMap = new HashMap<String, Object>();
				testMap.put("k1", 123);
				testMap.put("k2", 0.5f);
				testMap.put("k3", true);
				myFirebase.child("TestNode").setValue(new Date());
				myFirebase.child("TestMapNode").setValue(testMap);
				TestData td = new TestData("MyName", "MyPass", 3);
				myFirebase.child("TestMyObject").setValue(td);
				
			}
		});
		listButton = (Button) findViewById(R.id.listButton);
		listButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myFirebase.child("TestNode").addValueEventListener(TestFireBaseActivity.this);
				myFirebase.child("TestMyObject").addValueEventListener(TestFireBaseActivity.this);
			}
		});
	}
	@Override
	public void onCancelled(FirebaseError arg0) {
	}
	@Override
	public void onDataChange(DataSnapshot arg0) {
		if("TestNode".equals(arg0.getKey())){
			Object d = arg0.getValue();
			outputText.setText("TestNode data="+d +"\n");
		}
		Toast.makeText(TestFireBaseActivity.this, "Data="+arg0.getKey(), Toast.LENGTH_LONG).show();
	}



}
