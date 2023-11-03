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

import PropertyRentalManagement.model.TenentModel;
import PropertyRentalManagement.repository.TenentRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TenentDetailsService implements UserDetailsService {
	
	@Autowired
	private TenentRepository tenentRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<TenentModel> tenentModelList = tenentRepository.findByEmail(username);
		if(tenentModelList.size()==0) {
			throw new UsernameNotFoundException("invalid Tenent Email Address");
		}
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_TENENT");
		List<SimpleGrantedAuthority> grandAuthorities = new ArrayList<>();
		grandAuthorities.add(simpleGrantedAuthority);
		return new User(tenentModelList.get(0).getEmail(),tenentModelList.get(0).getPassword(),grandAuthorities);
	

	}
}
