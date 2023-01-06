package com.gestion_ecole.ecole.entities;

import java.io.Serializable;

public class Reponse implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String message;
private int code;
private Object result;


public Reponse(String message, int code, Object result) {
	super();
	this.message = message;
	this.code = code;
	this.result = result;
}



public Reponse() {
	super();
}



public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public Object getResult() {
	return result;
}

public void setResult(Object result) {
	this.result = result;
}


}
