package com.core;

import java.io.StringWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Payment {
	private double input;
	private double cash;
	
	public Payment(double input) {
		this.input = input;
	}
	
	public Payment(double input , double cash) {
		this(input);
		this.cash = cash;
	}
	
	public Payment(String json) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject)parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.input = Double.parseDouble(obj.get("input").toString());
		this.cash = Double.parseDouble(obj.get("change").toString());
	}

	public double getCash() {
		return cash;
	}


	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getInput() {
		return input;
	}


	public void setInput(double input) {
		this.input = input;
	}
	
	public double getChange(){
		return input - cash;
	}
	
	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
	    obj.put("input", Double.valueOf(input));
	    obj.put("cash", Double.valueOf(cash));
		return obj.toString();
	}
}
