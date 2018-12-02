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
import com.example.model.OrdemServico;
import com.example.service.OSService;

@Controller
@RequestMapping("/os")
public class OSController {

	private static final String MSG_SUCESS_INSERT = "Cadastro Realizado com Sucesso!";
	private static final String MSG_SUCESS_UPDATE = "Cadastro Atualizado!";
	private static final String MSG_SUCESS_DELETE = "Cadastro Excluído com Sucesso!";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private OSService osService;
	
	@GetMapping(value = "/indexOS")
	public String index(Model model) {
		List<OrdemServico> all = osService.findAll();
		model.addAttribute("listOS", all);
		model.addAttribute("");
		return "servicos/indexOS";
	}

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			OrdemServico os = osService.findOne(id).get();
			model.addAttribute("os", os);
		}
		return "servicos/showOS";
	}
	
	@GetMapping(value = "/cadOS")
	public String create(Model model, @ModelAttribute OrdemServico entityOS) {
		return "servicos/abrir_os";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute OrdemServico entity, BindingResult result, RedirectAttributes redirectAttributes) {
		OrdemServico os = null;
		try {
			os = osService.save(entity);
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
		return "redirect:/os/" + os.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				OrdemServico entity = osService.findOne(id).get();
				model.addAttribute("os", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "servicos/abrir_os";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute OrdemServico entity, BindingResult result, RedirectAttributes redirectAttributes) {
		OrdemServico os = null;
		try {
			os = osService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/os/" + os.getId();
	}
	

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				OrdemServico entity = osService.findOne(id).get();
				osService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/os/indexOS";
	}

}
