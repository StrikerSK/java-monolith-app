package com.application.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class MainMenuController {

	@RequestMapping("")
	public String getMainMenu(){
		return "mainMenuPage";
	}

	@RequestMapping("/about")
	public String getPage(Locale locale, Model model){
		model.addAttribute("language", locale.toString());
		return "aboutPage";
	}

}
