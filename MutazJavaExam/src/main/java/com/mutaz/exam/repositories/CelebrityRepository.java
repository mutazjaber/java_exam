package com.mutaz.exam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mutaz.exam.models.Celebrity;

public interface CelebrityRepository extends CrudRepository<Celebrity , Long>  {

		List<Celebrity> findAll();
//		Optional<Celebrity> findByNameContaining(String search);
//		
		Optional<Celebrity> findCelebrityById(Long id);

	}

