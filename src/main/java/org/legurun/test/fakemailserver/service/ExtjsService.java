package org.legurun.test.fakemailserver.service;

import java.io.IOException;
import java.util.List;

import org.legurun.test.fakemailserver.utils.FilterExtjs;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExtjsService implements IExtjsService {

	@Override
	public List<FilterExtjs> convert(String filtersString)
			throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(filtersString, new TypeReference<List<FilterExtjs>>() {});
	}
}
