package com.practica.profesionalizante.auth.service.impl;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.practica.profesionalizante.auth.entity.Usuario;

public class UserPrincipal implements UserDetails {

    private final UUID id;
    private final String userName;
    private final String userPass;
    private final String userMail;


    public UserPrincipal(UUID id, String userName, String userPass, String userMail) {
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
        this.userMail = userMail;
    }

    public static UserPrincipal build(Usuario userEntity){
        return new UserPrincipal(
                userEntity.getId(),
                userEntity.getUsuario(),
                userEntity.getPassword(),
                userEntity.getEmail());
    }

    public  String getUserMail(){
        return this.userMail;
    }

    @Override
    public String getPassword() {
        return this.userPass;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UUID getId() {
        return id;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
