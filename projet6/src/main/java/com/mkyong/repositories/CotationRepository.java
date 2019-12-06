package com.mkyong.repositories;

import com.mkyong.beans.Cotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotationRepository extends JpaRepository<Cotation,String> {
}
