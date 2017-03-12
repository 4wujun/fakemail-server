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

	public EmailSearchReport(Long id, String sender, String recipient, Date sentDate, String subject) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.sentDate = sentDate;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
