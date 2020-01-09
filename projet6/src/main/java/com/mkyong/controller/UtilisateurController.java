package com.mkyong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mkyong.beans.Utilisateur;
import com.mkyong.services.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	private Utilisateur utilisateur;
	
	public UtilisateurController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/allUtilisateurs")
	@PreAuthorize("hasAuthority('SUPER_MEMBER')")
	public String allUtilisateurs(Model model) {
    	System.out.println("Dans AllUtilisateurs");

		this.utilisateur = this.authentification();
		
		model.addAttribute("utilisateur", this.utilisateur);
		model.addAttribute("listUtilisateurs", this.utilisateurService.getAllUtilisateurs(this.utilisateur.getId()));
		
		return "liste-utilisateurs";
	}
	
	@GetMapping("/utilisateur-details")
	@PreAuthorize("hasAuthority('SUPER_MEMBER')")
	public String utilisateurDetails(@RequestParam(name = "utilisateurId", required = true) Long id, Model model) {
		
		Utilisateur utilisateurDetails = this.utilisateurService.findById(id);
		
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("utilisateurDetails", utilisateurDetails);
		
		return "utilisateur-details";
	}
	
	@PostMapping("/utilisateur-details")
	@PreAuthorize("hasAuthority('SUPER_MEMBER')")
	public String utilisateurDetails(@RequestParam(name = "id", required = true) Long id, @RequestParam(name = "role") String role) {
		
		Utilisateur u = this.utilisateurService.findById(id);
		u.setRole(role);
		this.utilisateurService.editUtilisateur(u);
		
		return "redirect:/allUtilisateurs";
	}
	
    private Utilisateur authentification() {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return this.utilisateurService.findByEmail(email);
    }

}
