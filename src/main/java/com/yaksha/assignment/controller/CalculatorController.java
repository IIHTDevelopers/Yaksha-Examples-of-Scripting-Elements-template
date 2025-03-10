package com.yaksha.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

	// Display the input form for numbers
	@GetMapping("/")
	public String showForm() {
		return "index";
	}

	// Process the numbers from URL and calculate their sum
	@GetMapping("/calculate")
	public String calculateSum(@RequestParam int num1, @RequestParam int num2, Model model) {
		int sum = num1 + num2;
		model.addAttribute("sum", sum);
		return "result";
	}
}
