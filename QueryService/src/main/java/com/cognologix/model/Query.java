package com.cognologix.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "query")
public class Query {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column
	String identifier;
	@Column
	String query;
	
	public Query() {
		// TODO Auto-generated constructor stub
	}
	
	public Query(int id, String identifier, String query) {
		super();
		this.id = id;
		this.identifier = identifier;
		this.query = query;
	}
	public Query( String identifier, String query) {
		this.identifier = identifier;
		this.query = query;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, identifier, query);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Query other = (Query) obj;
		return id == other.id && Objects.equals(identifier, other.identifier) && Objects.equals(query, other.query);
	}
	
	
	
}
