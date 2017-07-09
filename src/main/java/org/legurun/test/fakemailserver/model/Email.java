package org.legurun.test.fakemailserver.model;

/*
 * Copyright (C) 2017 Patrice Le Gurun
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Email entity.
 *
 * @author patrice
 * @since 2017
 */
@SuppressWarnings("serial")
@SuppressFBWarnings("SE_NO_SERIALVERSIONID")
@Entity
@Table(name = "email", indexes = {
	@Index(name = "message_recipient_idx", columnList = "recipient"),
	@Index(name = "message_sentdate_idx", columnList = "sent_date"),
	@Index(name = "message_subject_idx", columnList = "subject")
})
public class Email extends AbstractEntity {
	/**
	 * Sender.
	 */
	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	private Sender sender;
	/**
	 * Recipient address.
	 */
	@Column(name = "recipient", nullable = false, length = 250)
	private String recipient;
	/**
	 * Sent date.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sent_date", nullable = false)
	private Date sentDate;
	/**
	 * Subject.
	 */
	@Column(name = "subject", nullable = true, length = 250)
	private String subject;
	/**
	 * Full message.
	 */
	@Lob
	@Column(name = "message", nullable = false)
	private byte[] message;


	/**
	 * Get the sender.
	 * @return Sender
	 */
	public Sender getSender() {
		return sender;
	}

	/**
	 * Set the sender.
	 * @param sender Sender
	 */
	public void setSender(final Sender sender) {
		this.sender = sender;
	}

	/**
	 * Get the recipient address.
	 * @return Recipient address
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Set the recipient address.
	 * @param recipient Recipient address
	 */
	public void setRecipient(final String recipient) {
		this.recipient = recipient;
	}

	/**
	 * Get the sent date.
	 * @return Sent date
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * Set the sent date.
	 * @param sentDate Sent date
	 */
	public void setSentDate(final Date sentDate) {
		this.sentDate = sentDate;
	}

	/**
	 * Get the sent date.
	 * @return Sent date
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Set the subject.
	 * @param subject Subject
	 */
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	/**
	 * Get the full message.
	 * @return Message
	 */
	public byte[] getMessage() {
		return message;
	}

	/**
	 * Set the full message.
	 * @param message Message
	 */
	public void setMessage(final byte[] message) {
		this.message = message;
	}
}
