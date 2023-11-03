package PropertyRentalManagement.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PropertyRentalManagement.model.HouseModel;
import PropertyRentalManagement.model.HouseOwnerModel;
import PropertyRentalManagement.service.HouseOwnerService;

@Controller
public class HouseOwnerController {
	
	@Autowired
	private HouseOwnerService houseOwnerService;
	
	@GetMapping("index")
	public String index(@RequestParam("message") String message, Model model) {
		System.out.println(message);
		model.addAttribute("message", message);
		if(message!="") {
			
		}
		return "index";
	}
	@GetMapping("HouseOwnerLogin")
	public String HouseOwnerLogin() {
		return "HouseOwnerLogin";
	}
	
	
	@GetMapping("HouseOwnerRegistration")
	public String HouseOwnerRegistration(Model model) {
		HouseOwnerModel houseOwnerModel = new HouseOwnerModel();
		model.addAttribute("houseOwnerModel", houseOwnerModel);
		return "HouseOwnerRegistration";

	}
	@PostMapping("HouseOwnerRegistration1")
	public String HouseOwnerRegistration1(@ModelAttribute("houseOwnerModel") HouseOwnerModel houseOwnerModel,Model model) {
		String message=houseOwnerService.HouseOwnerRegistration1(houseOwnerModel);
		model.addAttribute("message",message );
		return "redirect:/message?message="+message;
	}
	@GetMapping("viewHouseowner")
	public String viewStaff(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
			List<HouseOwnerModel> houseOwnerModelList= houseOwnerService.getHouseOwner2();
			model.addAttribute("houseOwnerModelList",houseOwnerModelList);
		}
		return "viewHouseowner";
	}
	
	
	@GetMapping("houseownersHome")
	public String houseownerHome(Principal principal,Model model) {
		HouseOwnerModel houseOwnerModel =  houseOwnerService.getByEmail(principal.getName());
		model.addAttribute("houseOwnerModel", houseOwnerModel);
		return "houseownersHome";
	}
}
