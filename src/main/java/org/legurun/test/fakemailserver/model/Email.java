package org.legurun.test.fakemailserver.model;

/*******************************************************************************
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
 ******************************************************************************/

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

@SuppressWarnings("serial")
@Entity
@Table(name = "email", indexes = {
	@Index(name = "message_recipient_idx", columnList = "recipient"),
	@Index(name = "message_sentdate_idx", columnList = "sent_date"),
	@Index(name = "message_subject_idx", columnList = "subject")
})
public class Email extends AbstractEntity {
	private Sender sender;
	private String recipient;
	private Date sentDate;
	private String subject;
	private byte[] message;

	public Email() {
		super();
	}

	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	public Sender getSender() {
		return sender;
	}

	public void setSender(final Sender sender) {
		this.sender = sender;
	}

	@Column(name = "recipient", nullable = false, length = 250)
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(final String recipient) {
		this.recipient = recipient;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sent_date", nullable = false)
	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(final Date sentDate) {
		this.sentDate = sentDate;
	}

	@Column(name = "subject", nullable = true, length = 250)
	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@Lob
	@Column(name = "message", nullable = false)
	public byte[] getMessage() {
		return message;
	}

	public void setMessage(final byte[] message) {
		this.message = message;
	}
}
