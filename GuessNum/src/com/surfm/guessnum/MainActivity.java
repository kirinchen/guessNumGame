package com.surfm.guessnum;

import java.util.Random;

import com.firebase.client.Firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Firebase firebase = null;
	private Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Firebase.setAndroidContext(this);
		firebase = new Firebase("https://tutorialclass.firebaseio.com/"
				+ Constant.BASE_GAME_ROOM);
		setContentView(R.layout.activity_main);
		initViews();
	}

	private void initViews() {
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Random r = new Random();
		int ans = r.nextInt(100);
		Intent i = new Intent(this, GameRoomActivity.class);
		firebase.removeValue();
		firebase.child("finalAns").setValue(ans);
		startActivity(i);
	}

}
