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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Common class for persisted entities.
 *
 * @author patrice
 * @since 2017
 */
@SuppressWarnings("serial")
@MappedSuperclass
@JsonIgnoreProperties({ "version", "dateCreated", "lastUpdated" })
public abstract class AbstractEntity implements Serializable {
	/**
	 * Identifier.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SuppressWarnings("PMD.ShortVariable")
	private Long id;
	/**
	 * Version number for optimistic locking.
	 */
	@Version
	@Column(name = "version", nullable = false)
	private Long version;
	/**
	 * Creation date.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false, insertable = true,
		updatable = false)
	private Date dateCreated;
	/**
	 * Last modification date.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", nullable = false, insertable = true,
		updatable = true)
	private Date lastUpdated;

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
	 * Get the entity version.
	 * @return Version number
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Set the entity version.
	 * @param version Version number
	 */
	public void setVersion(final Long version) {
		this.version = version;
	}

	/**
	 * Get the creation date.
	 * @return Creation date
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Set the creation date.
	 * @param dateCreated Creation date
	 */
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Get the last modification date.
	 * @return Last modification date
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * Set the modification date.
	 * @param lastUpdated Modification date
	 */
	public void setLastUpdated(final Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@PrePersist
	public void onCreate() {
		this.dateCreated = new Date();
		this.lastUpdated = this.dateCreated;
	}

	@PreUpdate
	public void onUpdate() {
		this.lastUpdated = new Date();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//CHECKSTYLE:OFF
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		//CHECKSTYLE:ON
		return result;
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
		final AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		}
		else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + "]";
	}
}
