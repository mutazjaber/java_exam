package com.mutaz.loginregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mutaz.loginregistration.models.LoginUser;
import com.mutaz.loginregistration.models.User;
import com.mutaz.loginregistration.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {
		
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
		model.addAttribute("newUser", new User());
		model.addAttribute("user", new LoginUser());
		
		return "login.jsp";
	}
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		// TO-DO Later -- call a register method in the service
		// to do some extra validations and create a new user!
		userService.register(newUser, result);
		if (result.hasErrors()) {
			// Be sure to send in the empty LoginUser before
			// re-rendering the page.

			model.addAttribute("user", new LoginUser());
			return "login.jsp";
		}
		else {
			session.setAttribute("loginId", newUser.getId());
			// No errors!
			// TO-DO Later: Store their ID from the DB in session,
			// in other words, log them in.
			return "redirect:/dashboard";
		}
				}
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("user") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        User user = userService.login(newLogin, result);   
        if(result.hasErrors()) {
        	model.addAttribute("newUser", new User());
            return "login.jsp";
        }
        else {
        	 session.setAttribute("loginId", user.getId());
             return "redirect:/dashboard";
        }     		
	}
		@GetMapping("/dashboard")
		public String dashboard(HttpSession session, Model model) {
			Long id =(Long) session.getAttribute( "loginId");
			 User user = userService.find_User(id);
			 model.addAttribute("email",user.getEmail());
			 model.addAttribute("userName",user.getUserName());
			System.out.println(user.getEmail());
			return "dashboard.jsp";
		}
		
		
		@GetMapping("/logout")
		public String logOut(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
	

}
