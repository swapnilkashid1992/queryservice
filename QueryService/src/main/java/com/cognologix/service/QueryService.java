package com.cognologix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognologix.dao.IQueryDao;
import com.cognologix.model.Query;

@Service
public class QueryService implements IQueryService {

	@Autowired
	private IQueryDao queryDao;
	
	@Override
	public Query addQuery(Query query) {
		return queryDao.addQuery(query);
	}

}
