package com.surfm.numbergame;

public class TestData {
	private String name;
	private String pass;
	private int id;
	public TestData(String name, String pass, int id) {
		super();
		this.name = name;
		this.pass = pass;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TestData [name=" + name + ", pass=" + pass + ", id=" + id + "]";
	}
	
	
}
