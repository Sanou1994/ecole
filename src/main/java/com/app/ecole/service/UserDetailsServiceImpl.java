package com.app.ecole.service;
import java.util.ArrayList;
import java.util.Collection;

import com.app.ecole.entities.Utilisateur;
import com.app.ecole.repository.IDaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private IDaoUser iDaoUser;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user =iDaoUser.findByEmail(username).get();
		if(user == null) throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		User userNew = new User(
				user.getEmail(),
				user.getPassword(),
				authorities	
				);
		return userNew;
	}

}
