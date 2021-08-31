package com.cognologix.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.cognologix.exception.QueryException;
import com.cognologix.model.Query;
import com.cognologix.service.IQueryService;
import com.cognologix.vo.QueryVo;

@RestController
@RequestMapping("/api")
public class QueryController {

	final Logger log = LogManager.getLogger(this.getClass());
	@Autowired
	private IQueryService queryService;

	@GetMapping(path = "/query/{identifier}")
	public ResponseEntity<QueryVo> getQuery(@PathVariable final String identifier) {
		log.debug("Request to get query with identifier {}",identifier);
		try {
			final Query query = queryService.getQuery(identifier);
			return new ResponseEntity<>(new QueryVo(query.getQuery(), query.getIdentifier()), HttpStatus.OK);
		} catch(QueryDoesNotExistsException e) {
			log.error("Query Does not exist with identifier {} with exception {}",identifier,e);
			return new ResponseEntity<>(new QueryVo("Query Does not exist"), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (QueryException e) {
			log.error("Somthing happend wrong with identifier {} Please contact administrator. with exception {}",identifier,e);
			return  new ResponseEntity<>(new QueryVo("Somthing happend wrong"+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QueryVo> addQuery(@RequestBody final QueryVo queryVo) {
		log.debug("Request to add query with identifier {} and query is {}",queryVo.getIdentifier(),queryVo.getQuery());
		final Query query = new Query(queryVo.getIdentifier(), queryVo.getQuery());
		try {
			final Query output = queryService.addQuery(query);
			return new ResponseEntity<QueryVo>(new QueryVo(output.getQuery(), output.getIdentifier()), HttpStatus.OK);
		} catch (QueryAlreadyExistsException e) {
			log.error("Query already exists in database  with identifier {} with exception {}",queryVo.getIdentifier(),e);
			return new ResponseEntity<QueryVo>(new QueryVo("Query already exists in database"),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QueryVo> updateQuery(@RequestBody final  QueryVo queryVo) {
		log.debug("Request to update query with identifier {} and query is {}",queryVo.getIdentifier(),queryVo.getQuery());
		try {
			final Query output = queryService.updateQuery(queryVo.getIdentifier(), queryVo.getQuery());
			return new ResponseEntity<>(new QueryVo(output.getQuery(), output.getIdentifier()), HttpStatus.OK);
		} catch(QueryDoesNotExistsException e) {
			log.error("Query Does not exist with identifier {} with exception {}",queryVo.getIdentifier(),e);
			return new ResponseEntity<>(new QueryVo("Query Does not exist"), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (QueryException e) {
			log.error("Somthing happend wrong with identifier {} Please contact administrator. with exception {}",queryVo.getIdentifier(),e);
			return new ResponseEntity<>(new QueryVo("Somthing happend wrong"+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/query/{identifier}")
	public ResponseEntity<QueryVo> deleteQuery(@PathVariable final String identifier) {
		log.debug("Request to delete query with identifier {} ",identifier);
		try {
			queryService.deleteQuery(identifier);
			return new ResponseEntity<QueryVo>(HttpStatus.NO_CONTENT);
		} catch (QueryDoesNotExistsException e) {
			log.error("Query Does not exist with identifier {} with exception {}",identifier,e);
			return new ResponseEntity<QueryVo>(new QueryVo("Query Does not exist"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
