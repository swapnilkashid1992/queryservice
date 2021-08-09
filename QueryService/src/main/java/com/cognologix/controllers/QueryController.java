package com.cognologix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognologix.exception.QueryAlreadyExistsException;
import com.cognologix.exception.QueryDoesNotExistsException;
import com.cognologix.model.Query;
import com.cognologix.service.IQueryService;
import com.cognologix.vo.QueryVo;

@RestController
@RequestMapping("/api")
public class QueryController {

	@Autowired
	private IQueryService queryService;
	
	@GetMapping(path = "/query/{identifier}")
	public String getQuery(@PathVariable(required = true) String identifier) {
		String query = null;
		try {
			query = queryService.getQuery(identifier);
		} catch (QueryDoesNotExistsException e) {
			e.printStackTrace();
		}
		return query;
	}

	@PostMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addQuery(@RequestBody(required = true) QueryVo queryVo) {
		Query query = new Query(queryVo.getIdentifier(), queryVo.getQuery());
		
		Query output=null;
		try {
			output = queryService.addQuery(query);
			return output.toString();
		} catch (QueryAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@PutMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateQuery(@RequestBody(required = true) QueryVo queryVo) {
		Query output=null;
		try {
			output=queryService.updateQuery(queryVo.getIdentifier(), queryVo.getQuery());
		} catch (QueryDoesNotExistsException e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	@DeleteMapping(path = "/query/{identifier}")
	public void deleteQuery(@PathVariable(required = true) String identifier) {
		try {
			queryService.deleteQuery(identifier);
		} catch (QueryDoesNotExistsException e) {
			e.printStackTrace();
		}
	}

}
