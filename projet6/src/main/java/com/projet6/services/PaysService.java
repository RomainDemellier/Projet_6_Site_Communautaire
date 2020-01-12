package com.projet6.services;

import com.projet6.beans.Pays;
import com.projet6.exceptions.ResourceNotFoundException;
import com.projet6.repositories.PaysRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public List<Pays> findAll(){
        return paysRepository.findAll();
    }

    public Pays findById(Long idPays){
        return paysRepository.findById(idPays).orElseThrow(() -> new ResourceNotFoundException("Pays","id",idPays));
    }
}
