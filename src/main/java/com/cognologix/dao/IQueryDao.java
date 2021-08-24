package com.cognologix.dao;

import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.model.Query;

public interface IQueryDao {

	Query addQuery(Query query) throws QueryAlreadyExistsException;

	Query getQueryByIdentifier(String Identifier) throws QueryDoesNotExistsException;

	Query putQueryByIdentifier(String Identifier, String inputquery) throws QueryDoesNotExistsException;

	void deleteQueryByIdentifier(String Identifier) throws QueryDoesNotExistsException;
}
