package com.mkyong.services;

import com.mkyong.beans.Cotation;
import com.mkyong.repositories.CotationRepository;
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
