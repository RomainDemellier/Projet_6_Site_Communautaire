package com.mkyong.controller;

import com.mkyong.beans.Site;
import com.mkyong.beans.Utilisateur;
import com.mkyong.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartagerSiteController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private PaysService paysService;

    @Autowired
    private DepartementService departementService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CotationService cotationService;
    
    @Autowired
    private UtilisateurService utilisateurService;

    private Site site;
    
    private Utilisateur utilisateur;

    @GetMapping("/partager-site")
    @PreAuthorize("isAuthenticated()")
    public String partagerSite(@RequestParam(name = "erreur", required = false, defaultValue = "") String erreur, Model model){

        if(erreur.equals("")){
            site = new Site();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            utilisateur = utilisateurService.findByEmail(email);
        } else {
            model.addAttribute("erreur",erreur);
        }
        model.addAttribute("site",site);
        model.addAttribute("listePays", paysService.findAll());
        model.addAttribute("listeDepartements", departementService.findAll());
        model.addAttribute("listeTypes", typeService.findAll());
        model.addAttribute("listeCotations", cotationService.findAll());

        return "partager-site";
    }

    @PostMapping("/partager-site")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView partagerSite(@ModelAttribute Site site){

        ModelAndView mv = new ModelAndView();
        this.site = site;

        //System.out.println(site.toString());
        //Long l = new Long(1);
        site.setIdUtilisateur(utilisateur.getId());
        try{
            siteService.createSite(site);
        } catch (Exception e){
            //System.out.println(e.getMessage());
            mv.addObject("erreur",e.getMessage());
            mv.setViewName("redirect:/partager-site");
        }


        return mv;
    }
}
