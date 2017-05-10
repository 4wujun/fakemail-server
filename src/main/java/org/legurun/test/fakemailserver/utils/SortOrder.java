package org.legurun.test.fakemailserver.utils;

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
