package com.app.ecole.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.ecole.utils.Utility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type"+
		                   ",Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials, Authorization");
		if(request.getMethod().equals("OPTIONS"))
		{
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			
			String jwtToken =request.getHeader(Utility.HEADER_STRING);
			if(jwtToken == null  || !jwtToken.startsWith(Utility.TOKEN_PREFIX)){
				filterChain.doFilter(request, response);return ;
			}
			Claims claims = Jwts.parser()
					     .setSigningKey(Utility.SECRET)
					     .parseClaimsJws(jwtToken.replace(Utility.TOKEN_PREFIX, ""))
					     .getBody();
			String username =claims.getSubject();
		
			Collection<GrantedAuthority>authorities= new ArrayList<GrantedAuthority>();

			UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username,null,authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			filterChain.doFilter(request, response);
		}
	}
	

}
