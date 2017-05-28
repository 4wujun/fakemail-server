package org.legurun.test.fakemailserver.utils;

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

import java.util.List;

public class PagedList<E> {
	private List<E> data;
	private Number total = 0;


	public List<E> getData() {
		return data;
	}

	public void setData(final List<E> data) {
		this.data = data;
	}

	public Number getTotal() {
		return total;
	}

	public void setTotal(final Number total) {
		this.total = total;
	}
}
