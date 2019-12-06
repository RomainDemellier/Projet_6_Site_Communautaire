package com.mkyong.services;

import com.mkyong.beans.Site;
import com.mkyong.exceptions.ResourceNotFoundException;
import com.mkyong.exceptions.SiteException;
import com.mkyong.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public SiteService(){super();}

    public List<Site> findAll(){
        return siteRepository.findAll();
    }

    public Site createSite(Site site) throws SiteException {
        if(site.getNomPays().equals("0")){
            throw new SiteException("Veuillez sélectionner un pays");
        }
        if(site.getNomPays().equals("1") && site.getNomDepartement().equals("0")){
            throw new SiteException("Veuillez sélectionner un département.");
        }
        if(site.getNom().length() == 0){
            throw new SiteException("Le champ nom est requis.");
        }
        if(site.getNom().length() > 20){
            throw new SiteException("Le champ nom ne doit pas excéder 20 caractères.");
        }
        if(site.getType().equals("0")){
            throw new SiteException("Veuillez sélectionner un type.");
        }
        if(site.getHauteur().length() == 0){
            throw new SiteException("Veuillez spécifier une hauteur.");
        }
        if(site.getHauteur().length() > 25){
            throw new SiteException("Le champ hauteur ne doit pas excéder 25 caractères.");
        }
        if(site.getOrientation().length() == 0){
            throw new SiteException("Veuillez spécifier une orientation.");
        }
        if(site.getHauteur().length() > 30){
            throw new SiteException("Le champ orientation ne doit pas excéder 30 caractères.");
        }
        if(site.getVoies().length() == 0){
            throw new SiteException("Veuillez spécifier le nombre de voies.");
        }
        if(site.getVoies().length() > 25){
            throw new SiteException("Le champ voies ne doit pas excéder 25 caractères");
        }
        if(site.getCotations().equals("0")){
            throw new SiteException("Veuillez sélectionner une cotation.");
        }
        return siteRepository.save(site);
    }

    public Site editSite(Site site){
        return siteRepository.save(site);
    }

    public List<Site> findByCriteres(String nomPays, String nomDepartement, String type, String cotations, Long idSite) {
        List<Site> liste = new ArrayList<Site>();
        if(idSite != 0){
            liste.add(siteRepository.findById(idSite).orElseThrow(() -> new ResourceNotFoundException("Site", "id", idSite)));
        } else {
            if (nomPays.equals("France")) {
                if (!nomDepartement.equals("0")) {
                    if (!type.equals("0")) {
                        if (!cotations.equals("0")) {
                            liste = siteRepository.findByNomDepartementAndTypeAndCotations(nomDepartement,type,cotations);
                        } else {
                            liste = siteRepository.findByNomDepartementAndType(nomDepartement,type);
                        }
                    } else if(!cotations.equals("0")) {
                        liste = siteRepository.findByNomDepartementAndCotations(nomDepartement,cotations);
                    } else {
                        liste =  siteRepository.findByNomDepartement(nomDepartement);
                    }
                } else {
                    if (!type.equals("0")) {
                        if (!cotations.equals("0")) {
                            liste = siteRepository.findByNomPaysAndTypeAndCotations(nomPays,type,cotations);
                        } else {
                            liste = siteRepository.findByNomPaysAndType(nomPays,type);
                        }
                    } else if(!cotations.equals("0")) {
                        liste = siteRepository.findByNomPaysAndCotations(nomPays,cotations);
                    } else {
                        liste = siteRepository.findByNomPays(nomPays);
                    }
                }
            } else if (!nomPays.equals("France")) {
                if (!type.equals("0")) {
                    if (!cotations.equals("0")) {
                        liste = siteRepository.findByNomPaysAndTypeAndCotations(nomPays,type,cotations);
                    } else {
                        liste = siteRepository.findByNomPaysAndType(nomPays,type);
                    }
                } else if(!cotations.equals("0")) {
                    liste = siteRepository.findByNomPaysAndCotations(nomPays,cotations);
                } else {
                    liste = siteRepository.findByNomPays(nomPays);
                }
            }
            if (nomPays.equals("0") && nomDepartement.equals("0") && type.equals("0") && cotations.equals("0")) {
                liste = siteRepository.findAll();
            }
        }

        return liste;
    }

    public Site findById(Long id){
        return siteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Site", "id", id));
    }
}
