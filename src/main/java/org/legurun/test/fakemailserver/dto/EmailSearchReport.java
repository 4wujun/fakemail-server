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

package org.legurun.test.fakemailserver.dto;

import java.io.Serializable;
import java.util.Date;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Result of the emails search.
 * @author patrice
 * @since 2017
 */
@SuppressWarnings("serial")
public class EmailSearchReport implements Serializable {
	/**
	 * Identifier of the email.
	 */
	@SuppressWarnings("PMD.ShortVariable")
	private Long id;
	/**
	 * Sender of the email.
	 */
	private String sender;
	/**
	 * Recipient of the email.
	 */
	private String recipient;
	/**
	 * Date wich the email was send.
	 */
	private Date sentDate;
	/**
	 * Subject of the mail.
	 */
	private String subject;

	/**
	 * Empty constructor.
	 */
	public EmailSearchReport() {
		// NOOP
	}

	/**
	 * Constructor.
	 * @param id Identifier
	 * @param sender Sender
	 * @param recipient Recipient
	 * @param sentDate Sent date
	 * @param subject Subject
	 */
	@SuppressFBWarnings("EI_EXPOSE_REP2")
	@SuppressWarnings("PMD.ShortVariable")
	public EmailSearchReport(final Long id, final String sender,
			final String recipient, final Date sentDate,
			final String subject) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.sentDate = sentDate;
		this.subject = subject;
	}

	/**
	 * Get the identifier.
	 * @return Identifier
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the identifier.
	 * @param id Identifier
	 */
	@SuppressWarnings("PMD.ShortVariable")
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Get the sender address.
	 * @return Sender address
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Set the sender address.
	 * @param sender Sender address
	 */
	public void setSender(final String sender) {
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
	@SuppressFBWarnings("EI_EXPOSE_REP")
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * Set the sent date.
	 * @param sentDate Sent date
	 */
	@SuppressFBWarnings("EI_EXPOSE_REP2")
	public void setSentDate(final Date sentDate) {
		this.sentDate = sentDate;
	}

	/**
	 * Get the subject.
	 * @return Subject
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
}
