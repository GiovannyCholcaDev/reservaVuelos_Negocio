package com.utpl.reserva.vuelos.negocio.dao;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.utpl.reserva.vuelos.negocio.core.DaoJpaImpl;

import modelo.Itinerario;
import modelo.vistas.ItinerarioTransient;

@Stateless
public class ItinerarioDAO extends DaoJpaImpl<Itinerario, Integer> {

	@SuppressWarnings("unchecked")
	public List<ItinerarioTransient> obtenerItinerarios() {
		List<ItinerarioTransient> itinerariosCol = new ArrayList<>();
		StringBuffer builder = new StringBuffer();
		builder.append(" SELECT IT.ID_ITINERARIO as idItinerario ");
		builder.append(" ,RT.ID_RUTA as idRuta, RT.ABORDAJE ");
		builder.append(" ,ARO.ID_AEROPUERTO as idAeropuertoOrigen, ARO.NOMBRE_AEROPUERTO as nombreAeropuertoOrigen, ARO.ubicacion as ubicacionOrigen, ARO.CIUDAD as ciudadOrigen, ARO.ABREVIATURA as abreviaturaOrigen ");
		builder.append(" ,ARD.ID_AEROPUERTO as idAeropuertoDestino, ARD.NOMBRE_AEROPUERTO as nombreAeropuertoDestino,ARD.ubicacion as ubicacionDestino, ARD.CIUDAD as ciudadDestino, ARD.ABREVIATURA as abreviaturaDestino ");
		builder.append(" ,DI.FECHA as fechaOrigen, HI.HORA as horaOrigen ");
		builder.append(" ,DL.FECHA as fechaDestino, HL.HORA as horaDestino ");	
		builder.append(" ,IT.DURACION ");
		builder.append(" ,CC.TIPO_CLASE as tipoClase ");
		builder.append(" ,AV.AEROLINEA, AV.NUMASIENTOS, AV.NUMASIENTOSPRIMERACLASE, AV.NUMASIENTOSECONOMICA, AV.ESTADO_AVION as estadoAvion, AV.TIPO_AVION as tipoAvion ");
		builder.append(" ,VU.ID_VUELO as idVuelo, VU.ESTADO_VUELO AS numeroVuelo ");
		builder.append(" ,TA.VALOR_TARIFA as valorTarifa, (TA.IMPUESTO_TARIFA + TA.TASA_TARIFA) AS impuestoTasa, (TA.VALOR_TARIFA + (TA.IMPUESTO_TARIFA + TA.TASA_TARIFA)) as totalPagarTarifa ");
		builder.append(" FROM RUTA RT ");
		builder.append(" INNER JOIN AEROPUERTO ARO ON RT.ID_AEROPUERTO_ORIGEN = ARO.ID_AEROPUERTO ");
		builder.append(" INNER JOIN AEROPUERTO ARD ON RT.ID_AEROPUERTO_DESTINO = ARD.ID_AEROPUERTO ");
		builder.append(" INNER JOIN HORARIO HOI ON RT.ID_HORARIO_IDA = HOI.IDHORARIO ");
		builder.append(" INNER JOIN DIA DI  ON HOI.ID_DIA = DI.ID_DIA ");
		builder.append(" INNER JOIN HORA HI ON HOI.IDHORA = HI.IDHORA ");
		builder.append(" INNER JOIN HORARIO HOL ON RT.ID_HORARIO_LLEGADA = HOL.IDHORARIO ");
		builder.append(" INNER JOIN DIA DL  ON HOL.ID_DIA = DL.ID_DIA ");
		builder.append(" INNER JOIN HORA HL ON HOL.IDHORA = HL.IDHORA ");
		builder.append(" INNER JOIN ITINERARIO IT ON RT.ID_RUTA = IT.ID_RUTA ");
		builder.append(" INNER JOIN clasificacioncabina CC ON IT.ID_CLASIFICACION_CABINA = CC.ID_CLASIFICACION_CABINA ");
		builder.append(" INNER JOIN AVION AV ON IT.ID_AVION = AV.ID_AVION ");
		builder.append(" LEFT JOIN VUELO VU ON IT.ID_ITINERARIO = VU.ID_ITINERARIO ");
		builder.append(" INNER JOIN TARIFA TA ON VU.ID_TARIFA = TA.ID_TARIFA ");
		builder.append(" ORDER BY DI.FECHA ");
		
		Query query = this.createNativeQuery(builder.toString());
		List<Object[]> results = (List<Object[]> ) query.getResultList();
		results.size();
		for (Object[] objects : results) {
			ItinerarioTransient itinerarioTr = new ItinerarioTransient();
			itinerarioTr.setIdItinerario((Integer) objects[0]);
			itinerarioTr.setIdRuta((Integer) objects[1]);
			itinerarioTr.setAbordaje((String) objects[2]);
			itinerarioTr.setIdAeropuertoOrigen((Integer) objects[3]);
			itinerarioTr.setNombreAeropuertoOrigen((String) objects[4]);
			itinerarioTr.setUbicacionOrigen((String) objects[5]);
			itinerarioTr.setCiudadOrigen((String) objects[6]);
			itinerarioTr.setAbreviaturaOrigen((String) objects[7]);
			
			itinerarioTr.setIdAeropuertoDestino((Integer) objects[8]);
			itinerarioTr.setNombreAeropuertoDestino((String) objects[9]);
			itinerarioTr.setUbicacionDestino((String) objects[10]);
			itinerarioTr.setCiudadDestino((String) objects[11]);
			itinerarioTr.setAbreviaturaDestino((String) objects[12]);
			
			itinerarioTr.setFechaOrigen((Date) objects[13]);
			itinerarioTr.setHoraOrigen((Time) objects[14]);
			itinerarioTr.setFechaDestino((Date) objects[15]);
			itinerarioTr.setHoraDestino((Time) objects[16]);
			
			itinerarioTr.setDuracion((Time) objects[17]);
			itinerarioTr.setTipoClase((String) objects[18]);
			itinerarioTr.setAerolinea((String) objects[19]);
			
			itinerarioTr.setNumAsientos((Integer) objects[20]);
			itinerarioTr.setNumAsientosPrimeraClase((Integer) objects[21]);
			itinerarioTr.setNumAsientosEconomica((Integer) objects[22]);
			itinerarioTr.setEstadoAvion((String) objects[23]);
			itinerarioTr.setTipoAvion((String) objects[24]);
			
			itinerarioTr.setIdVuelo((Integer) objects[25]);
			itinerarioTr.setNumeroVuelo((String) objects[26]);
			
			itinerarioTr.setValorTarifa((BigDecimal) objects[27]);
			itinerarioTr.setImpuestoTasa((BigDecimal) objects[28]);
			itinerarioTr.setTotalPagarTarifa((BigDecimal) objects[29]);
			itinerariosCol.add(itinerarioTr);
		}
		return itinerariosCol;
	}
}
