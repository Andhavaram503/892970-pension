package com.cognizant.pension.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.pension.clients.AuthenticationClient;
import com.cognizant.pension.model.AuthenticationRequest;
import com.cognizant.pension.model.User;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationClient authenticationClient;

	@GetMapping("/check")
	public String check() {
		return "check";
	}
	@GetMapping("/login")
	public String showLogin(Model model,HttpServletRequest req) {
		HttpSession session=req.getSession();
		session.setAttribute("token", null);
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute AuthenticationRequest user, HttpServletRequest req, Model model) {
		try {
			ResponseEntity<?> authenticationResponse = authenticationClient.createAuthenticationToken(user);
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> key = (LinkedHashMap<String, String>) authenticationResponse.getBody();
			HttpSession session = req.getSession();
			session.setAttribute("token", "Bearer " + key.get("jwt"));
			return "redirect:pensioners";
		} catch (Exception ex) {
			model.addAttribute("error", "Invalid Credientials");
			model.addAttribute("user", new User());
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session=req.getSession();
		session.setAttribute("token", null);
		return "redirect:pensioners";
	}
}
