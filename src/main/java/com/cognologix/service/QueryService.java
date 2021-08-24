package com.cognologix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognologix.dao.IQueryDao;
import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.model.Query;

@Service
public class QueryService implements IQueryService {

	@Autowired
	private IQueryDao queryDao;

	@Override
	public Query addQuery(Query query) throws QueryAlreadyExistsException {
		return queryDao.addQuery(query);
	}

	@Override
	public Query getQuery(String identifier) throws QueryDoesNotExistsException {

		return queryDao.getQueryByIdentifier(identifier);
	}

	@Override
	public Query updateQuery(String identifier, String query) throws QueryDoesNotExistsException {

		return queryDao.putQueryByIdentifier(identifier, query);
	}

	@Override
	public void deleteQuery(String identifier) throws QueryDoesNotExistsException {
		queryDao.deleteQueryByIdentifier(identifier);
	}

}
