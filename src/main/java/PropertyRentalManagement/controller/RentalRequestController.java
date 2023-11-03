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
import org.springframework.web.bind.annotation.RequestParam;

import PropertyRentalManagement.model.RentalRequestModel;
import PropertyRentalManagement.service.RentalRequestService;

@Controller
public class RentalRequestController {
	
	@Autowired
	private RentalRequestService rentalRequestService;
	
	@GetMapping("rentalRequest")
	public String rentalRequest(Model model,@RequestParam("rentalPackageId")long rentalPackageId,Principal principal) {
		RentalRequestModel rentalRequestModel = rentalRequestService.rentalRequest(rentalPackageId,principal.getName());
		model.addAttribute("rentalRequestModel", rentalRequestModel);
		return "rentalRequest";
	}
	@GetMapping("viewRequest")
	public String viewRequest(Model model,@RequestParam("houseId")String houseId,Principal principal,@AuthenticationPrincipal UserDetails userDetails)throws Exception {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
			List<RentalRequestModel> rentalRequestModelList = rentalRequestService.viewRequest(houseId,principal.getName(), grantedAuthority.getAuthority());
			model.addAttribute("rentalRequestModelList", rentalRequestModelList);
		}
		return "viewRequest";
	}
	@GetMapping("Accept")
	public String Accept(@RequestParam("rentalRequestId")long rentalRequestId,Model model) {
		String message=rentalRequestService.Accept(rentalRequestId);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
	@GetMapping("Reject")
	public String Reject(@RequestParam("rentalRequestId")long rentalRequestId,Model model) {
		String message=rentalRequestService.Reject(rentalRequestId);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
	
	@GetMapping("Cancel")
	public String Cancel(@RequestParam("rentalRequestId")long rentalRequestId,Model model) {
		String message=rentalRequestService.Cancel(rentalRequestId);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
}

