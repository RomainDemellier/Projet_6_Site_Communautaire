package com.mkyong.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.beans.Reservation;
import com.mkyong.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public ReservationService() {
		// TODO Auto-generated constructor stub
	}
	
	public Reservation createReservation(Reservation res) {
		return this.reservationRepository.save(res);
	}
	
	public Reservation editReservation(Reservation res) {
		return this.reservationRepository.save(res);
	}
	
	public List<Reservation> findByTopoId(Long topoId){
		return this.reservationRepository.findByTopoId(topoId);
	}
	
	public Reservation findByTopoIdAndDateFin(Long topoId, Date date) {
		return this.reservationRepository.findByTopoIdAndDateFin(topoId, null);
	}

}
