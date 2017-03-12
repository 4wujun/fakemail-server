package org.legurun.test.fakemailserver.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EmailSearchReport implements Serializable {
	private Long id;
	private String sender;
	private String recipient;
	private Date dateSent;
	private String subject;

	public EmailSearchReport() {
	}

	public EmailSearchReport(Long id, String sender, String recipient, Date dateSent, String subject) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.dateSent = dateSent;
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

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
