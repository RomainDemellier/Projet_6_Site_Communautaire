package com.mkyong.restController;

import com.mkyong.beans.Site;
import com.mkyong.repositories.SiteRepository;
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
