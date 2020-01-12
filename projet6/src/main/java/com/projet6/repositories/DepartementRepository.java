package com.projet6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet6.beans.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
}
