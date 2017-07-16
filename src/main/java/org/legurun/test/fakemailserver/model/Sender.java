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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Sender entity.
 *
 * @author patrice
 * @since 2017
 */
@SuppressWarnings("serial")
@SuppressFBWarnings("SE_NO_SERIALVERSIONID")
@Entity
@Table(name = "sender")
@JsonIgnoreProperties("emails")
@Cacheable
public class Sender extends AbstractEntity {
	/**
	 * Address.
	 */
	@Column(name = "address", nullable = false, unique = true)
	@NaturalId
	@org.hibernate.validator.constraints.Email
	@NotBlank
	@Length(max = 250)
	private String address;

	/**
	 * Emails sent by this sender.
	 */
	@OneToMany(mappedBy = "sender")
	private Set<Email> emails = new HashSet<Email>();

	/**
	 * Get the address.
	 * @return Adress
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address.
	 * @param address Address
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * Get the email list.
	 * @return Email list
	 */
	public Set<Email> getEmails() {
		return emails;
	}

	/**
	 * Set the email list.
	 * @param emails Email list
	 */
	public void setEmails(final Set<Email> emails) {
		this.emails = emails;
	}

	/**
	 * Add an email.
	 * @param email Email to add
	 */
	public void addEmail(final Email email) {
		this.emails.add(email);
		email.setSender(this);
	}

	/**
	 * Remove an email.
	 * @param email Email to remove
	 */
	public void removeEmail(final Email email) {
		this.emails.remove(email);
		email.setSender(null);
	}
}
