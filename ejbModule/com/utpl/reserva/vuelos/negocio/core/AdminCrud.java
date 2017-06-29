package com.utpl.reserva.vuelos.negocio.core;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.utpl.reserva.vuelos.negocio.cliente.IAdminCrud;

@Stateful
public class AdminCrud implements IAdminCrud {

	@PersistenceContext(unitName = "reservaVuelosPU")
	private EntityManager conexion;

	@Override
	public String guardar(Object registro) throws Exception {
		try {
			conexion.persist(registro);
		} catch (Exception e) {
			throw new Exception("Se produjo un error en la guardada");
		}
		return "Registro guardado correctamente";
	}

	@Override
	public String actualizar(Object registro) throws Exception {
		try {
			conexion.merge(registro);
		} catch (Exception e) {
			throw new Exception("Se produjo un error en la actualizacion");
		}
		return "Registro actualizado correctamente";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String eliminar(Class tabla, Integer id) throws Exception {
		try {
			//Consultar por Id
			Object objDev = conexion.find(tabla, id);
			conexion.remove(objDev);
		} catch (Exception e) {
			throw new Exception("Se produjo un error en la eliminación");
		}
		return "Registro eliminado correctamente";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object consultarPorId(Class tabla, Integer id) throws Exception {
		Object objDev = null;
		try {
			//Consultar por Id
			objDev = conexion.find(tabla, id);
		} catch (Exception e) {
			throw new Exception("Se produjo un error en la consulta");
		}
		return objDev; 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> consultarTodos(Class tabla) throws Exception {
		String sqlObj = "select alias from " + tabla.getSimpleName() + " alias";
		Query conTod = conexion.createQuery(sqlObj);
		return conTod.getResultList();
	}
	
	public EntityManager getConexion() {
		return conexion;
	}

	public void setConexion(EntityManager conexion) {
		this.conexion = conexion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByNamedQuery(String namedQuery, Map<String, Object> parameters) throws Exception {
		Query query = this.conexion.createNamedQuery(namedQuery);
		if (null != parameters && parameters.size() > 0) {
			for (String name : parameters.keySet()) {
				query.setParameter(name, parameters.get(name));
			}
		}
		List<Object> results = query.getResultList();
		return results;
	}

}
