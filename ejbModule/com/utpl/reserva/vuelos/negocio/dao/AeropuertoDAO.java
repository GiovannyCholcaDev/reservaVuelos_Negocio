package com.utpl.reserva.vuelos.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.utpl.reserva.vuelos.negocio.core.DaoJpaImpl;

import modelo.Aeropuerto;

@Stateless
public class AeropuertoDAO extends DaoJpaImpl<Aeropuerto, Integer> {

	public List<Aeropuerto> obtenerListaAeropuertos() {
		List<Aeropuerto> aeropuertosCol = new ArrayList<Aeropuerto>();
		aeropuertosCol = this.findByNamedQuery("Aeropuerto.findAll", null);
		return aeropuertosCol;
	}
}
