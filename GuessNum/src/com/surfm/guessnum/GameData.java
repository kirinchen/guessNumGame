package com.surfm.guessnum;

import java.util.Date;

public class GameData {

	private int finalAns;
	private int currrentGuessNum;
	private Date guessAt;
	private String deviceId;



	public int getFinalAns() {
		return finalAns;
	}

	public void setFinalAns(int finalAns) {
		this.finalAns = finalAns;
	}

	public int getCurrrentGuessNum() {
		return currrentGuessNum;
	}

	public void setCurrrentGuessNum(int currrentGuessNum) {
		this.currrentGuessNum = currrentGuessNum;
	}

	public Date getGuessAt() {
		return guessAt;
	}

	public void setGuessAt(Date guessAt) {
		this.guessAt = guessAt;
	}



	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
