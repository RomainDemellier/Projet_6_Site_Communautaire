package com.mkyong.repositories;

import com.mkyong.beans.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

    List<Site> findByNomPays(String nomPays);
    List<Site> findByNomPaysAndType(String nomPays,String type);
    List<Site> findByNomPaysAndCotations(String nomPays,String cotations);
    List<Site> findByNomPaysAndTypeAndCotations(String nomPays,String type,String cotations);

    List<Site> findByNomDepartement(String nomDepartement);
    List<Site> findByNomDepartementAndType(String nomDepartement,String type);
    List<Site> findByNomDepartementAndCotations(String nomDepartement,String cotations);
    List<Site> findByNomDepartementAndTypeAndCotations(String nomDepartement,String type,String cotations);

    List<Site> findByType(String type);
    List<Site> findByTypeAndCotations(String type,String cotations);

    List<Site> findByCotations(String cotations);
}
