package com.surfm.guessnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameRoomActivity extends Activity implements OnClickListener,
		ValueEventListener {
	private Firebase firebase = null;
	private TextView hintText;
	private EditText inputAnsText;
	private Button replyButton;
	private int finalAns;
	private int minHint = 0, maxHint = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Firebase.setAndroidContext(this);
		firebase = new Firebase("https://tutorialclass.firebaseio.com/"
				+ Constant.BASE_GAME_ROOM);
		firebase.addValueEventListener(this);
		setContentView(R.layout.activity_game_room);
		initViews();

		refleshHint();
	}

	private void initViews() {
		hintText = (TextView) findViewById(R.id.ansHint);
		replyButton = (Button) findViewById(R.id.replyButton);
		inputAnsText = (EditText) findViewById(R.id.inputAns);
		replyButton.setOnClickListener(this);
	}

	private void refleshHint() {
		hintText.setText(minHint + "~" + maxHint);
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

	private void replyAns(int ans) {
		if (ans > maxHint || ans < minHint) {
			alertAnsFormat();
		} else {
			setAns2Firebase(ans);
		}
	}

	private void ansNext(int ans) {
		if (ans == finalAns) {
			DialogKit.showAlertDialog(this, "Bingo", null);
		} else if (ans > finalAns) {
			DialogKit.showAlertDialog(this, "沒有答對", null);
			maxHint = ans;
		} else if (ans < finalAns) {
			DialogKit.showAlertDialog(this, "沒有答對", null);
			minHint = ans;
		}
		refleshHint();
	}

	private void setAns2Firebase(int guessNum) {
		GameData gd = new GameData();
		gd.setCurrrentGuessNum(guessNum);
		gd.setFinalAns(finalAns);
		gd.setDeviceId(Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID));
		gd.setGuessAt(new Date());
		firebase.setValue(gd);
	}

	private void alertAnsFormat() {
		inputAnsText.setText("");
	}

	private void syncData(GameData gd) {
		finalAns = gd.getFinalAns();
		Toast.makeText(this, finalAns + "", Toast.LENGTH_LONG).show();
		ansNext(gd.getCurrrentGuessNum());
	}

	@Override
	public void onCancelled(FirebaseError arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDataChange(DataSnapshot arg0) {
		// Toast.makeText(this,arg0.getValue().toString(),
		// Toast.LENGTH_LONG).show();
		// syncData((GameData) arg0.getValue());
		Map<String, Object> map = (Map<String, Object>) arg0.getValue();
		Toast.makeText(this, map.toString(), Toast.LENGTH_LONG).show();
		GameData gd = new GameData();
		long l = (Long) map.get("finalAns");
		gd.setFinalAns((int) l);
		if (map.containsKey("currrentGuessNum")) {
			long l2 = (Long) map.get("currrentGuessNum");
			gd.setCurrrentGuessNum((int) l2);
		}
		if (map.containsKey("deviceId")) {
			gd.setDeviceId((String) map.get("deviceId"));
		}
		syncData(gd);

	}

}
