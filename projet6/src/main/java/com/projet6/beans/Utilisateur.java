package com.projet6.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="utilisateurs")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    //@NotBlank
    private String role;
    
    @OneToMany(mappedBy = "auteur", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Topo> listTopos = new ArrayList<Topo>();
    
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_emprunteur")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private List<Topo> listToposEmpruntes = new ArrayList<Topo>();

    public Utilisateur(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public List<Topo> getListTopos() {
		return listTopos;
	}

	public void setListTopos(List<Topo> listTopos) {
		this.listTopos = listTopos;
	}

//	public List<Topo> getListToposEmpruntes() {
//		return listToposEmpruntes;
//	}
//
//	public void setListToposEmpruntes(List<Topo> listToposEmpruntes) {
//		this.listToposEmpruntes = listToposEmpruntes;
//	}
	
	public void addTopos(Topo topo) {
		this.listTopos.add(topo);
	}
	
	public void deleteTopo(Long id) {
		List<Topo> list = this.listTopos;
		Iterator li = list.iterator();
		Topo t;
		while(li.hasNext()) {
			t = (Topo) li.next();
			if(t.getId() == id) {
				li.remove();
				break;
			}
		}
		this.setListTopos(list);
	}
	
//	public void addToposEmpruntes(Topo topo) {
//		this.listToposEmpruntes.add(topo);
//	}
    
}
