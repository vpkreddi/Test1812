package com.colco.demo.domain.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.colco.demo.domain.MRepEntity;
import com.colco.demo.domain.dto.MRepDTO;

@Repository
public interface MRepRepository extends MongoRepository<MRepEntity,String>{

	@Query(value="{}",fields = "{id:1,name:1}")
	List<MRepDTO> findAllMRep();
}
