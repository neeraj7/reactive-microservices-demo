package com.microservices.demo.multiplier.model;

public class NumberData {
	
	private int num;
	private int marks;
	
	public NumberData() {}
	public NumberData(int num) {
		this.num = num;
		this.marks = num+20;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

}
