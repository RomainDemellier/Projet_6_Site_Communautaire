package com.projet6.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet6.beans.Topo;
import com.projet6.beans.Utilisateur;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

	List<Topo> findByAuteur(Utilisateur auteur);
	
	List<Topo> findByAuteurNotAndStatut(Utilisateur auteur,String statut);
	
	List<Topo> findByEmprunteurId(Long id);
	
	List<Topo> findByEmprunteurIdAndStatut(Long id, String statut);
}
