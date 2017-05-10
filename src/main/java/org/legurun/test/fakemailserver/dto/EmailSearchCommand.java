package org.legurun.test.fakemailserver.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EmailSearchCommand implements Serializable {
	private Long senderId;
	private String recipient;
	private Date sentSince;
	private Date sentBefore;

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(final Long senderId) {
		this.senderId = senderId;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(final String recipient) {
		this.recipient = recipient;
	}

	public Date getSentSince() {
		return sentSince;
	}

	public void setSentSince(final Date sentFrom) {
		this.sentSince = sentFrom;
	}

	public Date getSentBefore() {
		return sentBefore;
	}

	public void setSentBefore(final Date sentTo) {
		this.sentBefore = sentTo;
	}

}
