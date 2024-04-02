package pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T130detmau;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T130detmauPK;
import pe.gob.sunat.tecnologiams.arquitectura.framework.jpa.dao.BaseDao;

public interface T130detamuComprobanteFisico extends BaseDao<T130detmau, T130detmauPK>{

	Integer buscarRangoNumCpe(String numRuc, String codCpe, String numSerieCpe, Integer numCpe) throws ConexionException, ProcesoException, Exception;
}
