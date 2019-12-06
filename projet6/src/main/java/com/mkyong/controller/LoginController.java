package com.mkyong.controller;

import com.mkyong.beans.Utilisateur;
import com.mkyong.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    UtilisateurService utilisateurService;

    public LoginController(){
        //utilisateurService = new UtilisateurService();
    }

/*    @GetMapping("/login")
    public String identification(@RequestParam(name = "erreur", required = false, defaultValue = "") String erreur, Model model){
        System.out.println("Pas de message d'erreur");
        if(erreur.equals("")){
            System.out.println("Pas de message d'erreur");
        } else {
            System.out.println("Un message d'erreur");
            model.addAttribute("erreur", erreur);
        }
        return "login";
    }*/

    @GetMapping("/login")
    public String identification(Model model, String error, String logout){
        if(error != null){
            model.addAttribute("erreur", "bad username or password");
        }
        if(logout != null){
            System.out.println("Succès");
        }
        return "login";
    }

/*    @PostMapping("/login")
    public ModelAndView identification(@RequestParam(name = "email", required = false, defaultValue = "") String email,
                                       @RequestParam(name = "password", required = false, defaultValue = "") String password){

        ModelAndView mv = new ModelAndView();

        try{
            Utilisateur utilisateur = utilisateurService.logOk(email, password);
            mv.addObject("succes", "Vous êtes logué");
            mv.setViewName("redirect:/authenticated/home");
        } catch (Exception e) {
            mv.addObject("erreur", e.getMessage());
            mv.setViewName("redirect:/login");
        }

        return mv;
    }*/
}
