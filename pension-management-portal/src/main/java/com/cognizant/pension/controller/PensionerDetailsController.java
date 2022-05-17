package com.cognizant.pension.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.pension.clients.AuthenticationClient;
import com.cognizant.pension.clients.PensionDetailsClient;
import com.cognizant.pension.model.PensionerInput;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PensionerDetailsController {

	@Autowired
	private AuthenticationClient authenticationClient;

	@Autowired
	private PensionDetailsClient pensionDetailsController;

	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) {
		log.info("Started Index Page");
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {

					log.info("Authenticated Successfully");
					log.info("Generated Pensioner Details");
					return "redirect:pensioners";
				}
			} catch (Exception ex) {
				log.info("Login Invalid");
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}
		log.info("Ending Index Page");
		return "redirect:login";
	}

	@GetMapping("/pensioners/{aadharNumber}")
	public String getPensionDetails(Model model, @PathVariable long aadharNumber, HttpServletRequest request) {
		log.info("Started Retriving the pensioner Details by aadhar");
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {
					log.info("Authenticated Successfully");
					try {
						model.addAttribute("pensionerInput1", new PensionerInput());
						model.addAttribute("pensioner", pensionDetailsController.getDetails(aadharNumber));
						model.addAttribute("pensionerDateOfBirth", new SimpleDateFormat("MM/dd/yyyy")
								.format(pensionDetailsController.getDetails(aadharNumber).getDateOfBirth()));

						log.info("Details Found" );
					} catch (Exception e) {
						log.info("No Pensioner Found With Aadhar Number  : \" + aadharNumber");
						model.addAttribute("error", "No Pensioner Found With Aadhar Number  : " + aadharNumber);
						model.addAttribute("PensionerInput", new PensionerInput());
					}
					return "pensionerDetails";
				}
			} catch (Exception ex) {
				log.info("Session Expired Please Login Again..");
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}
		log.info("Authentication Failed");
		return "redirect:login";

	}

	@GetMapping("/pensioners")
	public String getPensioners(Model model, HttpServletRequest request) {

		log.info("Stated Pensioner Details Process");
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {
					log.info("Authenticated Successfully");
					model.addAttribute("pensionerInput1", new PensionerInput());
					model.addAttribute("pensioners", pensionDetailsController.getPensioners());
					if (model.getAttribute("pensioners") == null) {
						log.info("No Pensioner Details Found");
						model.addAttribute("error", "No Pensioner Details Found");
					}

					log.info("Pensioner Details Found");
					return "pensioners";
				}
			} catch (Exception ex) {
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}
		return "redirect:login";
	}

}
