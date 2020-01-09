package com.mkyong.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.beans.Topo;
import com.mkyong.beans.Utilisateur;
import com.mkyong.repositories.TopoRepository;
import com.mkyong.services.TopoService;
import com.mkyong.services.UtilisateurService;

@RestController
@RequestMapping("/api")
public class TopoRestController {

	@Autowired TopoRepository topoRepository;
	
	@Autowired UtilisateurService utilisateurService;
	
	public TopoRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/topos-disponible/{id}")
	public List<Topo> toposDisponible(@PathVariable Long id){
		System.out.println("Dans restcontroller");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String email = userDetails.getUsername();
//        Utilisateur utilisateur = this.utilisateurService.findByEmail(email);
		Utilisateur utilisateur = this.utilisateurService.findById(id);
		return this.topoRepository.findByAuteurNotAndStatut(utilisateur, "disponible");
	}

}
