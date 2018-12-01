package com.example.controller;

import java.util.List;
import javax.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.model.Funcionario;
import com.example.model.Pessoa;
import com.example.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class CadFuncionarioController {

	private static final String MSG_SUCESS_INSERT = "Cadastro Realizado com Sucesso!";
	private static final String MSG_SUCESS_UPDATE = "Cadastro Atualizado!";
	private static final String MSG_SUCESS_DELETE = "Cadastro Excluído com Sucesso!";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping(value = "/indexFunc")
	public String index(Model model) {
		List<Funcionario> all = funcionarioService.findAll();
		model.addAttribute("listFuncionario", all);
		model.addAttribute("");
		return "funcionario/indexFunc";
	}

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Funcionario funcionario = funcionarioService.findOne(id).get();
			model.addAttribute("funcionario", funcionario);
		}
		return "funcionario/showFunc";
	}
	
	@GetMapping(value = "/cadFuncionario")
	public String create(Model model, @ModelAttribute Funcionario entityFuncionario) {
		return "funcionario/cadastro_funcionario";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Funcionario entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Funcionario funcionario = null;
		try {
			funcionario = funcionarioService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			System.out.println("Exception:: exception");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
		}catch (Throwable e) {
			System.out.println("Throwable:: exception");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
		}
		return "redirect:/funcionario/" + funcionario.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Funcionario entity = funcionarioService.findOne(id).get();
				model.addAttribute("funcionario", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "funcionario/cadastro_funcionario";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Funcionario entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Funcionario funcionario = null;
		try {
			funcionario = funcionarioService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/funcionario/" + funcionario.getId();
	}
	

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Funcionario entity = funcionarioService.findOne(id).get();
				funcionarioService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/funcionario/indexFunc";
	}

}
