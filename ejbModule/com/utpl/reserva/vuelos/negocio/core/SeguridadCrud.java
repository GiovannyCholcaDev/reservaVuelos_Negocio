package com.utpl.reserva.vuelos.negocio.core;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.utpl.reserva.vuelos.negocio.cliente.ISeguridadCrud;

@Stateless
public class SeguridadCrud implements ISeguridadCrud {

	@PersistenceContext(unitName = "reservaVuelosPU")
	private EntityManager conexion;

	@SuppressWarnings("rawtypes")
	@Override
	public Object consultarLogin(Class tabla, String user, String password) throws Exception {
		String sqlObj = "select alias from " + tabla.getSimpleName() + " alias";
		Query conTod = this.conexion.createQuery(sqlObj);
		return conTod.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByQuery(String jpql, Map<String, Object> parameters) throws Exception {
		Query query = this.conexion.createQuery(jpql);
		if (null != parameters && parameters.size() > 0) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object findByNamedQuery(String namedQuery, Map<String, Object> parameters) throws Exception {

		Query query = this.conexion.createNamedQuery(namedQuery);
		if (null != parameters && parameters.size() > 0) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		List results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	public EntityManager getConexion() {
		return conexion;
	}

	public void setConexion(EntityManager conexion) {
		this.conexion = conexion;
	}

}
