package com.gestion_ecole.ecole.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestion_ecole.ecole.dto.response.UserDtoResponse;
import com.gestion_ecole.ecole.entities.Reponse;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
    private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		Reponse user =accountService.getUserByTelephone(phone);
		if(user == null) throw new UsernameNotFoundException(phone);
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		User userNew = new User(
				((UserDtoResponse) user.getResult()).getTelephone(),
				((UserDtoResponse) user.getResult()).getPassword(),
				authorities	
				);
		return userNew;
	}

}
