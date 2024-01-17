package com.mutaz.exam.controllers;


import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mutaz.exam.models.Celebrity;
import com.mutaz.exam.models.LoginUser;
import com.mutaz.exam.models.User;
import com.mutaz.exam.services.CelebrityService;
import com.mutaz.exam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private CelebrityService celebrityService;

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

		
		
		@GetMapping("/logout")
		public String logOut(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
		
		
		///////////////////////////////
		
		@GetMapping("/dashboard")

		public String dashboard (HttpSession session, Model model) {

			if (session.getAttribute("loginId") == null) {
				return "redirect:/";
			}
			Long id = (Long) session.getAttribute("loginId");
			User user = userService.find_User(id);
			model.addAttribute("email", user.getEmail());
			model.addAttribute("userName", user.getUserName());

			List<Celebrity> celebrities = celebrityService.ListCelebrities();
			for (Celebrity celebrity : celebrities) {
				System.out.println(celebrity.getId());
			}

			model.addAttribute("celebrities", celebrities);

			System.out.println(celebrities.isEmpty());
			System.out.println(user.getEmail());
			return "dashboard.jsp";
		}
		
		
		@GetMapping("/Celebrities/new")
		public String createCelebrityForm(Model model, @ModelAttribute("celebrity") Celebrity celebrity, HttpSession session) {
			Long id = (Long) session.getAttribute("loginId");
			User user = userService.find_User(id);
			model.addAttribute(user);
			return "newCelebrity.jsp";
		}
		
		@PostMapping("celebrities/createCelebrity")
		public String createCelebrity(@Valid @ModelAttribute("celebrity") Celebrity celebrity, BindingResult result, HttpSession session) {
			if (result.hasErrors()) {
				return "newCelebrity.jsp";
			}
			Long id = (Long) session.getAttribute("loginId");
			System.out.println(id);
			User user = userService.find_User(id);
			System.out.println(user.getEmail());
			celebrityService.create(celebrity, user);
			return "redirect:/dashboard";
		}
		
		@GetMapping("/celebrities/{celebrityId}")
		public String celebrityDetails(@PathVariable("celebrityId") Long celebrityId, HttpSession session, Model model) {
			Long id = (Long) session.getAttribute("loginId");
			User user = userService.find_User(id);
			Celebrity celebrity = celebrityService.findCelebrity(celebrityId);
			model.addAttribute("celebrity", celebrity);
			model.addAttribute("user", user);
			return "editCelebrity.jsp";
		}
				
		
		
		@GetMapping("/celebrities/{id}/edit")
		public String editCelebrity(@PathVariable("id") Long celebrityId, HttpSession session, Model model) {
			Long id = (Long) session.getAttribute("loginId");
			Celebrity celebrity = celebrityService.findCelebrity(celebrityId);
			System.out.println(!celebrity.getUser().getId().equals(id));
			if (!celebrity.getUser().getId().equals(id)) {
				return "celebrityDetails.jsp";
			}

			model.addAttribute("celebrity", celebrity);

			return "editCelebrity.jsp";
		}
		
		
		@RequestMapping(value = "/celebrities/{id}", method = RequestMethod.PATCH)
		public String update(@Valid @ModelAttribute("book") Celebrity celebrity, BindingResult result, Model model,
				HttpSession session) {

			Long loginId = (Long) session.getAttribute("loginId");
			User user = userService.find_User(loginId);

			if (result.hasErrors()) {

				model.addAttribute("celebrity", celebrity);
				return "editCelebrity.jsp";
			} else {

				celebrityService.updateCelebrity(celebrity, user);
				System.out.println(celebrity.getUser().getUserName());
				return "redirect:/dashboard";
			}
		}
		@DeleteMapping("/celebrities/{id}/delete")
		public String delete(@PathVariable("id") Long bookId, HttpSession session) {
			Long id = (Long) session.getAttribute("loginId");
			Celebrity celebrity = celebrityService.findCelebrity(id);
			if(!celebrity.getUser().getId().equals(id)) {
				return "redirect:/dashboard";
			}
			celebrityService.deleteCelebrity(bookId);
			return "redirect:/dashboard";
		}

	

}
