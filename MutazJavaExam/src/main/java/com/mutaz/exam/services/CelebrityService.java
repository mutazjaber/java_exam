package com.mutaz.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutaz.exam.models.Celebrity;
import com.mutaz.exam.models.User;
import com.mutaz.exam.repositories.CelebrityRepository;
import com.mutaz.exam.repositories.UserRepository;

@Service
public class CelebrityService {

	@Autowired
	private CelebrityRepository celebrityRepository;
	@Autowired
	private UserRepository userRepository;
	

	public void create(Celebrity celebrity ,User user ) {
		celebrity.setUser(user);
		celebrityRepository.save(celebrity);
	}

	public void deleteCelebrity(Long id) {
		Celebrity celebrity = celebrityRepository.findById(id).get();
		celebrityRepository.delete(celebrity);
	}

	public List<Celebrity> ListCelebrities() {
		List<Celebrity> findcelebrities = celebrityRepository.findAll();
		return findcelebrities;
	}

	public void updateCelebrity(Celebrity celebrity, User user) {
		celebrity.setUser(user);
		celebrityRepository.save(celebrity);
	}
	public Celebrity findCelebrity(Long id) {
		Celebrity celebrity = celebrityRepository.findCelebrityById(id).get();
		return celebrity;
	}

}


