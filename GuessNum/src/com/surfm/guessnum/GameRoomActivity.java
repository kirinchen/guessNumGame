package com.surfm.guessnum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameRoomActivity extends Activity implements OnClickListener {
	private TextView hintText;
	private EditText inputAnsText;
	private Button replyButton;
	private int finalAns ;
	private int minHint = 0 , maxHint = 100;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_room);
		initViews();
		Bundle b =this.getIntent().getExtras();
		finalAns = b.getInt("Ans");
		Toast.makeText(this, finalAns+"", Toast.LENGTH_LONG).show();
		refleshHint();
	}

	private void initViews() {
		hintText = (TextView) findViewById(R.id.ansHint);
		replyButton = (Button) findViewById(R.id.replyButton);
		inputAnsText = (EditText) findViewById(R.id.inputAns);
		replyButton.setOnClickListener(this);
	}
	
	private void refleshHint(){
		hintText.setText(minHint+"~"+maxHint);
	}

	@Override
	public void onClick(View v) {
		String userAns = inputAnsText.getText().toString();
		try {
			int inNum = Integer.parseInt(userAns);
			replyAns(inNum);
		} catch (Exception e) {
			alertAnsFormat();
		}
	}
	
	private void replyAns(int ans){
		if(ans == finalAns){
			Toast.makeText(this, "Bingo", Toast.LENGTH_LONG).show();
		}else if( ans>maxHint || ans <minHint){
			alertAnsFormat();
		}else{
			ansNext(ans);
		}
	}
	
	private void ansNext(int ans) {
		Toast.makeText(this, "¨S¦³µª¹ï", Toast.LENGTH_LONG).show();
		if(ans > finalAns){
			maxHint = ans;
		}else if(ans < finalAns){
			minHint = ans;
		}else{
			throw new RuntimeException("this is imposible ans="+ans);
		}
		refleshHint();
	}

	private void alertAnsFormat(){
		inputAnsText.setText("");
	}
	

	
}
