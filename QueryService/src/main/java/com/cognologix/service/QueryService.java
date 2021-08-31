package com.cognologix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognologix.dao.IQueryDao;
import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.exception.QueryException;
import com.cognologix.model.Query;

@Service
public class QueryService implements IQueryService {

	@Autowired
	private IQueryDao queryDao;

	@Override
	public Query addQuery(final Query query) throws QueryAlreadyExistsException {
		return queryDao.addQuery(query);
	}

	@Override
	public Query getQuery(final String identifier) throws QueryDoesNotExistsException,QueryException {

		return queryDao.getQueryByIdentifier(identifier);
	}

	@Override
	public Query updateQuery(final String identifier, final String query) throws QueryDoesNotExistsException,QueryException {

		return queryDao.putQueryByIdentifier(identifier, query);
	}

	@Override
	public void deleteQuery(final String identifier) throws QueryDoesNotExistsException {
		queryDao.deleteQueryByIdentifier(identifier);
	}

}
