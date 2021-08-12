package com.cognologix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.cognologix.repository.ResponseEntity;
import com.cognologix.service.IQueryService;
import com.cognologix.vo.QueryVo;

@RestController
@RequestMapping("/api")
public class QueryController {

	@Autowired
	private IQueryService queryService;

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/query/{identifier}")
	public ResponseEntity getQuery(@PathVariable(required = true) String identifier) {
		String query = null;
		ResponseEntity entity = new ResponseEntity();
		try {
			query = queryService.getQuery(identifier);
			entity.setOuput(query);
			entity.setStatus(HttpStatus.OK);
			return entity;
		} catch (QueryDoesNotExistsException e) {
			entity.setOuput(e.getMessage());
			entity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}

	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addQuery(@RequestBody(required = true) QueryVo queryVo) {
		Query query = new Query(queryVo.getIdentifier(), queryVo.getQuery());
		ResponseEntity entity = new ResponseEntity();
		Query output = null;
		try {
			output = queryService.addQuery(query);
			entity.setOuput(output);
			entity.setStatus(HttpStatus.OK);
			return entity;
		} catch (QueryAlreadyExistsException e) {
			entity.setOuput(e.getMessage());
			entity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}

	}

	@CrossOrigin(origins = "*")
	@PutMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateQuery(@RequestBody(required = true) QueryVo queryVo) {
		Query output = null;
		ResponseEntity entity = new ResponseEntity();
		try {
			output = queryService.updateQuery(queryVo.getIdentifier(), queryVo.getQuery());
			entity.setOuput(output);
			entity.setStatus(HttpStatus.OK);
			return entity;
		} catch (QueryDoesNotExistsException e) {
			entity.setOuput(e.getMessage());
			entity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping(path = "/query/{identifier}")
	public ResponseEntity deleteQuery(@PathVariable(required = true) String identifier) {
		ResponseEntity entity = new ResponseEntity();
		try {
			queryService.deleteQuery(identifier);
			entity.setOuput("Query Deleted");
			entity.setStatus(HttpStatus.OK);
			return entity;
		} catch (QueryDoesNotExistsException e) {
			entity.setOuput(e.getMessage());
			entity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}
	}

}
