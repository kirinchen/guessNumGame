package com.surfm.guessnum;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button startButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		Bundle b = new Bundle();
		b.putInt("Ans", ans);
		i.putExtras(b);
		startActivity(i);
	}
	
	
	
	

}
