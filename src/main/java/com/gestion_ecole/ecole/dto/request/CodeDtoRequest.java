package com.gestion_ecole.ecole.dto.request;

public class CodeDtoRequest {
	private int code;
	private String type;
	public CodeDtoRequest(int code, String type) {
		super();
		this.code = code;
		this.type = type;
	}
	
	
	public CodeDtoRequest(int code) {
		super();
		this.code = code;
	}


	public CodeDtoRequest() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
