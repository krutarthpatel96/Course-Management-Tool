package com.group3.AdminAndAuthorization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminLogOutController {
	@RequestMapping("/adminLogout")
	String adminLogout() {
		return "login.html";
	}
}