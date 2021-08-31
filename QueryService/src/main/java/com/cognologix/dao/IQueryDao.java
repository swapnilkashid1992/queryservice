package com.cognologix.dao;

import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.exception.QueryException;
import com.cognologix.model.Query;

public interface IQueryDao {

	Query addQuery(final Query query) throws QueryAlreadyExistsException;

	Query getQueryByIdentifier(final String Identifier) throws QueryDoesNotExistsException, QueryException;

	Query putQueryByIdentifier(final String Identifier,final  String inputquery) throws QueryDoesNotExistsException, QueryException;

	void deleteQueryByIdentifier(final String Identifier) throws QueryDoesNotExistsException;
}
