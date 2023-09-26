package com.srpingsecurity6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
//			.authorizeHttpRequests(t ->t.requestMatchers("/public").permitAll())
//			.formLogin(form->new FormLoginConfigurer<>().loginPage(null))
//			.formLogin();
//			return httpSecurity.build();
		
		.csrf(t ->t.disable())
		.authorizeHttpRequests(authorize ->authorize.anyRequest().authenticated()
		).httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.builder()
		.username("akshay")
		.password(passwordEncoder().encode("akshay"))
		.roles("USER")
		.build();
		
		
		UserDetails admin = User.builder()
		.username("amol")
		.password(passwordEncoder().encode("amol"))
		.roles("ADMIN")
		.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
}
