package com.surfm.numbergame;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.snapshot.IndexedNode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends Activity implements OnClickListener,ValueEventListener {
	private Button startGameButton;
	private Firebase myFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        myFirebase = new Firebase(Constant.FILEBASE_URL);
        startGameButton = (Button) findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(this);
        myFirebase.child("finalAns").addValueEventListener(this);
    }
    
	@Override
	public void onClick(View v) {
		Random r = new Random();
		int finalAns = r.nextInt(99);
		myFirebase.child("finalAns").setValue(finalAns);
		
	}
	@Override
	public void onCancelled(FirebaseError arg0) {
	}

	@Override
	public void onDataChange(DataSnapshot arg0) {
		Object o =arg0.getValue();
		if(o!=null){
			int finalAns = Integer.parseInt(o.toString());
			Toast.makeText(this, "Remote finalAns="+finalAns, Toast.LENGTH_LONG).show();
			Intent i = new Intent(this,GameRoomOnlineActivity.class);
			Bundle b =new Bundle();
			b.putInt("finalAns", finalAns);
			i.putExtras(b);
			startActivity(i);
		}
		
	}

}
