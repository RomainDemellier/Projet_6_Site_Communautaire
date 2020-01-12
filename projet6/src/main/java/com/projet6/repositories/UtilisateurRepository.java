package com.projet6.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet6.beans.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    //@Query(value = "select * from utilisateurs where email = ?1", nativeQuery = true)
    Utilisateur findByEmail(String email);

    Utilisateur findByNom(String nom);

    //@Query(value = "select * from utilisateurs where email = ?1 and password = ?2", nativeQuery = true)
    Utilisateur findByEmailAndPassword(String email, String password);
    
    List<Utilisateur> findByIdNot(Long id);
}
