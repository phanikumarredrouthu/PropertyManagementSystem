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

import PropertyRentalManagement.model.ComplaintModel;
import PropertyRentalManagement.service.ComplaintService;

@Controller
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
	@GetMapping("risecomplaints")
	public String risecomplaints(Model model,@RequestParam("houseId")long rentalRequestId) {
		ComplaintModel complaintModel=new ComplaintModel();
		complaintModel.setRentalRequestId(rentalRequestId);
		model.addAttribute("rentalRequestId", rentalRequestId);
		model.addAttribute("complaintModel", complaintModel);
		return "risecomplaints";
	}
	@PostMapping("risecomplaints1")
	private String risecomplaints1(@ModelAttribute("complaintModel")ComplaintModel complaintModel,Model model) {
		String message=complaintService.risecomplaints1(complaintModel);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
	@GetMapping("viewcomplaints")
	public String viewcomplaints(@RequestParam("houseId")long houseId,@AuthenticationPrincipal UserDetails userDetails,Model model) {
	Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
	while (grantedAuthories.hasNext()) {
		GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
		model.addAttribute("role", grantedAuthority.getAuthority());
		List<ComplaintModel>complaintList = complaintService.viewcomplaints(houseId);
		model.addAttribute("complaintList", complaintList);
	}
		return "viewcomplaints";

	}
	@GetMapping("resolved")
	public String resolved(Model model,@RequestParam("complaintId")long complaintId) {
		ComplaintModel complaintModel= complaintService.getComplintModel(complaintId); 
		complaintModel.setComplaintId(complaintId);
		model.addAttribute("complaintId", complaintId);
		model.addAttribute("complaintModel", complaintModel);
		return "resolved";
	}
	@PostMapping("resolved1")
	private String resolved1(@RequestParam("complaintId")long complaintId, @RequestParam("solution")String solution,Model model) {
		String message=complaintService.resolved1(complaintId, solution);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	
	}
}

