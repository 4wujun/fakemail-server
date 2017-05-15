package org.legurun.test.fakemailserver.model;

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
