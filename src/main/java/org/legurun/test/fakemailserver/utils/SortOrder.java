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

import org.springframework.core.convert.converter.Converter;

/**
 * Sort order.
 *
 * @author patrice
 * @since 2017
 */
public enum SortOrder {
	/**
	 * Ascending order.
	 */
	ASCENDING,
	/**
	 * Descending order.
	 */
	DESCENDING;

	/**
	 * Converter for SortOrder.
	 * @author patrice
	 * @since 2017
	 */
	public static class OrderConverter implements Converter<String, SortOrder> {
		/**
		 * Convert order string to enum.
		 * @param source Order string
		 * @return Enum
		 */
		@SuppressWarnings("checkstyle:returncount")
		@Override
		public SortOrder convert(final String source) {
			if ("asc".equals(source)) {
				return SortOrder.ASCENDING;
			}
			else if ("desc".equals(source)) {
				return SortOrder.DESCENDING;
			}
			else {
				return null;
			}
		}
	}
}
