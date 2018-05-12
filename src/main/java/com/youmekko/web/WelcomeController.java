package com.youmekko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.youmekko.domain.QuestionRepository;

@Controller
public class WelcomeController {
	
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("questions", questionRepository.findAll());
		return "index";
	}
	
	

}
