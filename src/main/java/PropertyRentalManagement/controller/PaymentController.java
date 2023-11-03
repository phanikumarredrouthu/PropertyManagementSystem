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

import PropertyRentalManagement.model.PaymentModel;
import PropertyRentalManagement.model.RentalRequestModel;
import PropertyRentalManagement.service.PaymentService;
import PropertyRentalManagement.service.RentalRequestService;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RentalRequestService rentalRequestService;
	
	@GetMapping("payment")
	public String payment(Model model,@RequestParam("rentalRequestId")long rentalRequestId) {
		RentalRequestModel rentalRequestModel = rentalRequestService.getRentalRequest(rentalRequestId);
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setAmount(rentalRequestModel.getRentalPackageModel().getDepositeAmount());
		paymentModel.setRentalRequestId(rentalRequestId);
		model.addAttribute("rentalRequestId", rentalRequestId);
		model.addAttribute("rentalRequestModel", rentalRequestModel);
		model.addAttribute("paymentModel", paymentModel);
		return "payment";
	}
	@PostMapping("payment1")
	private String payment1(@ModelAttribute("paymentModel")PaymentModel paymentModel,Model model) {
		String message=paymentService.payment1(paymentModel);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
	
	@PostMapping("paymentRent1")
	private String paymentRent1(@ModelAttribute("paymentModel")PaymentModel paymentModel,Model model, @RequestParam("month") String month, @RequestParam("year") String year) {
		String message=paymentService.paymentRent1(paymentModel,month,year);
		model.addAttribute("message", message);
		return "redirect:/message?message="+message;
	}
	
	
	@GetMapping("viewpayment")
	public String viewpayment(@RequestParam("houseId")long rentalRequestId,@AuthenticationPrincipal UserDetails userDetails,Model model) {
	Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
	while (grantedAuthories.hasNext()) {
		GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
		model.addAttribute("role", grantedAuthority.getAuthority());
		List<PaymentModel>paymentList = paymentService.viewpayment(rentalRequestId);
		model.addAttribute("paymentList", paymentList);
	}
		return "viewpayment";

	}
	
	@GetMapping("paymentRent")
	public String paymentRent(Model model,@RequestParam("rentalRequestId")long rentalRequestId) {
		RentalRequestModel rentalRequestModel = rentalRequestService.getRentalRequest(rentalRequestId);
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setAmount(rentalRequestModel.getRentalPackageModel().getRentPerMonth());
		paymentModel.setRentalRequestId(rentalRequestId);
		model.addAttribute("rentalRequestId", rentalRequestId);
		model.addAttribute("rentalRequestModel", rentalRequestModel);
		model.addAttribute("paymentModel", paymentModel);
		return "paymentRent";
	}
	
}
