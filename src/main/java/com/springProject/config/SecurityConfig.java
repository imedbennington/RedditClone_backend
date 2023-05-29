package com.springProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@SuppressWarnings({ "deprecation", "removal" })
	public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	            .authorizeRequests()
	            .requestMatchers("/register/**").permitAll()
	            .requestMatchers("/index").permitAll()
	            .requestMatchers("/viewusers").hasRole("ADMIN")
	            .and()
	            .formLogin(
	                    form -> form
	                            .loginPage("/login")
	                            .loginProcessingUrl("/login")
	                            .defaultSuccessUrl("/viewusers")
	                            .permitAll()
	            ).logout(
	                    logout -> logout
	                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                            .permitAll()

	            );
	    return http.build();
}
}