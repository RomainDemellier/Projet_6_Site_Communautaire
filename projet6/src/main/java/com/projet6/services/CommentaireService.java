package com.projet6.services;

import com.projet6.beans.Commentaire;
import com.projet6.repositories.CommentaireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentaireService {

    @Autowired
    CommentaireRepository commentaireRepository;

    public ResponseEntity<?> deleteCommentaire(Long idComment){
        //Commentaire commentaire = commentaireRepository.find
        commentaireRepository.deleteById(idComment);
        return ResponseEntity.ok().build();
    }
}
