package com.projet6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet6.beans.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type,String> {
}
