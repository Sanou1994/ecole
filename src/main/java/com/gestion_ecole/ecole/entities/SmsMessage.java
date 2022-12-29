package com.gestion_ecole.ecole.entities;

public class SmsMessage {
 private  OutboundSMSMessageRequest outboundSMSMessageRequest;
 
 
public SmsMessage(OutboundSMSMessageRequest outboundSMSMessageRequest) {
	this.outboundSMSMessageRequest = outboundSMSMessageRequest;
}


public SmsMessage() {
	super();
}


public OutboundSMSMessageRequest getOutboundSMSMessageRequest() {
	return outboundSMSMessageRequest;
}


public void setOutboundSMSMessageRequest(OutboundSMSMessageRequest outboundSMSMessageRequest) {
	this.outboundSMSMessageRequest = outboundSMSMessageRequest;
}



}


 