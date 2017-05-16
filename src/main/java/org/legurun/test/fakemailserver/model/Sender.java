package org.legurun.test.fakemailserver.model;

/*-
 * #%L
 * Fakemail server
 * %%
 * Copyright (C) 2017 Patrice Le Gurun
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "sender")
@JsonIgnoreProperties({ "emails" })
@Cacheable
public class Sender extends AbstractEntity {
	private String address;

	private Set<Email> emails = new HashSet<Email>();

	@Column(name = "address", nullable = false, unique = true)
	@NaturalId
	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@OneToMany(mappedBy = "sender")
	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(final Set<Email> emails) {
		this.emails = emails;
	}

	public void addEmail(final Email email) {
		this.emails.add(email);
		email.setSender(this);
	}

	public void removeEmail(final Email email) {
		this.emails.remove(email);
		email.setSender(null);
	}
}
