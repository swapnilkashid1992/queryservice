package com.cognologix.vo;

import java.util.Objects;

import org.springframework.lang.NonNull;

public class QueryVo {
	@NonNull
	String query;
	@NonNull
	String identifier;

	public QueryVo(String query, String identifier) {
		super();
		this.query = query;
		this.identifier = identifier;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier, query);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryVo other = (QueryVo) obj;
		return Objects.equals(identifier, other.identifier) && Objects.equals(query, other.query);
	}

}
