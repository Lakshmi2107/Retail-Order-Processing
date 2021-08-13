package com.activemq.orderprocessing.controllers;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activemq.orderprocessing.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Value("${orderprocessing.jwtSecret}")
	private String secret;
	
	@PostMapping("/login")
	public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		String token = getJsonWebToken(username);
		User user = new User();
		user.setUsername(username);
		user.setToken(token);	
		return user;
		
	}

	private String getJsonWebToken(String username) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("securejwt")
				.setSubject(username)
				.claim("authorities",grantedAuthorities.stream()
	            .map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,secret.getBytes()).compact();

		return token;
	}
}
