package com.mkyong.services;

import com.mkyong.beans.Type;
import com.mkyong.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public List<Type> findAll(){
        return typeRepository.findAll();
    }
}
