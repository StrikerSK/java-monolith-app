package com.application.frontend.controller;

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

	@RequestMapping("/random")
	public String getPage(Model model){
		model.addAttribute("websiteLink", "Custom video");
		return "sampleVideoPage";
	}
}
