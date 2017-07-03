package org.legurun.test.fakemailserver.utils;

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

import java.util.List;

/**
 * Paginated list.
 *
 * @author patrice
 * @param <E> Data type
 * @since 2017
 */
public class PagedList<E> {
	/**
	 * Data.
	 */
	private List<E> data;
	/**
	 * Total number of data.
	 */
	private Number total = 0;


	/**
	 * Get data.
	 * @return Data
	 */
	public List<E> getData() {
		return data;
	}

	/**
	 * Set data.
	 * @param data Data
	 */
	public void setData(final List<E> data) {
		this.data = data;
	}

	/**
	 * Get the total number of data.
	 * @return Total number
	 */
	public Number getTotal() {
		return total;
	}

	/**
	 * Set the total number of data.
	 * @param total Total number
	 */
	public void setTotal(final Number total) {
		this.total = total;
	}
}
