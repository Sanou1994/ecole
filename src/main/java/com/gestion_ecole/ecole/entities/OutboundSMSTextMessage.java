package com.gestion_ecole.ecole.entities;

	public class OutboundSMSTextMessage {
		private String message;

		public OutboundSMSTextMessage(String message) {
			super();
			this.message = message;
		}

		public OutboundSMSTextMessage() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	}

