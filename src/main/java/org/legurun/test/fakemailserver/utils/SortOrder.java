package org.legurun.test.fakemailserver.utils;

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

import org.springframework.core.convert.converter.Converter;

public enum SortOrder {
	ASCENDING,
	DESCENDING;

	public static class OrderConverter implements Converter<String, SortOrder> {
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
