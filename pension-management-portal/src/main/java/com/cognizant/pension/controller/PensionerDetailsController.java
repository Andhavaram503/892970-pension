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

@Controller
public class PensionerDetailsController {

	@Autowired
	private AuthenticationClient authenticationClient;

	@Autowired
	private PensionDetailsClient pensionDetailsController;

	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {
					return "redirect:pensioners";
				}
			} catch (Exception ex) {
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}

		return "redirect:login";
	}

	@GetMapping("/pensioners/{aadharNumber}")
	public String getPensionDetails(Model model, @PathVariable long aadharNumber, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {
					try {
						model.addAttribute("pensionerInput1", new PensionerInput());
						model.addAttribute("pensioner", pensionDetailsController.getDetails(aadharNumber));
						model.addAttribute("pensionerDateOfBirth", new SimpleDateFormat("MM/dd/yyyy")
								.format(pensionDetailsController.getDetails(aadharNumber).getDateOfBirth()));
					} catch (Exception e) {
						model.addAttribute("error", "No Pensioner Found With Aadhar Number  : " + aadharNumber);
						model.addAttribute("PensionerInput", new PensionerInput());
					}
					return "pensionerDetails";
				}
			} catch (Exception ex) {
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}
		return "redirect:login";

	}

	@GetMapping("/pensioners")
	public String getPensioners(Model model, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {
					model.addAttribute("pensionerInput1", new PensionerInput());
					model.addAttribute("pensioners", pensionDetailsController.getPensioners());
					if (model.getAttribute("pensioners") == null) {
						model.addAttribute("error", "No Pensioner Details Found");
					}
					return "pensioners";
				}
			} catch (Exception ex) {
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}
		return "redirect:login";
	}

}
