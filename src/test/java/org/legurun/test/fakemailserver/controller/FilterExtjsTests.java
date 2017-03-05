package org.legurun.test.fakemailserver.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.legurun.test.fakemailserver.utils.FilterExtjs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilterExtjsTests {

	@Test
	public void testDeserializeJsonWithNull() {
		String filtersString = "[{\"property\":\"senderId\",\"value\":null}]";
		ObjectMapper mapper = new ObjectMapper();
		List<FilterExtjs> filters;
		try {
			filters = mapper.readValue(filtersString, new TypeReference<List<FilterExtjs>>() {});
			assertNotNull(filters);
			assertEquals(1, filters.size());
			FilterExtjs filter = filters.get(0);
			assertNotNull(filter);
			assertEquals("senderId", filter.getProperty());
			assertEquals(null, filter.getValue());
		}
		catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeserializeJsonWithNumberValue() {
		String filtersString = "[{\"property\":\"senderId\",\"value\":2}]";
		ObjectMapper mapper = new ObjectMapper();
		List<FilterExtjs> filters;
		try {
			filters = mapper.readValue(filtersString, new TypeReference<List<FilterExtjs>>() {});
			assertNotNull(filters);
			assertEquals(1, filters.size());
			FilterExtjs filter = filters.get(0);
			assertNotNull(filter);
			assertEquals("senderId", filter.getProperty());
			assertEquals("2", filter.getValue());
		}
		catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeserializeJsonWithStringValue() {
		String filtersString = "[{\"property\":\"senderId\",\"value\":\"test\"}]";
		ObjectMapper mapper = new ObjectMapper();
		List<FilterExtjs> filters;
		try {
			filters = mapper.readValue(filtersString, new TypeReference<List<FilterExtjs>>() {});
			assertNotNull(filters);
			assertEquals(1, filters.size());
			FilterExtjs filter = filters.get(0);
			assertNotNull(filter);
			assertEquals("senderId", filter.getProperty());
			assertEquals("test", filter.getValue());
		}
		catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
