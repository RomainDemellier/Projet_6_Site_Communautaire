package com.mkyong.controller;

import com.mkyong.beans.*;
import com.mkyong.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/authenticated")
public class HomeController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PaysService paysService;

    @Autowired
    private DepartementService departementService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CotationService cotationService;

    @Autowired
    private SiteService siteService;

    private Utilisateur utilisateur = null;
    
    private List<Site> listSites = new ArrayList<Site>();

//    private List<Pays> listePays = new ArrayList<Pays>();
//    private List<Departement> listeDepartement = new ArrayList<Departement>();
//    private List<Site> listeSite = new ArrayList<Site>();
//    private List<Site> listeArticleSite = new ArrayList<Site>();
//    private List<Type> listeType = new ArrayList<Type>();
//    private List<Cotation> listeCotation = new ArrayList<Cotation>();
//
//
//    private String nomPays = "0";
//    private String nomDepartement = "0";
//    private Long idSite = new Long(0);
//    private String nomType = "0";
//    private String difCotation = "0";
//
//    private Boolean hasSubmit = false;

    @GetMapping("/home")
    public String home(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("authentication : " + authentication.getPrincipal());
        if(!authentication.getPrincipal().equals("anonymousUser")){
            //System.out.println("dans anonymous user");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            this.utilisateur = utilisateurService.findByEmail(email);
        } else {
            this.utilisateur = null;
        }
//
//        if(!hasSubmit){
//
//            listePays = paysService.findAll();
//            listeType = typeService.findAll();
//            listeCotation = cotationService.findAll();
//            listeSite = siteService.findAll();
//
//        } else {
//            model.addAttribute("nomPays", nomPays);
//            model.addAttribute("nomDepartement", nomDepartement);
//            model.addAttribute("idSite", idSite);
//            model.addAttribute("nomType", nomType);
//            model.addAttribute("difCotation", difCotation);
//
//            if(nomPays.equals("France")){
//                listeDepartement = departementService.findAll();
//            } else {
//                listeDepartement = null;
//            }
//        }
//
//        model.addAttribute("listePays", listePays);
//        model.addAttribute("listeDepartement", listeDepartement);
//        model.addAttribute("listeSite", listeSite);
//        model.addAttribute("listeArticleSite", listeArticleSite);
//        model.addAttribute("listeType", listeType);
//        model.addAttribute("listeCotation", listeCotation);

        this.listSites = this.siteService.findAll();
        
        model.addAttribute("listSites", this.listSites);
        model.addAttribute("utilisateur", utilisateur);

        return "home";
    }

//    @PostMapping("/home")
//    public ModelAndView home(@RequestParam(value = "pays") String nomPays, @RequestParam(value = "departement") String nomDepartement,
//                             @RequestParam(value = "site") Long idSite, @RequestParam(value = "type") String nomType,
//                             @RequestParam(value = "cotation") String difCotation){
//
//        System.out.println("Pays : " + nomPays);
//        System.out.println("Departement : " + nomDepartement);
//        System.out.println("Site : " + idSite);
//        System.out.println("Type : " + nomType);
//        System.out.println("Cotation : " + difCotation);
//
//
//
//        listeSite = siteService.findByCriteres(nomPays,nomDepartement,nomType,difCotation,new Long(0));
//        listeArticleSite = siteService.findByCriteres(nomPays,nomDepartement,nomType,difCotation,idSite);
//
//        this.nomPays = nomPays;
//        this.nomDepartement = nomDepartement;
//        this.idSite = idSite;
//        this.nomType = nomType;
//        this.difCotation = difCotation;
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("redirect:/home");
//        hasSubmit = true;
//        return mv;
//    }

}
