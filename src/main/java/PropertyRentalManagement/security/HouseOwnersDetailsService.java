package PropertyRentalManagement.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import PropertyRentalManagement.model.HouseOwnerModel;
import PropertyRentalManagement.repository.HouseOwnerRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HouseOwnersDetailsService implements UserDetailsService {
	@Autowired
	private HouseOwnerRepository houseOwnerRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<HouseOwnerModel> houseOwnerModelList = houseOwnerRepository.findByEmail(username);
		if(houseOwnerModelList.size()==0) {
			throw new UsernameNotFoundException("invalid Email Address");
		}
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_HOUSEOWNER");
		List<SimpleGrantedAuthority> grandAuthorities = new ArrayList<>();
		grandAuthorities.add(simpleGrantedAuthority);
		return new User(houseOwnerModelList.get(0).getEmail(),houseOwnerModelList.get(0).getPassword(),grandAuthorities);
	

	}
}
