package PropertyRentalManagement.controller;

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

import PropertyRentalManagement.model.RentalPackageModel;
import PropertyRentalManagement.service.RentalPackageService;

@Controller
public class RentalPackagecontroller {
	
	@Autowired
	private RentalPackageService rentalPackageService;

	
	@GetMapping("rentalPackage")
	public String rentalPackage (Model model,@RequestParam("houseId")long houseId) {
		RentalPackageModel rentalPackageModel = new RentalPackageModel();
		rentalPackageModel.setHouseId(houseId);
		model.addAttribute("houseId",houseId);
		model.addAttribute("rentalPackageModel",rentalPackageModel);
		return "rentalPackage";	
	}
	@PostMapping("rentalPackage1")
	private String rentalPackage1(@ModelAttribute("rentalPackageModel")RentalPackageModel rentalPackageModel,Model model ) {
		String message = rentalPackageService.rentalPackage1(rentalPackageModel);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
	@GetMapping("viewPackage")
	public String viewPackage(@RequestParam("houseId")long houseId,@AuthenticationPrincipal UserDetails userDetails,Model model) {
	Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
	while (grantedAuthories.hasNext()) {
		GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
		model.addAttribute("role", grantedAuthority.getAuthority());
		List<RentalPackageModel>rentalPackageList = rentalPackageService.viewPackage(houseId);
		model.addAttribute("rentalPackageList", rentalPackageList);
	}
		return "viewPackage";
}
	
}
