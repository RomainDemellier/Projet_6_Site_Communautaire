package com.mkyong.services;

import com.mkyong.beans.Departement;
import com.mkyong.exceptions.ResourceNotFoundException;
import com.mkyong.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> findAll(){
        return departementRepository.findAll();
    }

    public Departement findById(Long idDepartement){
        return departementRepository.findById(idDepartement).orElseThrow(() -> new ResourceNotFoundException("Departement","id",idDepartement));
    }
}
