package org.legurun.test.fakemailserver.service;

import java.io.IOException;
import java.util.List;

import org.legurun.test.fakemailserver.utils.FilterExtjs;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IExtjsService {
	public List<FilterExtjs> convert(String filtersString)
			throws JsonProcessingException, IOException;
}