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

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuthenticationController {

	@Autowired
	private AuthenticationClient authenticationClient;

	@GetMapping("/login")
	public String showLogin(Model model,HttpServletRequest req) {
		log.info("Starting To Show Login Page");
		HttpSession session=req.getSession();
		session.setAttribute("token", null);
		model.addAttribute("user", new User());
		log.info("Ending To Show Login Page");
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute AuthenticationRequest user, HttpServletRequest req, Model model) {
		log.info("Started Login Process");
		try {
			ResponseEntity<?> authenticationResponse = authenticationClient.createAuthenticationToken(user);
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> key = (LinkedHashMap<String, String>) authenticationResponse.getBody();
			HttpSession session = req.getSession();
			session.setAttribute("token", "Bearer " + key.get("jwt"));
			log.info("Login Done Successfully");
			return "redirect:pensioners";
		} catch (Exception ex) {
			model.addAttribute("error", "Invalid Credientials");
			model.addAttribute("user", new User());
			log.info("Login Failed");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		log.info("Started Logout Process");
		HttpSession session=req.getSession();
		session.setAttribute("token", null);
		log.info("Ending Logout Process");
		return "redirect:pensioners";
	}
}
