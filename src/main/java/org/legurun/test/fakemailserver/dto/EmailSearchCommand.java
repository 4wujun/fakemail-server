package org.legurun.test.fakemailserver.dto;

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

import java.io.Serializable;
import java.util.Date;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

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

	@SuppressFBWarnings("EI_EXPOSE_REP")
	public Date getSentSince() {
		return sentSince;
	}

	@SuppressFBWarnings("EI_EXPOSE_REP2")
	public void setSentSince(final Date sentFrom) {
		this.sentSince = sentFrom;
	}

	@SuppressFBWarnings("EI_EXPOSE_REP")
	public Date getSentBefore() {
		return sentBefore;
	}

	@SuppressFBWarnings("EI_EXPOSE_REP2")
	public void setSentBefore(final Date sentTo) {
		this.sentBefore = sentTo;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(final Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(final Integer limit) {
		this.limit = limit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "EmailSearchCommand [senderId=" + senderId
				+ ", recipient=" + recipient + ", sentSince=" + sentSince
				+ ", sentBefore=" + sentBefore
				+ ", offset=" + offset + ", limit=" + limit + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "PMD.NPathComplexity",
		"checkstyle:AvoidInlineConditionals" })
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((limit == null) ? 0 : limit.hashCode());
		result = prime * result
				+ ((offset == null) ? 0 : offset.hashCode());
		result = prime * result
				+ ((recipient == null) ? 0 : recipient.hashCode());
		result = prime * result
				+ ((senderId == null) ? 0 : senderId.hashCode());
		result = prime * result
				+ ((sentBefore == null) ? 0 : sentBefore.hashCode());
		result = prime * result
				+ ((sentSince == null) ? 0 : sentSince.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "PMD.CyclomaticComplexity",
		"PMD.StdCyclomaticComplexity", "PMD.ModifiedCyclomaticComplexity",
		"PMD.NPathComplexity", "checkstyle:CyclomaticComplexity",
		"checkstyle:NPathComplexity"})
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
		if (limit == null) {
			if (other.limit != null) {
				return false;
			}
		}
		else if (!limit.equals(other.limit)) {
			return false;
		}
		if (offset == null) {
			if (other.offset != null) {
				return false;
			}
		}
		else if (!offset.equals(other.offset)) {
			return false;
		}
		if (recipient == null) {
			if (other.recipient != null) {
				return false;
			}
		}
		else if (!recipient.equals(other.recipient)) {
			return false;
		}
		if (senderId == null) {
			if (other.senderId != null) {
				return false;
			}
		}
		else if (!senderId.equals(other.senderId)) {
			return false;
		}
		if (sentBefore == null) {
			if (other.sentBefore != null) {
				return false;
			}
		}
		else if (!sentBefore.equals(other.sentBefore)) {
			return false;
		}
		if (sentSince == null) {
			if (other.sentSince != null) {
				return false;
			}
		}
		else if (!sentSince.equals(other.sentSince)) {
			return false;
		}
		return true;
	}
}
