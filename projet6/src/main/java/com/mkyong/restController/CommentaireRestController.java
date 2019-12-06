package com.mkyong.restController;

import com.mkyong.beans.Commentaire;
import com.mkyong.exceptions.ResourceNotFoundException;
import com.mkyong.repositories.CommentaireRepository;
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
