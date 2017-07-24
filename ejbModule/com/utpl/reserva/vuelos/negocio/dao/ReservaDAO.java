package com.utpl.reserva.vuelos.negocio.dao;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.utpl.reserva.vuelos.negocio.core.DaoJpaImpl;

import modelo.Reserva;

@Stateless
public class ReservaDAO extends DaoJpaImpl<Reserva, Integer> {

	public Reserva guardarReserva(Reserva reservaVuelo){
		BigDecimal idMax = this.obtenerMaxIdReserva();
		reservaVuelo.setIdreserva(idMax.intValue());
		reservaVuelo = this.create(reservaVuelo);
		return reservaVuelo;
	}
	
	public BigDecimal obtenerMaxIdReserva(){
		StringBuffer builder = new StringBuffer();
		builder.append(" SELECT MAX(IDRESERVA) + 1  FROM RESERVA; ");
		Query query = this.createNativeQuery(builder.toString());
		BigDecimal idMax = (BigDecimal) query.getSingleResult();
		return idMax;
	}
}
