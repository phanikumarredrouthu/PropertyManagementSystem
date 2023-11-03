package PropertyRentalManagement.controller;

import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Welcome {
		
	@GetMapping("/welcome")
	public String welcome(@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			System.out.println(grantedAuthority.getAuthority());
			if(grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
				return "redirect:/adminHome";
			}else if(grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_HOUSEOWNER")) {
				return "redirect:/houseownersHome";
			}else if(grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_TENENT")) {
				return "redirect:/tenentHome";
			}
		}
		return "index";
	}

	@GetMapping("/message")
	public String displayMessage(@AuthenticationPrincipal UserDetails userDetails, Model model,@RequestParam("message") String message) {
		try {
			System.out.println("message page ******");
			model.addAttribute("message",message);
			Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
			int count = 0;
			while (grantedAuthories.hasNext()) {
				GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
				count ++;
				model.addAttribute("role", grantedAuthority.getAuthority());
				System.out.println(grantedAuthority.getAuthority());
			}
			if(count==0) {
				model.addAttribute("role", "Home");
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "message";
	}
}

