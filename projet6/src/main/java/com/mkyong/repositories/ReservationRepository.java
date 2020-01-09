package com.mkyong.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.beans.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByTopoId(Long id);
	
	Reservation findByTopoIdAndDateFin(Long topoId, Date date);
}
