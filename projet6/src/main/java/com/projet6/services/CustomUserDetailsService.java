package com.projet6.services;

import com.projet6.beans.Utilisateur;
import com.projet6.repositories.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    public CustomUserDetailsService() {
    	super();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        GrantedAuthority authority = new SimpleGrantedAuthority(utilisateur.getRole());
        UserDetails userDetails = (UserDetails)new User(utilisateur.getEmail(),utilisateur.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
