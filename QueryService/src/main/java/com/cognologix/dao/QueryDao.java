package com.cognologix.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognologix.model.Query;
import com.cognologix.repository.QueryRepository;

@Repository
public class QueryDao implements IQueryDao {

	@Autowired
	private QueryRepository queryRepository;
	
	@Override
	public Query addQuery(Query query) {
		Query outputQuery = queryRepository.save(query);
		return outputQuery;
	}

}
