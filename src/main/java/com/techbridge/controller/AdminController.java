package com.techbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techbridge.model.UserModel;

@Controller
public class AdminController {

	@Autowired
	private UserModel usermodelService;

	@RequestMapping("/admin")
	public String adminHome() {
		return "admin";
	}

	@RequestMapping("/addAccount")
	public String addAccount() {
		return "addAccount";
	}

}
