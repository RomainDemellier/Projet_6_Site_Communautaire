package com.projet6.services;

import com.projet6.beans.Cotation;
import com.projet6.repositories.CotationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotationService {

    @Autowired
    private CotationRepository cotationRepository;

    public List<Cotation> findAll(){
        return cotationRepository.findAll();
    }
}
