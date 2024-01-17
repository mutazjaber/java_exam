package com.mutaz.exam.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mutaz.exam.models.Celebrity;
import com.mutaz.exam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<Celebrity> findCelebrityById(Long id);

	


	
}