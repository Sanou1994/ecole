package com.gestion_ecole.ecole.entities;


public class OutboundSMSMessageRequest {
	private String address;
	private OutboundSMSTextMessage outboundSMSTextMessage;
	private String senderAddress;
	private String senderName;
	
	public String getAddress() {
		return "tel:+"+address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public OutboundSMSTextMessage getOutboundSMSTextMessage() {
		return outboundSMSTextMessage;
	}


	public void setOutboundSMSTextMessage(OutboundSMSTextMessage outboundSMSTextMessage) {
		this.outboundSMSTextMessage = outboundSMSTextMessage;
	}


	public String getSenderAddress() {
		return "tel:+"+senderAddress;
	}


	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}


	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	public OutboundSMSMessageRequest(String address, OutboundSMSTextMessage outboundSMSTextMessage,
			String senderAddress, String senderName) {
		super();
		this.address = address;
		this.outboundSMSTextMessage = outboundSMSTextMessage;
		this.senderAddress = senderAddress;
		this.senderName = senderName;
	}
}

