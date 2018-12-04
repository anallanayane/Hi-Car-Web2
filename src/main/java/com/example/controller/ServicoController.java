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
import com.example.model.Servico;
import com.example.service.ServicoService;

@Controller
@RequestMapping("/servico")
public class ServicoController {

	private static final String MSG_SUCESS_INSERT = "Cadastro Realizado com Sucesso!";
	private static final String MSG_SUCESS_UPDATE = "Cadastro Atualizado!";
	private static final String MSG_SUCESS_DELETE = "Cadastro Excluído com Sucesso!";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private ServicoService servicoService;
	
	@GetMapping(value = "/indexServ")
	public String index(Model model) {
		List<Servico> all = servicoService.findAll();
		model.addAttribute("listServico", all);
		model.addAttribute("");
		return "servicos/indexServ";
	}

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Servico servico = servicoService.findOne(id).get();
			model.addAttribute("servico", servico);
		}
		return "servicos/showServ";
	}
	
	@GetMapping(value = "/servicos")
	public String veiculo(Model model, @ModelAttribute Servico entityServico) {
		return "servicos/meus_servicos";
	}
	
	@GetMapping(value = "/cadServico")
	public String create(Model model, @ModelAttribute Servico entityServico) {
		return "servicos/cadastro_servico";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Servico entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Servico servico = null;
		try {
			servico = servicoService.save(entity);
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
		return "redirect:/servico/" + servico.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Servico entity = servicoService.findOne(id).get();
				model.addAttribute("servico", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "servicos/cadastro_servico";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Servico entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Servico servico = null;
		try {
			servico = servicoService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/servico/" + servico.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Servico entity = servicoService.findOne(id).get();
				servicoService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/servico/indexServ";
	}
	
	

}
