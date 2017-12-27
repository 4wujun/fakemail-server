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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Command for the email search.
 * @author patlenain
 * @since 2017
 */
@SuppressWarnings("serial")
public class EmailSearchCommand implements Serializable {
	/**
	 * Sender id.
	 */
	private Long senderId;
	/**
	 * Recipient address.
	 */
	private String recipient;
	/**
	 * Date since the mail was sent.
	 */
	private Date sentSince;
	/**
	 * Date before the mails was sent.
	 */
	private Date sentBefore;
	/**
	 * First row number.
	 */
	private Integer firstRow;
	/**
	 * Max number of rows to fetch.
	 */
	private Integer maxRows;

	/**
	 * Get the sender id.
	 * @return Sender id
	 */
	public Long getSenderId() {
		return senderId;
	}

	/**
	 * Set the sender id.
	 * @param senderId Sender id
	 */
	public void setSenderId(final Long senderId) {
		this.senderId = senderId;
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
	 * Get the date since the mails was sent.
	 * @return Date since the mails was sent
	 */
	@SuppressFBWarnings("EI_EXPOSE_REP")
	public Date getSentSince() {
		return sentSince;
	}

	/**
	 * Set the date since the mails was sent.
	 * @param sentSince Date since the mails was sent
	 */
	@SuppressFBWarnings("EI_EXPOSE_REP2")
	public void setSentSince(final Date sentSince) {
		this.sentSince = sentSince;
	}

	/**
	 * Get the date before the mails was sent.
	 * @return Date before the mails was sent
	 */
	@SuppressFBWarnings("EI_EXPOSE_REP")
	public Date getSentBefore() {
		return sentBefore;
	}

	/**
	 * Set the date before the mails was sent.
	 * @param sentBefore Date before the mails was sent
	 */
	@SuppressFBWarnings("EI_EXPOSE_REP2")
	public void setSentBefore(final Date sentBefore) {
		this.sentBefore = sentBefore;
	}

	/**
	 * Get the first row number.
	 * @return Number
	 */
	public Integer getFirstRow() {
		return firstRow;
	}

	/**
	 * Set the first row number.
	 * @param firstRow Number
	 */
	public void setFirstRow(final Integer firstRow) {
		this.firstRow = firstRow;
	}

	/**
	 * Get the maximum number of rows to fetch.
	 * @return Maximum number
	 */
	public Integer getMaxRows() {
		return maxRows;
	}

	/**
	 * Set the maximum number of rows to fetch.
	 * @param maxRows Maximum number
	 */
	public void setMaxRows(final Integer maxRows) {
		this.maxRows = maxRows;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "EmailSearchCommand [senderId=" + senderId
				+ ", recipient=" + recipient + ", sentSince=" + sentSince
				+ ", sentBefore=" + sentBefore
				+ ", firstRow=" + firstRow
				+ ", maxRows=" + maxRows + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(senderId);
		builder.append(recipient);
		builder.append(sentBefore);
		builder.append(sentSince);
		builder.append(firstRow);
		builder.append(maxRows);
		return builder.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final EmailSearchCommand other = (EmailSearchCommand) obj;
		final EqualsBuilder builder = new EqualsBuilder();
		builder.append(senderId, other.senderId);
		builder.append(recipient, other.recipient);
		builder.append(sentBefore, other.sentBefore);
		builder.append(sentSince, other.sentSince);
		builder.append(firstRow, other.firstRow);
		builder.append(maxRows, other.maxRows);
		return builder.isEquals();
	}

	/**
	 * Return a pageable instance matching the request.
	 * @return Pageable instance
	 */
	public Pageable getPageable() {
		int pageIndex = 0;
		if (firstRow != null && maxRows != null) {
			pageIndex = firstRow / maxRows;
		}
		int pageSize = Integer.MAX_VALUE;
		if (maxRows != null) {
			pageSize = maxRows;
		}
		return new PageRequest(pageIndex, pageSize);
	}
}
