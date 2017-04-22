package com.surfm.numbergame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameRoomActivity extends Activity implements OnClickListener {

	private int finalAns = 51;
	private EditText inputText;
	private Button okButton;
	private TextView hintText;
	private int min=0,max=99;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_room);
		Bundle b = getIntent().getExtras();
		finalAns = b.getInt("finalAns");
		//Toast.makeText(this, "HI~~~"+finalAns, Toast.LENGTH_LONG).show();
		hintText = (TextView) findViewById(R.id.hintText);
		inputText = (EditText) findViewById(R.id.inputText);
		okButton = (Button) findViewById(R.id.okButton);
		okButton.setOnClickListener(this);
		refleshHintText();
	}

	private void refleshHintText(){
		hintText.setText(min+" ~ "+max);
	}
	
	@Override
	public void onClick(View v) {
		int playerAns = Integer.parseInt(inputText.getText().toString());
		//Toast.makeText(this, playerAns + "", Toast.LENGTH_LONG).show();
		if (checkAns(playerAns)){
			if(finalAns == playerAns){
				DialogKit.showAlertDialog(this, "您太神了", null);
			}else if (playerAns > finalAns){
				max = playerAns;
			}else if(playerAns < finalAns){
				min = playerAns;
			}
			refleshHintText();			
		}
	}

	private boolean checkAns(int playerInput){
		if(playerInput > max){
			inputText.setText("");
			return false;
		}else if(playerInput < min){
			inputText.setText("");
			return false;
		}
		return true;
	}
	
}
