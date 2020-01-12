package com.projet6.restController;

import com.projet6.beans.Commentaire;
import com.projet6.exceptions.ResourceNotFoundException;
import com.projet6.repositories.CommentaireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentaireRestController {

    @Autowired
    CommentaireRepository commentaireRepository;

    @GetMapping("/commentaires/{id}")
    public Commentaire getCommentaireById(@PathVariable(value = "id") Long idComment){
        return commentaireRepository.findById(idComment).orElseThrow(() -> new ResourceNotFoundException("Commentaire","id",idComment));
    }
}
