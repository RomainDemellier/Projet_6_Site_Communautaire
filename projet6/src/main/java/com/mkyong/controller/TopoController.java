package com.mkyong.controller;

import java.util.Date;
import java.util.List;

//import javax.persistence.EntityManager;

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
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.beans.Reservation;
import com.mkyong.beans.Topo;
import com.mkyong.beans.Utilisateur;
import com.mkyong.exceptions.TopoException;
import com.mkyong.services.ReservationService;
import com.mkyong.services.TopoService;
import com.mkyong.services.UtilisateurService;

@Controller
public class TopoController {

	@Autowired
	private TopoService topoService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private ReservationService reservationService;
	
	private Topo topo;
	
	private Utilisateur utilisateur;
	
	public TopoController() {
		// TODO Auto-generated constructor stub
	}
	
    @GetMapping("/topo-en-ligne")
    @PreAuthorize("isAuthenticated()")
    public String topoEnLigne(@RequestParam(name = "erreur", required = false, defaultValue = "") String erreur, Model model) {
    	
        this.utilisateur = this.authentification();
        
        if(!erreur.equals("")) {
        	model.addAttribute("erreur",erreur);
        } else {
        	this.topo = new Topo();
        }
//    	if(erreur.equals("")) {
//    		this.topo = new Topo();
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String email = userDetails.getUsername();
//            this.utilisateur = utilisateurService.findByEmail(email);
//    	} else {
//    		model.addAttribute("erreur",erreur);
//    	}
        
    	model.addAttribute("topo",this.topo);
    	return "topo-en-ligne";
    }
    
    @PostMapping("/topo-en-ligne")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView topoEnLigne(@ModelAttribute Topo t) {
    	
    	ModelAndView mv = new ModelAndView();
    	
//    	if(this.utilisateur == null) {
//    		System.out.println("utilisateur null !");
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String email = userDetails.getUsername();
//            this.utilisateur = utilisateurService.findByEmail(email);
//    	}
    	
    	this.utilisateur = this.authentification();
    	
    	System.out.println("utilisateur id : " + this.utilisateur.getId());
    	
    	this.topo = new Topo(t.getNom(),t.getDescription(),t.getLieu(),this.utilisateur);
    	
    	Date today = new Date();
    	java.sql.Date sqlDate = new java.sql.Date(today.getTime());
    	this.topo.setDateParution(sqlDate);
    	
    	this.topo.setStatut("Disponible");
    	
    	try {
    		this.topo = this.topoService.createTopo(topo);
    	} catch(Exception e) {
            mv.addObject("erreur",e.getMessage());
            mv.setViewName("redirect:/topo-en-ligne");
            return mv;
    	}
    	
//    	this.utilisateur.addTopos(topo);
//    	this.utilisateur = this.utilisateurService.editUtilisateur(this.utilisateur);
    	
    	mv.setViewName("redirect:/home");
    	return mv;
    }
    
    @GetMapping("/mes-topos")
    @PreAuthorize("isAuthenticated()")
    public String mesTopos(Model model) {
    	
    	this.utilisateur = this.authentification();
    	
    	System.out.println(this.topoService.findByEmprunteurIdAndStatut(this.utilisateur.getId(),"Réservé"));
    	
    	model.addAttribute("utilisateur",utilisateur);
    	model.addAttribute("listTopos", this.topoService.getToposByAuteur(utilisateur));
    	model.addAttribute("listToposDemandeReservation", this.topoService.findByEmprunteurIdAndStatut(this.utilisateur.getId(),"Demande de réservation"));
    	model.addAttribute("listToposReserves", this.topoService.findByEmprunteurIdAndStatut(this.utilisateur.getId(),"Réservé"));
    	return "mes-topos";
    }
    
    @PostMapping("/deleteTopo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteTopo(@RequestParam(name = "topoId") Long id) {
    	ModelAndView mv = new ModelAndView();
    	System.out.println("id topo : " + id);
    	this.topoService.deleteTopo(id);
 
//    	this.utilisateur.deleteTopo(id);
//    	this.utilisateur = this.utilisateurService.editUtilisateur(this.utilisateur);
    	
    	mv.setViewName("redirect:/mes-topos");
    	return mv;
    }
    
    @GetMapping("/reserver-un-topo")
    @PreAuthorize("isAuthenticated()")
    public String reserverTopo(Model model) {
    	
        this.utilisateur = this.authentification();
        System.out.println(this.utilisateur.getEmail());
        model.addAttribute("utilisateur", this.utilisateur);
        model.addAttribute("listToposReservation", this.topoService.getToposReservation(utilisateur));
        
    	return "reserver-un-topo";
    }
    
    @GetMapping("/reserver-un-topo/details-topo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detailsTopo(@RequestParam(name = "topoId", required = false)Long topoId) {
    	
    	ModelAndView mv = new ModelAndView();
    	
    	if(topoId == null) {
    		mv.setViewName("redirect:/reserver-un-topo");
    		return mv;
    	}
    	
    	this.utilisateur = this.authentification();
    	mv.addObject("utilisateur", this.utilisateur);
    	
    	Topo t = this.topoService.findById(topoId);
    	mv.addObject("topo", t);
    	mv.setViewName("details-topo");
    	return mv;
    }
    
    @GetMapping("/mes-topos/details-mon-topo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detailsMonTopo(@RequestParam(name = "topoId", required = false)Long topoId) {
    	
    	ModelAndView mv = new ModelAndView();
    	
    	this.utilisateur = this.authentification();
    	mv.addObject("utilisateur", this.utilisateur);
    	
    	Topo t = this.topoService.findById(topoId);
    	
    	Long emprunteurId = t.getEmprunteurId();
    	
    	if(emprunteurId != null) {
    		Utilisateur emprunteur = this.utilisateurService.findById(emprunteurId);
    		mv.addObject("emprunteur", emprunteur);
    	}
    	
    	mv.addObject("topo", t);
    	mv.setViewName("details-mon-topo");
    	return mv;
    }
    
    @PostMapping("/demande-reservation")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView reservation(@RequestParam(name = "topoId") Long topoId) {
    	ModelAndView mv = new ModelAndView();
    	
    	Topo topoReserve = this.topoService.findById(topoId);
    	topoReserve.setEmprunteurId(this.utilisateur.getId());
    	topoReserve.setStatut("Demande de réservation");
    	this.topoService.editTopo(topoReserve);
    	
    	mv.setViewName("redirect:/home");
    	
    	return mv;
    }
    
    @PostMapping("/mes-topos/accepter-ou-refuser")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView accepterOuRefuser(@RequestParam(name = "topoId") Long topoId, @RequestParam(name = "accepter", required = false) String accepter,
    									  @RequestParam(name = "refuser", required = false) String refuser) {
    	ModelAndView mv = new ModelAndView();
    	
    	Topo t = this.topoService.findById(topoId);
    	
    	if(accepter == null) {
    		t.setEmprunteurId(null);
    		t.setStatut("Disponible");
    	} else {
    		t.setStatut("Réservé");
    		
    		Reservation reservation = new Reservation();
    		reservation.setTopoId(t.getId());
    		
    		Utilisateur emprunteur = this.utilisateurService.findById(t.getEmprunteurId());
    		
    		reservation.setEmprunteurId(emprunteur.getId());
    		reservation.setEmprunteurNom(emprunteur.getNom());
    		reservation.setEmprunteurPrenom(emprunteur.getPrenom());
    		
        	Date today = new Date();
        	java.sql.Date sqlDate = new java.sql.Date(today.getTime());
        	
        	reservation.setDateDebut(sqlDate);
        	
        	this.reservationService.createReservation(reservation);
    	}
    	
    	this.topoService.editTopo(t);
    	
    	mv.setViewName("redirect:/mes-topos");
    	
    	return mv;
    }
    
    @PostMapping("/rendreTopo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView rendreTopo(@RequestParam(name = "rendreTopoId") Long topoId) {
    	ModelAndView mv = new ModelAndView();
    	
    	Topo t = this.topoService.findById(topoId);
    	t.setEmprunteurId(null);
    	t.setStatut("Disponible");
    	
    	Reservation reservation = this.reservationService.findByTopoIdAndDateFin(topoId, null);
    	Date today = new Date();
    	java.sql.Date sqlDate = new java.sql.Date(today.getTime());
    	
    	reservation.setDateFin(sqlDate);
    	
    	this.reservationService.editReservation(reservation);
    	
    	this.topoService.editTopo(t);
    	
    	mv.setViewName("redirect:/mes-topos");
    	return mv;
    }
    
    @GetMapping("/historique")
    @PreAuthorize("isAuthenticated()")
    public String historique(@RequestParam(name = "topoId", required = false) Long id, Model model) {
    	if(id != null) {
    		Topo t = this.topoService.findById(id);
    		
    		List<Reservation> listReservation = this.reservationService.findByTopoId(id);
    		
    		model.addAttribute("utilisateur", utilisateur);
    		model.addAttribute("listReservation", listReservation);
    		model.addAttribute("topo", t);
    		return "historique";
    	} else {
    		return "redirect:/mes-topos";
    	}
    }
    
    private Utilisateur authentification() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return this.utilisateurService.findByEmail(email);
    }

}
