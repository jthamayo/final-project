package io.github.jthamayo.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.jthamayo.backend.security.CustomUserDetailsServiceImpl;
import io.github.jthamayo.backend.security.JwtAuthenticationEntryPoint;
import io.github.jthamayo.backend.security.JwtAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    CustomUserDetailsServiceImpl customUserDetailsService;
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService,
	    JwtAuthenticationEntryPoint unauthorizedHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
	this.customUserDetailsService = customUserDetailsService;
	this.unauthorizedHandler = unauthorizedHandler;
	this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }

    @Bean
    PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	return authConfig.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.cors(withDefaults()).csrf(csrf -> csrf.disable())
		.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
		.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(requests -> requests
			.requestMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg",
				"/**/*.html", "/**/*.css", "/**/*.js")
			.permitAll().requestMatchers("/api/auth/**").permitAll()
			.requestMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
			.permitAll().requestMatchers(HttpMethod.GET, "/api/groups/**", "/api/users/**").permitAll()
			.anyRequest().authenticated());

	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	return http.build();
    }
}
