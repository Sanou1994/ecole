package com.gestion_ecole.ecole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Reponse {
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
	// TODO Auto-generated constructor stub
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
