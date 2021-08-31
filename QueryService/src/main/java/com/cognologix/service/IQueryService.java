package com.cognologix.service;

import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.model.Query;

public interface IQueryService {

	Query addQuery(final Query query) throws QueryAlreadyExistsException;

	Query getQuery(final String identifier) throws QueryDoesNotExistsException;

	Query updateQuery(final String identifier,final  String query) throws QueryDoesNotExistsException;

	void deleteQuery(final String identifier) throws QueryDoesNotExistsException;
}
