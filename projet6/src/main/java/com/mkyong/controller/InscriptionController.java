package com.mkyong.controller;

import com.mkyong.beans.Utilisateur;
import com.mkyong.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InscriptionController {


    private Utilisateur utilisateur;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/inscription")
    public String inscription(@RequestParam(name = "erreur", required = false, defaultValue = "") String erreur, Model model){

        if(erreur.equals("")){
            utilisateur = new Utilisateur();
            model.addAttribute("utilisateur", utilisateur);
        } else {
            model.addAttribute("erreur", erreur);
            model.addAttribute("utilisateur", utilisateur);
        }

        return "inscription";
    }

    @PostMapping("/inscription")
    public ModelAndView inscription(@ModelAttribute Utilisateur utilisateur, @RequestParam(value = "confirmPassword") String confirmPassword){

        ModelAndView mv = new ModelAndView();
        this.utilisateur = utilisateur;
        System.out.println("Dans Post Inscription");

        this.utilisateur.setRole("USER");

        try{
            utilisateurService.createUtilisateur(utilisateur, confirmPassword);
            System.out.println("Sauvegarde réussie");
            mv.setViewName("redirect:/login");
        } catch (Exception e){
            System.out.println(e.getMessage());
            mv.addObject("erreur", e.getMessage());
            mv.setViewName("redirect:/inscription");
        }

        return mv;
    }
}
