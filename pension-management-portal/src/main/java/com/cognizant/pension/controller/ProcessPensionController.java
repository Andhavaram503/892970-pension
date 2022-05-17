package com.cognizant.pension.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.pension.clients.AuthenticationClient;
import com.cognizant.pension.clients.PensionDetailsClient;
import com.cognizant.pension.clients.ProcessPensionClient;
import com.cognizant.pension.model.PensionDetail;
import com.cognizant.pension.model.PensionerDetails;
import com.cognizant.pension.model.PensionerInput;
import com.cognizant.pension.model.ProcessPensionInput;
import com.cognizant.pension.model.ProcessPensionResponse;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProcessPensionController {

	@Autowired
	private AuthenticationClient authenticationClient;

	@Autowired
	private PensionDetailsClient pensionDetailsController;

	@Autowired
	private ProcessPensionClient processPensionClient;

	@GetMapping("/pensionDetails")
	public String getPensionDetails(@RequestParam long aadharNumber, Model model, HttpServletRequest request) {
		log.info("Fetching Pension Details");
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {
					log.info("User Validated Successfully");
					PensionerDetails pensionerDetails = pensionDetailsController.getDetails(aadharNumber);
					PensionerInput pensionerInput = new PensionerInput(pensionerDetails.getName(),
							pensionerDetails.getDateOfBirth(), pensionerDetails.getPan(), Long.toString(aadharNumber),
							pensionerDetails.getPensionType());
					PensionDetail pensionDetails = processPensionClient.getPensionDetails(pensionerInput);
					model.addAttribute("pensionDetails", pensionDetails);
					model.addAttribute("aadharNumber", aadharNumber);
					model.addAttribute("pensionerInput1", new PensionerInput());
					return "pensionerDetails";
				}
			} catch (Exception ex) {
				log.info("Session Expired Please Login Again..");
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}
		return "redirect:login";
	}

	@PostMapping("/pensionDetails1")
	public String getPensionDetails(@ModelAttribute("pensionerInput1") PensionerInput pensionerInput1, Model model,
			HttpServletRequest request) {
		log.info("Fetch Penion Details");
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {
					log.info("User Validated Successfully");
					PensionDetail pensionDetails = processPensionClient.getPensionDetails(pensionerInput1);
					model.addAttribute("pensionerInput1", new PensionerInput());
					model.addAttribute("pensionDetails", pensionDetails);
					model.addAttribute("aadharNumber", pensionerInput1.getAadhar());
					log.info("Fetched Penion Details");
					return "pensionerDetails";
				}
			} catch (Exception ex) {
				log.info("Session Expired Please Login Again..");
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}
		return "redirect:login";
	}

	@GetMapping("/processPension/{aadharNumber}")
	public String processPension(@PathVariable long aadharNumber, Model model, HttpServletRequest request)
			throws Exception {
		log.info("Fetch Penion Details by aadhar.");
		String token = (String) request.getSession().getAttribute("token");
		if (token != null) {
			token = token.substring(7);
			ResponseEntity<?> result = null;
			try {
				result = authenticationClient.validateToken(token);
				if (result.getStatusCode() == HttpStatus.OK) {

					log.info("User Validated Successfully");
					PensionerDetails pensionerDetails = pensionDetailsController.getDetails(aadharNumber);
					PensionerInput pensionerInput = new PensionerInput(pensionerDetails.getName(),
							pensionerDetails.getDateOfBirth(), pensionerDetails.getPan(), Long.toString(aadharNumber),
							pensionerDetails.getPensionType());
					PensionDetail pensionDetails = processPensionClient.getPensionDetails(pensionerInput);
					ProcessPensionInput processPensionInput = new ProcessPensionInput(pensionerInput.getAadhar(),
							pensionDetails.getPensionAmount(), pensionerDetails.getBank().getBankType());
					ProcessPensionResponse processPensionResponse = processPensionClient
							.getProcessPension(processPensionInput);
					int statusCode = processPensionResponse.getStatus_code();
					if (statusCode == 10) {
						log.info("Amount credited successfully.");
						model.addAttribute("account", pensionerDetails.getBank().getAccountNo());
						model.addAttribute("message", statusCode);

					} else if (statusCode == 21) {
						log.info("Error while Processing the Pension Amount.");
						model.addAttribute("message", "Error while Processing the Pension Amount.");
					} else {
						log.info("Sorry An Error Occured.");
						model.addAttribute("message", "Sorry An Error Occured.");
					}
					model.addAttribute("pensionerInput1", new PensionerInput());
					model.addAttribute("pensioners", pensionDetailsController.getPensioners());
					if (model.getAttribute("pensioners") == null) {
						log.info("No Pensioner Details Found.");
						model.addAttribute("error", "No Pensioner Details Found");
					}
					return "index";
				}
			} catch (Exception ex) {	
				log.info("Session Expired Please Login Again...");
				model.addAttribute("error", "Session Expired Please Login Again..");
			}
		}

		return "redirect:login";
	}
}
