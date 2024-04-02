package pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T9432conscpcontin;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T9432conscpcontinPK;
import pe.gob.sunat.tecnologiams.arquitectura.framework.jpa.dao.BaseDao;

public interface T9432conscpcontinComprobanteContingencia extends BaseDao<T9432conscpcontin, T9432conscpcontinPK> {

	Integer buscarComprobanteContingencia(String numRuc, String codCpe, String numSerieCpe, String numCpe) throws ConexionException, ProcesoException, Exception;

}
