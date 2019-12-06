package com.mkyong.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pays")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Pays implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    public Pays(){
        super();
    }

    public Long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
