package com.mkyong.controller;

import com.mkyong.beans.Commentaire;
import com.mkyong.beans.Site;
import com.mkyong.beans.Utilisateur;
import com.mkyong.services.CommentaireService;
import com.mkyong.services.SiteService;
import com.mkyong.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

@Controller
//@RequestMapping("/authenticated")
public class DetailsController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private CommentaireService idCommentaireService;

    private Site site;
    private Utilisateur utilisateur = null;

    private Boolean hasSubmit = false;

    @GetMapping("/details")
    public ModelAndView details(@RequestParam(name = "idSite", required = false) Long idSite, Model model){
        //System.out.println(idSite);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("authentication : " + authentication.getPrincipal());
        if(!authentication.getPrincipal().equals("anonymousUser")){
            //System.out.println("dans anonymous user");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            utilisateur = utilisateurService.findByEmail(email);
        }

        ModelAndView mv = new ModelAndView();
/*        if(!hasSubmit && idSite == null){
            System.out.println("redirection vers home");
            mv.setViewName("redirect:/home");
            return mv;
        } else if(!hasSubmit){
            System.out.println("Vers Details");
            //site = siteService.findById(idSite);
        }*/
        if(!hasSubmit && idSite == null){
            System.out.println("redirection vers home");
            mv.setViewName("redirect:/home");
            return mv;
        }
        if(idSite != null){
            site = siteService.findById(idSite);
        }
        mv.setViewName("details");
        mv.addObject("site",site);
        mv.addObject("utilisateur",utilisateur);
        /*if(!hasSubmit){
            site = siteService.findById(idSite);
            utilisateur = utilisateurService.findById(idUtilisateur);
        }
        model.addAttribute("site",site);
        model.addAttribute("utilisateur",utilisateur);
        return "details";*/
        return mv;
    }

    @PostMapping("/addComment")
    @PreAuthorize("hasRole('USER') or hasRole('MEMBER') or hasRole('SUPER_MEMBER')")
    public ModelAndView addComment(@RequestParam(name = "commentaire",required = false) String contenu){

        ModelAndView mv = new ModelAndView();
        hasSubmit = true;
        if(contenu != null && !contenu.equals("")){
            Commentaire commentaire = new Commentaire();
            //System.out.println("id comm : " + idCommentaire.getId());
            commentaire.setContenu(contenu);
            commentaire.setPrenomAuteur(utilisateur.getPrenom());
            commentaire.setNomAuteur(utilisateur.getNom());
            site.addCommentaire(commentaire);
            this.site = siteService.editSite(site);
        }
        mv.setViewName("redirect:/details");
        return mv;
    }

    @PostMapping("/updateComment")
    public ModelAndView updateComment(@RequestParam(name = "updateCommentId", required = false) Long idComment,
                        @RequestParam(name = "updateCommentContenu", required = false) String contenu){

        System.out.println("contenu : " + contenu);
        System.out.println("idComment : " + idComment);
        System.out.println("idComment intValue : " + idComment.intValue());
        if(idComment != null && contenu != ""){
            Commentaire commentaire = new Commentaire();
            commentaire.setId(idComment);
            commentaire.setContenu(contenu);
            List<Commentaire> commentaires = this.site.getCommentaires();
            for (int i = 0;i < commentaires.size();i++){
                if(commentaires.get(i).getId() == idComment){
                    commentaire.setNomAuteur(commentaires.get(i).getNomAuteur());
                    commentaire.setPrenomAuteur(commentaires.get(i).getPrenomAuteur());
                    commentaires.set(i,commentaire);
                }
            }
            this.site.setCommentaires(commentaires);
            this.site = siteService.editSite(site);
        }



        ModelAndView mv = new ModelAndView();
        hasSubmit = true;
        mv.setViewName("redirect:/details");
        return mv;
    }

    //@PostMapping("/deleteComment")
/*    @GetMapping("/deleteComment")
    public ModelAndView deleteComment(@RequestParam(name = "id", required = false) Long idComment){
        System.out.println("dans Get delete : " + idComment);
        if(idComment != null){
            System.out.println("id idCommentaire : " + idComment);
            //idCommentaireService.deleteCommentaire(idComment);
            List<Commentaire> idCommentaires = this.site.getCommentaires();
            Iterator li = idCommentaires.iterator();
            while (li.hasNext()){
                Commentaire com = (Commentaire) li.next();
                if(com.getId() == idComment){
                    System.out.println("Dans remove.");
                    li.remove();
                    break;
                }
            }
            this.site.setCommentaires(idCommentaires);
            this.site = siteService.editSite(site);
        } else {
            System.out.println("idComment null");
        }
        ModelAndView mv = new ModelAndView();
        hasSubmit = true;
        mv.setViewName("redirect:/details");
        return mv;
    }*/


    @PostMapping("/deleteComment")
    public ModelAndView deleteCommentPost(@RequestParam(name = "id", required = false) Long idComment){
        System.out.println("dans Post delete : " + idComment);
        if(idComment != null){
            System.out.println("id idCommentaire : " + idComment);
            //idCommentaireService.deleteCommentaire(idComment);
            List<Commentaire> idCommentaires = this.site.getCommentaires();
            Iterator li = idCommentaires.iterator();
            while (li.hasNext()){
                Commentaire com = (Commentaire) li.next();
                if(com.getId() == idComment){
                    System.out.println("Dans remove.");
                    li.remove();
                    break;
                }
            }
            this.site.setCommentaires(idCommentaires);
            this.site = siteService.editSite(site);
        } else {
            System.out.println("idComment null");
        }
        ModelAndView mv = new ModelAndView();
        hasSubmit = true;
        mv.setViewName("redirect:/details");
        return mv;
    }

    @PostMapping("/cancelUpdateComment")
    public ModelAndView cancelUpdateComment(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/details");
        hasSubmit = true;
        return mv;
    }
}
