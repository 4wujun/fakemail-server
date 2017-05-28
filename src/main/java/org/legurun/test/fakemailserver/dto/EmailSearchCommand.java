package org.legurun.test.fakemailserver.dto;

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

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EmailSearchCommand implements Serializable {
	private Long senderId;
	private String recipient;
	private Date sentSince;
	private Date sentBefore;
	private Integer offset;
	private Integer limit;

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

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "EmailSearchCommand [senderId=" + senderId + ", recipient=" + recipient + ", sentSince=" + sentSince
				+ ", sentBefore=" + sentBefore + ", offset=" + offset + ", limit=" + limit + "]";
	}
}
