package com.cognologix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/query/{identifier}")
	public ResponseEntity<QueryVo> getQuery(@PathVariable(required = true) String identifier) {
		Query query = null;
		try {
			query = queryService.getQuery(identifier);
			return new ResponseEntity<>(new QueryVo(query.getQuery(), query.getIdentifier()), HttpStatus.OK);
		} catch (QueryDoesNotExistsException e) {
			return new ResponseEntity<QueryVo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QueryVo> addQuery(@RequestBody(required = true) QueryVo queryVo) {
		Query query = new Query(queryVo.getIdentifier(), queryVo.getQuery());
		ResponseEntity<Query> entity = new ResponseEntity<>(null, HttpStatus.OK);
		Query output = null;
		try {
			output = queryService.addQuery(query);
			return new ResponseEntity<QueryVo>(new QueryVo(output.getQuery(), output.getIdentifier()), HttpStatus.OK);
		} catch (QueryAlreadyExistsException e) {
			return new ResponseEntity<QueryVo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CrossOrigin(origins = "*")
	@PutMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QueryVo> updateQuery(@RequestBody(required = true) QueryVo queryVo) {
		Query output = null;
		try {
			output = queryService.updateQuery(queryVo.getIdentifier(), queryVo.getQuery());
			return new ResponseEntity<QueryVo>(new QueryVo(output.getQuery(), output.getIdentifier()), HttpStatus.OK);
		} catch (QueryDoesNotExistsException e) {
			return new ResponseEntity<QueryVo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/query/{identifier}")
	public ResponseEntity<QueryVo> deleteQuery(@PathVariable(required = true) String identifier) {
		try {
			queryService.deleteQuery(identifier);
			return new ResponseEntity<QueryVo>(HttpStatus.NO_CONTENT);
		} catch (QueryDoesNotExistsException e) {
			return new ResponseEntity<QueryVo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
