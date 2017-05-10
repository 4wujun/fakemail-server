package org.legurun.test.fakemailserver.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EmailSearchReport implements Serializable {
	private Long id;
	private String sender;
	private String recipient;
	private Date sentDate;
	private String subject;

	public EmailSearchReport() {
	}

	public EmailSearchReport(final Long id, final String sender, final String recipient, final Date sentDate, final String subject) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.sentDate = sentDate;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(final String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(final String recipient) {
		this.recipient = recipient;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(final Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}
}
