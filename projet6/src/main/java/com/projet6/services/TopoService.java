package com.projet6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet6.beans.Topo;
import com.projet6.beans.Utilisateur;
import com.projet6.exceptions.ResourceNotFoundException;
import com.projet6.exceptions.TopoException;
import com.projet6.repositories.TopoRepository;

@Service
public class TopoService {

	@Autowired
	private TopoRepository topoRepository;
	
	public TopoService() {
		// TODO Auto-generated constructor stub
	}
	
	public Topo findById(Long topoId) {
		return this.topoRepository.findById(topoId).orElseThrow(() -> new ResourceNotFoundException("Topo", "id", topoId));
	}
	
	public Topo createTopo(Topo topo) throws TopoException {
		
		if(topo.getNom().equals("") || topo.getDescription().equals("") || topo.getLieu().equals("")) {
			throw new TopoException("Tous les champs sont requis");
		}
		
		return this.topoRepository.save(topo);
	}
	
	public Topo editTopo(Topo topo) {
		return this.topoRepository.save(topo);
	}

	public List<Topo> getAllTopos(){
		return this.topoRepository.findAll();
	}
	
	public List<Topo> getToposReservation(Utilisateur auteur){
		return this.topoRepository.findByAuteurNotAndStatut(auteur, "Disponible");
	}
	
	public List<Topo> getToposByAuteur(Utilisateur auteur){
		return this.topoRepository.findByAuteur(auteur);
	}
	
	public void deleteTopo(Long id) {
		this.topoRepository.deleteById(id);
	}
	
	public List<Topo> findByEmprunteurId(Long id){
		return this.topoRepository.findByEmprunteurId(id);
	}
	
	public List<Topo> findByEmprunteurIdAndStatut(Long id, String statut){
		return this.topoRepository.findByEmprunteurIdAndStatut(id,statut);
	}
	
}
