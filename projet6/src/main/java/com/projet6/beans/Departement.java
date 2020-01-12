package com.projet6.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="departement")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "id_pays")
//    private int idPays = 1;

    private String nom;

    public Long getId() {
        return id;
    }
//    public int getIdPays() {
//        return idPays;
//    }
    public String getNom() {
        return nom;
    }
    public void setId(Long id) {
        this.id = id;
    }
//    public void setIdPays(int idPays) {
//        this.idPays = idPays;
//    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
