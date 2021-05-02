package com.application.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainMenuController {

	@RequestMapping("")
	public String getMainMenu(){
		return "mainMenuPage";
	}

}
