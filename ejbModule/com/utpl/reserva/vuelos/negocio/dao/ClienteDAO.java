package com.utpl.reserva.vuelos.negocio.dao;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.utpl.reserva.vuelos.negocio.core.DaoJpaImpl;

import modelo.Cliente;

@Stateless
public class ClienteDAO  extends DaoJpaImpl<Cliente, Integer> {

	
	public 	BigDecimal obtenerIDCliente(){
		StringBuffer builder = new StringBuffer();
		builder.append(" SELECT MAX(ID_CLIENTE) + 1  FROM CLIENTE; ");
		Query query = this.createNativeQuery(builder.toString());
		BigDecimal idMax = (BigDecimal) query.getSingleResult();
		return idMax;
	}
	
	
	public Cliente guardarCliente(Cliente cliente){
		BigDecimal idMax = this.obtenerIDCliente(); 
		cliente.setIdCliente(idMax.intValue());
		cliente = this.create(cliente);
		return cliente;
	}
	
	}
