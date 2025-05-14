package io.github.jthamayo.backend.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
	    CustomUserDetailsServiceImpl customUserDetailsServiceImpl) {
	this.jwtTokenProvider = jwtTokenProvider;
	this.customUserDetailsServiceImpl = customUserDetailsServiceImpl;
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
	try {
	    String jwt = getJwtFromRequest(request);
	    if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {

		Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);

		UserDetails userDetails = customUserDetailsServiceImpl.loadUserById(userId);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	    }
	} catch (Exception ex) {
	    logger.error("Failed to set user auth context", ex);
	}
	filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
	String bearerToken = request.getHeader("Authorization");
	if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
	    return bearerToken.substring(7, bearerToken.length());
	}
	return null;

    }
}
