package com.projet6.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reservation")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "topo_id")
    private Long topoId;
    
    @Column(name = "emprunteur_id")
    private Long emprunteurId;
    
    @Column(name = "emprunteur_nom")
    private String emprunteurNom;
    
    @Column(name = "emprunteur_prenom")
    private String emprunteurPrenom;
    
    @Column(name = "date_debut")
    private Date dateDebut;
    
    @Column(name = "date_fin")
    private Date dateFin;
    

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTopoId() {
		return topoId;
	}

	public void setTopoId(Long topoId) {
		this.topoId = topoId;
	}

	public Long getEmprunteurId() {
		return emprunteurId;
	}

	public void setEmprunteurId(Long emprunteurId) {
		this.emprunteurId = emprunteurId;
	}

	public String getEmprunteurNom() {
		return emprunteurNom;
	}

	public void setEmprunteurNom(String emprunteurNom) {
		this.emprunteurNom = emprunteurNom;
	}

	public String getEmprunteurPrenom() {
		return emprunteurPrenom;
	}

	public void setEmprunteurPrenom(String emprunteurPrenom) {
		this.emprunteurPrenom = emprunteurPrenom;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


    
}
