package com.group3.Login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group3.BusinessModels.LoginForm;
import com.group3.CreateQuestion.DAO.DAOAbstractFactory;

@Controller
public class LoginController {

	@PostMapping(path = "/login")
	public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {
		String email = loginForm.getEmail();
		String password = loginForm.getPassword();
		model.addAttribute("invalidCredentials", true);
		return "login";
	}

}
