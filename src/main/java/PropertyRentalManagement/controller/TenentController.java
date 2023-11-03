package PropertyRentalManagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import PropertyRentalManagement.model.TenentModel;
import PropertyRentalManagement.service.TenentService;

@Controller
public class TenentController {
	@Autowired
	private TenentService tenentService;
	
	@GetMapping("adminLogin")
	public String adminLogin() {
		return "adminLogin";
	}
	@GetMapping("adminHome")
	public String adminHome() {
		return "adminHome";
	}
	
	
	@GetMapping("tenentLogin")
	public String tenentLogin() {
		return "tenentLogin";
	}
	
	@GetMapping("tenentRegistration")
	public String tenentRegistration(Model model) {
		TenentModel tenentModel = new TenentModel();
		model.addAttribute("tenentModel", tenentModel);
		return "tenentRegistration";

	}
	@PostMapping("tenentRegistration1")
	public String tenentRegistration1(@ModelAttribute("tenentModel") TenentModel tenentModel,Model model) {
		System.out.println("inside");
		String message=tenentService.tenentRegistration(tenentModel);
		model.addAttribute("message",message );
		return "redirect:/message?message="+message;
	
	}
	@GetMapping("tenentHome")
	public String tenentHome(Principal principal,Model model) {
		TenentModel tenentModel = tenentService.getByEmail(principal.getName());
		model.addAttribute("tenentModel", tenentModel);
		return "tenentHome";
	}

}
