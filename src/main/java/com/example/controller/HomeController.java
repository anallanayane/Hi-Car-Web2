package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login/login")
	public String login() {
		return "login/login";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "user";
	}
	
	@RequestMapping("/perfil")
	public String perfil() {
		return "perfil";
	}
	
	@RequestMapping("/veiculos/meus_veiculos")
	public String veiculos() {
		return "veiculos/meus_veiculos";
	}
	
	@RequestMapping("/servicos/meus_servicos")
	public String servicos() {
		return "servicos/meus_servicos";
	}
	
}
