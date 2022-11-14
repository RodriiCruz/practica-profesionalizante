package com.practica.profesionalizante.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.profesionalizante.auth.entity.Usuario;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Lazy
    @Autowired
    AuthServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Usuario usuario = null;
    	
    	if (username.contains("@"))
    		usuario = userService.getUserByEmail(username).get();
    	else
    		usuario = userService.getUserByUsuario(username).get();
    	
        return UserPrincipal.build(usuario);
    }

}
