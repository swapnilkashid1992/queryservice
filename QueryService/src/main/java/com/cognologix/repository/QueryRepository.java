package com.cognologix.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognologix.model.Query;

public interface QueryRepository extends CrudRepository<Query,Integer> {

}
