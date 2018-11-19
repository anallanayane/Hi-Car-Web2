package com.example.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Pessoa;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/* @RequestMapping("efetuaLogin")
	public String efetuaLogin(Pessoa pessoa, HttpSession session) {
	    if(new JdbcUsuarioDao().existeUsuario(pessoa)) {
	        session.setAttribute("usuarioLogado", pessoa);
	        return "menu";
	    }
	    return "redirect:loginForm";
	}*/
}