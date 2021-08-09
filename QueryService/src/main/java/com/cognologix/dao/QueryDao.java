package com.cognologix.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.model.Query;
import com.cognologix.repository.QueryRepository;

@Repository
public class QueryDao implements IQueryDao {

	@Autowired
	private QueryRepository queryRepository;
	
	@Override
	public Query addQuery(Query query)throws QueryAlreadyExistsException {
		try {
			Query outputQuery = queryRepository.save(query);
			return outputQuery;
		} catch (Exception e) {
			throw new QueryAlreadyExistsException("Query Already Exists with identifier");
		}
	}

	@Override
	public String getQueryByIdentifier(String Identifier) throws QueryDoesNotExistsException {
		Query query = queryRepository.findByIdentifier(Identifier);
		if(query!=null)
			return query.getQuery();
		else
			throw new QueryDoesNotExistsException("Query not present for identifier"+Identifier);
	}
	
	@Override
	public Query putQueryByIdentifier(String Identifier,String inputquery) throws QueryDoesNotExistsException {
		Query query = queryRepository.findByIdentifier(Identifier);
		if(query!=null) {
			query.setQuery(inputquery);
			query=queryRepository.save(query);
			return query;
		}
		else
			throw new QueryDoesNotExistsException("Query not present for identifier"+Identifier);
		
	}
	
	@Override
	public void deleteQueryByIdentifier(String Identifier) throws QueryDoesNotExistsException {
		Query query = queryRepository.findByIdentifier(Identifier);
		if(query!=null)
			queryRepository.delete(query);
		else
			throw new QueryDoesNotExistsException("Query not present for identifier"+Identifier);
	}


}
