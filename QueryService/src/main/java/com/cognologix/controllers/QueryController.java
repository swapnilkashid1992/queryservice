package com.cognologix.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognologx.vo.QueryVo;

@RestController
@RequestMapping("/api")
public class QueryController {

	@GetMapping(path = "/query/{identifier}")
	public String getQuery(@PathVariable(required = true) String identifier) {
		return "Return null in Get";
	}

	@PostMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addQuery(@RequestBody(required = true) QueryVo queryVo) {
		return "Retun null from POST";
	}

	@PutMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateQuery(@RequestBody(required = true) QueryVo queryVo) {
		return "Return Null from put";
	}

	@DeleteMapping(path = "/query/{identifier}")
	public String deleteQuery(@PathVariable(required = true) String identifier) {
		return "Return Null from Delete";
	}

}
