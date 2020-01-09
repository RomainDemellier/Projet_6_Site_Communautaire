package com.mkyong.services;

import com.mkyong.beans.Topo;
import com.mkyong.beans.Utilisateur;
import com.mkyong.exceptions.EmailException;
import com.mkyong.exceptions.LoginException;
import com.mkyong.exceptions.ResourceNotFoundException;
import com.mkyong.exceptions.TopoException;
import com.mkyong.exceptions.UtilisateurException;
import com.mkyong.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public UtilisateurService(){
        //super();
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur, String confirmPassword) throws UtilisateurException {

        if(utilisateur.getPrenom().equals("")){
            throw new UtilisateurException("Le champ prénom doit être rempli.");
        } else if(utilisateur.getPrenom().length() > 20){
            throw new UtilisateurException("Le prénom doit comporter au maximum 20 caractères !");
        }
        if(utilisateur.getNom().equals("")){
            throw new UtilisateurException("Le champ nom doit être rempli.");
        } else if(utilisateur.getNom().length() > 20){
            throw new UtilisateurException("Le nom doit comporter au maximum 20 caractères !");
        }

        String email = utilisateur.getEmail();
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        Boolean emailValide = m.matches();
        if(! emailValide) {
            throw new UtilisateurException("Cette adresse mail n'est pas valide !");
        }

        if(utilisateurRepository.findByEmail(utilisateur.getEmail()) != null){
            throw new UtilisateurException("Cette email existe déjà dans la base");
        }

        String password = utilisateur.getPassword();
        if(password.length() < 8) {
            throw new UtilisateurException("Le mot de passe doit comporter au moins 8 caractères !");
        }

        if(! password.equals(confirmPassword)){
            throw new UtilisateurException("Veuillez saisir deux mots de passe identiques");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePwd = bCryptPasswordEncoder.encode(password);
        utilisateur.setPassword(encodePwd);

        return utilisateurRepository.save(utilisateur);
    }
    
    public Utilisateur editUtilisateur(Utilisateur utilisateur) {
    	
    	return this.utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByEmail(String email){
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur findById(Long id){
        return utilisateurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));
    }

    public Utilisateur findByNom(String nom){ return utilisateurRepository.findByNom(nom); }

    public Utilisateur logOk(Long id){
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));;
        System.out.println("Utilisateur = " + utilisateur.getNom());
        return utilisateur;
    }

    public Utilisateur logOk(String email, String password) throws LoginException {
        Utilisateur utilisateur = utilisateurRepository.findByEmailAndPassword(email, password);
        if(utilisateur != null){
            return utilisateur;
        } else {
            throw new LoginException("Email ou mot de passe incorrect");
        }
    }
    
    public List<Utilisateur> getAllUtilisateurs(Long id){
    	return this.utilisateurRepository.findByIdNot(id);
    }
}
