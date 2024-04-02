package pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.impl;

import java.net.ConnectException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T9432conscpcontin;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T9432conscpcontinPK;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.T9432conscpcontinComprobanteContingencia;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.utils.ConstantesUtils;
import pe.gob.sunat.tecnologiams.arquitectura.framework.jpa.dao.AbstractDao;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.util.UtilLog;

public class T9432conscpcontinComprobanteContingenciaImpl extends AbstractDao<T9432conscpcontin, T9432conscpcontinPK> implements T9432conscpcontinComprobanteContingencia {

	
	private static final String CONECTORAND = "' and ";
	
	@Inject
	@Named("cpe")
	private EntityManager entityManager;
	
	@Inject
	private UtilLog utilLog;
	
	@Override
	public EntityManager buildEntityManager() {
		return entityManager;
	}

	@Override
	public Class<T9432conscpcontin> provideEntityClass() {
		return T9432conscpcontin.class;
	}

	@Override
	public Integer buscarComprobanteContingencia(String numRuc, String codCpe, String numSerieCpe, String numCpe)
			throws ConexionException, ProcesoException, Exception {
		
		StringBuilder sb = new StringBuilder(
				"select a.num_fv816, a.num_ruc, a.num_establecimiento, a.cod_cp, a.num_serie_cp, a.num_inicial_cp, a.num_final_cp, a.cnt_cp_uso, a.cnt_cp_baja_ope, a.cnt_cp_baja_of, a.cnt_cp_baja_dif, a.cod_estado, a.fec_aut, a.cod_usuregis, a.fec_regis, a.ind_padron ");
		sb.append("from t9432conscpcontin a ");
		sb.append("where ");
		sb.append("a.num_ruc = '" + numRuc + CONECTORAND);
		sb.append("a.cod_cp = '" + codCpe + CONECTORAND);
		sb.append("a.num_serie_cp = " + Integer.parseInt(numSerieCpe) + " AND ");
		sb.append("a.num_inicial_cp <= " + numCpe + " and a.num_final_cp >= " + numCpe);

		Query query = entityManager.createNativeQuery(sb.toString(), T9432conscpcontin.class);
		try {
			T9432conscpcontin t9432 = (T9432conscpcontin) query.getSingleResult();
			return 1;
		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			Throwable a = e.getCause();
			while(a!=null) {
				if(a.getCause() instanceof ConnectException) {
					utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR, "buscarComprobanteContingencia, ConnectException: " + e.getMessage());
    			throw new ConexionException(e);
				}
				a = a.getCause();
			}
			utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR, "buscarComprobanteContingencia, Exception: " + e.getMessage());

			throw e;
		}
	}

}
