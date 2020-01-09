package com.mkyong.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.beans.Topo;
import com.mkyong.beans.Utilisateur;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

	List<Topo> findByAuteur(Utilisateur auteur);
	
	List<Topo> findByAuteurNotAndStatut(Utilisateur auteur,String statut);
	
	List<Topo> findByEmprunteurId(Long id);
	
	List<Topo> findByEmprunteurIdAndStatut(Long id, String statut);
}
