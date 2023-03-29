package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserController {

	private final UserServiceImp userServiceImp;

	public UserController(UserServiceImp userServiceImp) {
		this.userServiceImp = userServiceImp;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			request.getSession().invalidate();
		}
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/user")
	public ModelAndView usersPage(Principal principal, Model model) {
		User user = (User) ((Authentication) principal).getPrincipal();
		model.addAttribute("admin", Role.ROLE_ADMIN);
		model.addAttribute("principal", user);
		return new ModelAndView("user_page");
	}

	@GetMapping("/")
	public ModelAndView loginController(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error_text", "Invalid email or password.");
		}
		return new ModelAndView("login");
	}
}

