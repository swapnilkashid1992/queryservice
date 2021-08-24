package com.cognologix.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognologix.model.Query;
import com.cognologix.service.QueryService;
import com.cognologix.vo.QueryVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class QueryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QueryService service;

	@InjectMocks
	private QueryController controller;

	@Test
	public void getQueryByIdentifierTest() throws Exception {
		String identifier = "getuser";
		Query q = new Query(identifier, "select * from Users");
		QueryVo vo = new QueryVo("select * from Users", identifier);
		when(service.getQuery(identifier)).thenReturn(q);
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.get("/api/query/{identifier}", identifier))
				.andExpect(status().isOk()).andReturn();
		String content = mvc.getResponse().getContentAsString();

		assertEquals("{\"query\":\"select * from Users\",\"identifier\":\"getuser\"}", content.trim());

		identifier = "";
		q = new Query(identifier, "select * from Users");
		mvc = mockMvc.perform(MockMvcRequestBuilders.get("/api/query/{identifier}", identifier))
				.andExpect(status().is4xxClientError()).andReturn();
		content = mvc.getResponse().getContentAsString();
	}

	@Test
	public void addQuery() throws JsonProcessingException, Exception {
		String identifier = "getuser";
		Query q = new Query(identifier, "select * from Users");
		QueryVo vo = new QueryVo("select * from Users", identifier);
		when(service.addQuery(q)).thenReturn(q);
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/api/query").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsBytes(vo))).andExpect(status().isOk()).andReturn();
		String content=mvc.getResponse().getContentAsString();
		assertEquals("{\"query\":\"select * from Users\",\"identifier\":\"getuser\"}", content.trim());
	}
	
	@Test
	public void putQuery() throws JsonProcessingException, Exception {
		String identifier = "getuser";
		Query q = new Query(identifier, "select * from Users");
		QueryVo vo = new QueryVo("select * from Users", identifier);
		when(service.updateQuery(identifier, vo.getQuery())).thenReturn(q);
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.put("/api/query").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsBytes(vo))).andExpect(status().isOk()).andReturn();
		String content=mvc.getResponse().getContentAsString();
		assertEquals("{\"query\":\"select * from Users\",\"identifier\":\"getuser\"}", content.trim());
	}
	
	@Test
	public void deleteQuery() throws Exception {
		String identifier = "getuser";
		MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.delete("/api/query/{identifier}", identifier))
				.andExpect(status().isNoContent()).andReturn();
	}
}
