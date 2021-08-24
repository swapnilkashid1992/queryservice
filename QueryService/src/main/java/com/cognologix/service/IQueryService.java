package com.cognologix.service;

import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.model.Query;

public interface IQueryService {

	Query addQuery(Query query) throws QueryAlreadyExistsException;

	Query getQuery(String identifier) throws QueryDoesNotExistsException;

	Query updateQuery(String identifier, String query) throws QueryDoesNotExistsException;

	void deleteQuery(String identifier) throws QueryDoesNotExistsException;
}
