package com.utpl.reserva.vuelos.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.utpl.reserva.vuelos.negocio.core.DaoJpaImpl;

import modelo.Clasificacioncabina;

@Stateless
public class ClasificacionCabinaDAO extends DaoJpaImpl<Clasificacioncabina, Integer> {

	public List<Clasificacioncabina> obtenerClasificacionCabina() {
		List<Clasificacioncabina> aeropuertosCol = new ArrayList<Clasificacioncabina>();
		aeropuertosCol = this.findByNamedQuery("Clasificacioncabina.findAll", null);
		return aeropuertosCol;
	}
}
