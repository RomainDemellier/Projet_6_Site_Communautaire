package com.projet6.restController;

import com.projet6.beans.Site;
import com.projet6.repositories.SiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SiteRestController {

    @Autowired
    SiteRepository siteRepository;

    @RequestMapping("/allSites")
    public List<Site> getAllSites(){
        return siteRepository.findAll();
    }
}
