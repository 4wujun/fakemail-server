package org.legurun.test.fakemailserver.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EmailSearchCommand implements Serializable {
	private Long senderId;
	private String recipient;

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
