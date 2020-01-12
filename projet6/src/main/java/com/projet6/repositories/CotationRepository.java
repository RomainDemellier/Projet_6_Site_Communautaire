package com.projet6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet6.beans.Cotation;

@Repository
public interface CotationRepository extends JpaRepository<Cotation,String> {
}
