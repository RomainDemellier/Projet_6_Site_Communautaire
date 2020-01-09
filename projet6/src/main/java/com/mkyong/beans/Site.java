package com.mkyong.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="site")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Site implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pays")
    private String nomPays;

    @Column(name = "departement")
    private String nomDepartement;

    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    private String nom;

    private String type;

    private String hauteur;

    private String orientation;

    private String voies;

    private String cotations;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_site")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Commentaire> commentaires = new ArrayList<Commentaire>();
    
    private boolean tag = false;

    public Site(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHauteur() {
        return hauteur;
    }

    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getVoies() {
        return voies;
    }

    public void setVoies(String voies) {
        this.voies = voies;
    }

    public String getCotations() {
        return cotations;
    }

    public void setCotations(String cotations) {
        this.cotations = cotations;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public boolean isTag() {
		return tag;
	}

	public void setTag(boolean tag) {
		this.tag = tag;
	}

	public void addCommentaire(Commentaire commentaire){
        this.commentaires.add(commentaire);
    }
    
	public void deleteCommentaire(Long id) {
		List<Commentaire> list = this.commentaires;
		Iterator li = list.iterator();
		Commentaire c;
		while(li.hasNext()) {
			c = (Commentaire) li.next();
			if(c.getId() == id) {
				li.remove();
				break;
			}
		}
		this.setCommentaires(list);
	}

    public String toString(){
        String str = "";

        str += "Nom : " + this.nom + "\n";
        str += "Pays : " + this.nomPays + "\n";
        str += "Departement : " + this.nomDepartement + "\n";
        str += "Type : " + this.type + "\n";
        str += "Hauteur : " + this.hauteur + "\n";
        str += "Orientation : " + this.orientation + "\n";
        str += "Voies : " + this.voies + "\n";
        str += "Cotations : " + this.cotations + "\n";

        return str;
    }
}
