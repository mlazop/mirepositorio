package pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T4241cabcpe;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T4241cabcpePK;
import pe.gob.sunat.tecnologiams.arquitectura.framework.jpa.dao.BaseDao;

public interface T4241cabcpeComprobanteElectronico extends BaseDao<T4241cabcpe, T4241cabcpePK>{

	Integer buscarComprobantePagoElectronico(String numRuc, String codCpe, String numSerieCpe, Integer numCpe) throws ConexionException, ProcesoException, Exception;
	
}
