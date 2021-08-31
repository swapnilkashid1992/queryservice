package com.cognologix.vo;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
@NoArgsConstructor
public class QueryVo {
	@NonNull
	private String query;
	@NonNull
	private String identifier;
	
	private String error;
	
	public QueryVo(String query, String identifier) {
		super();
		this.query = query;
		this.identifier = identifier;
	}

	public QueryVo(String error) {
		this.error=error;
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

	
	public void setError(String error) {
		this.error = error;
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

	@Override
	public String toString() {
		return "QueryVo [query=" + query + ", identifier=" + identifier + "]";
	}
	
	
	
}
