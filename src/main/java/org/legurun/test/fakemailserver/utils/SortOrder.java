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

package org.legurun.test.fakemailserver.utils;

/**
 * Sort order.
 *
 * @author patlenain
 * @since 2017
 */
public final class SortOrder {
	/**
	 * Private constructor.
	 */
	private SortOrder() {
	}

	/**
	 * Test is sort order is ascending.
	 * @param order Order
	 * @return <code>true</code> if it's ascending sort order.
	 */
	public static boolean isAscending(final String order) {
		return "asc".equals(order);
	}

	/**
	 * Test is sort order is descending.
	 * @param order Order
	 * @return <code>true</code> if it's descending sort order.
	 */
	public static boolean isDescending(final String order) {
		return "desc".equals(order);
	}
}
