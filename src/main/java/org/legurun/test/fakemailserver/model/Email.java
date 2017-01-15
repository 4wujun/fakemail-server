package org.legurun.test.fakemailserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@SuppressWarnings("serial")
@Entity
@Table(name="message", indexes={
	@Index(name="message_sender_idx", columnList="sender"),
	@Index(name="message_recipient_idx", columnList="recipient")
})
public class Email implements Serializable {
	private Long id;
	private Long version;
	private Date dateCreated;
	private Date lastUpdated;
	private String sender;
	private String recipient;
	private byte[] message;

	@Id
	@Column(name="id")
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	@Column(name="version", nullable=false)
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created", nullable=false)
	@CreationTimestamp
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated", nullable=false)
	@UpdateTimestamp
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name="sender", nullable=false)
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name="recipient", nullable=false)
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Lob
	@Column(name="message", nullable=false)
	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}
}
