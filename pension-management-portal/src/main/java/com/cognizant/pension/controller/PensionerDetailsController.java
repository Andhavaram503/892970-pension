package com.cognizant.pension.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.pension.clients.PensionDetailsClient;
import com.cognizant.pension.model.PensionerInput;

@Controller
public class PensionerDetailsController {

	@Autowired
	private PensionDetailsClient pensionDetailsController;

	@GetMapping("/")
	public String index(Model model) {
		return "redirect:pensioners";
	}

	@GetMapping("/pensioners/{aadharNumber}")
	public String getPensionDetails(Model model, @PathVariable long aadharNumber) {
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

	@GetMapping("/pensioners")
	public String getPensioners(Model model) {
		model.addAttribute("pensionerInput1", new PensionerInput());
		model.addAttribute("pensioners", pensionDetailsController.getPensioners());
		if (model.getAttribute("pensioners") == null) {

			model.addAttribute("error", "No Pensioner Details Found");
		}
		return "pensioners";
	}

}
