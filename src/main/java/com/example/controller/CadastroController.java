package com.example.controller;

import java.util.List;
import javax.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.model.Pessoa;
import com.example.service.PessoaService;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

	private static final String MSG_SUCESS_INSERT = "Cadastro Realizado com Sucesso!";
	private static final String MSG_SUCESS_UPDATE = "Cadastro Atualizado!";
	private static final String MSG_SUCESS_DELETE = "Cadastro Excluído com Sucesso!";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping(value = "/index")
	public String index(Model model) {
		List<Pessoa> all = pessoaService.findAll();
		model.addAttribute("listPessoa", all);
		model.addAttribute("");
		return "pessoa/index";
	}

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Pessoa pessoa = pessoaService.findOne(id).get();
			model.addAttribute("pessoa", pessoa);
		}
		return "pessoa/show";
	}
	
	@GetMapping(value = "/cadPessoa")
	public String create(Model model, @ModelAttribute Pessoa entityPessoa) {
		return "cadastro/cadastro_pessoa";
	}
	
	
	@PostMapping
	public String create(@Valid @ModelAttribute Pessoa entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Pessoa pessoa = null;
		try {
			pessoa = pessoaService.save(entity);
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
		return "redirect:/cadastro/" + pessoa.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Pessoa entity = pessoaService.findOne(id).get();
				model.addAttribute("pessoa", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "cadastro/cadastro_pessoa";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Pessoa entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Pessoa pessoa = null;
		try {
			pessoa = pessoaService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/cadastro/" + pessoa.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Pessoa entity = pessoaService.findOne(id).get();
				pessoaService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/cadastro/index";
	}

}
