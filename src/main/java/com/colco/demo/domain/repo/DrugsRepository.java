package com.colco.demo.domain.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.colco.demo.domain.Drugs;

public interface DrugsRepository extends MongoRepository<Drugs,String>{

}
