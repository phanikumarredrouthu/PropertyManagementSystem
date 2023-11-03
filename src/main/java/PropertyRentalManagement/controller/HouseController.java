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
import org.springframework.web.multipart.MultipartFile;

import PropertyRentalManagement.model.HouseModel;
import PropertyRentalManagement.service.HouseService;

@Controller
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	
	@GetMapping("addhouse")
	public String addhouse(Model model) {
		HouseModel houseModel = new HouseModel();
		model.addAttribute("houseModel", houseModel);
		return "addhouse";
	}
	
	@PostMapping("addhouse1")
	public String addhouse1(@ModelAttribute("houseModel")HouseModel houseModel,Model model,@RequestParam("houseImage2")MultipartFile houseImage2,Principal principal) {
		String message=houseService.addhouse1(houseModel,houseImage2,principal.getName());
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
	
	@GetMapping("viewHouse")
	public String viewHouse(Model model,@AuthenticationPrincipal UserDetails userDetails,Principal principal,@RequestParam("type") String type) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
		List<HouseModel>houseModelList = houseService.getHouse2(grantedAuthority.getAuthority(),principal.getName(),type);
		model.addAttribute("houseModelList", houseModelList);
		}
		return "viewHouse";
	}
	@GetMapping("postRental")
	public String postRental(@RequestParam("houseId")long houseId,Model model) {
		String message=houseService.postRental(houseId);
		model.addAttribute("message", message);
		return "redirect:/viewHouse?type=all";
	}
}	
	
	
	
