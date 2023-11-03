package PropertyRentalManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class EventSecurityConfiguration {
	
	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	@Bean
	UserDetailsService adminDetailsService() {
		UserDetails userDetails=User.builder().username("admin").password(getBCryptPasswordEncoder().encode("admin")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(userDetails);
	}
	@Bean
		UserDetailsService getHouseOwnerService() {
			return new HouseOwnersDetailsService();
	} 
	@Bean
		UserDetailsService TenentDetailsService() {
			return new TenentDetailsService();
	}
	
	
	
	
	
	
	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder  authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder
		.userDetailsService(adminDetailsService())
		.passwordEncoder(getBCryptPasswordEncoder())
		.and()
		.userDetailsService(getHouseOwnerService())
		.and()
		.userDetailsService(TenentDetailsService());
		
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		
		http.authorizeHttpRequests()
			.requestMatchers("/index", "/adminLogin", "/HouseOwnerLogin", "/tenentLogin","/tenentRegistration","/tenentRegistration1","HouseOwnerRegistration", "HouseOwnerRegistration1").permitAll()
			.requestMatchers("/**").authenticated()
			.and()
			.formLogin()
			.loginPage("/index?message=")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/welcome").permitAll()
			.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/index").permitAll()
			.and()
			.authenticationManager(authenticationManager)
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
			
		return http.build();
	}

}
