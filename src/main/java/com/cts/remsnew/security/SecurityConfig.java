package com.remsnew.security;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticatorfilter;


	    @SuppressWarnings({ "deprecation", "removal" })
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .csrf().disable()
	                .authorizeRequests()
	                .requestMatchers("/api/register").permitAll()
	                .requestMatchers("/api/login").permitAll()
	                .requestMatchers("/api/v1/rems/services/forall/**").permitAll()
	                .requestMatchers("/api/v1/rems/services/forboth/**").hasAnyAuthority("Owner", "Broker")
	                .requestMatchers("/api/v1/rems/services/owner/**").hasAuthority("Owner")
	                .requestMatchers("/api/v1/rems/services/broker/**").hasAuthority("Broker")
	                .requestMatchers("/api/v1/rems/services/tenant/**").hasAuthority("Tenant")
	                .anyRequest().authenticated()
	                .and()
	                .addFilterBefore(jwtAuthenticatorfilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    
	   
	    @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
	    }
	    
}
