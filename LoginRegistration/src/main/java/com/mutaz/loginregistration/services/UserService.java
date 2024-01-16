package com.mutaz.loginregistration.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mutaz.loginregistration.models.LoginUser;
import com.mutaz.loginregistration.models.User;
import com.mutaz.loginregistration.repository.UserRepository;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	// TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
    	String passwordEntered = newUser.getPassword();
        // TO-DO: Additional validations!
    	if(!newUser.getPassword().equals(newUser.getConfirm()) ){
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	    
    	}
        if(result.hasErrors()) {
        	    // Exit the method and go back to the controller 
        	    // to handle the response
        	    return null;
        	}
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
 
        return userRepository.save(newUser);
    }
    public User login(LoginUser newLoginObject, BindingResult result) {
    	Optional<User> user = userRepository.findByEmail(newLoginObject.getEmail());
    	

    	String passwordEntered = newLoginObject.getPassword();
        // TO-DO - Reject values:
        
    	// Find user in the DB by email
        // Reject if NOT present
    	if(!user.isPresent()) {
    	    result.rejectValue("email", "Exists", "email doesnt exist");
    	    return null;
    	}
    	else {
    		User userToLogin = user.get();

    		// Reject if BCrypt password match fails
        	if(!BCrypt.checkpw(passwordEntered,(String) userToLogin.getPassword())) {
        	    result.rejectValue("password", "Matches", "Invalid Password!");
        	    return null;
        	}   		
    	}
        // Return null if result has errors
        // Otherwise, return the user object
        return user.get();
    }
    public User find_User(Long id) {
    	Optional<User> findUser = userRepository.findById(id);
    	return findUser.get();
    }

}