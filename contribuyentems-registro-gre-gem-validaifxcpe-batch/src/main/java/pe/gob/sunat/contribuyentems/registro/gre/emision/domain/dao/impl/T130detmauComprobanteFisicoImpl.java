package pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.impl;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T130detmau;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T130detmauPK;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.T130detamuComprobanteFisico;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.utils.ConstantesUtils;
import pe.gob.sunat.tecnologiams.arquitectura.framework.jpa.dao.AbstractDao;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.util.UtilLog;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.net.ConnectException;

public class T130detmauComprobanteFisicoImpl extends AbstractDao<T130detmau, T130detmauPK>
		implements T130detamuComprobanteFisico {

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
	public Class<T130detmau> provideEntityClass() {
		return T130detmau.class;
	}

	@Override
	public Integer buscarRangoNumCpe(String numRuc, String codCpe, String numSerieCpe, Integer numCpe) throws ConexionException, ProcesoException, Exception{

		StringBuilder sb = new StringBuilder(
				"select a.t130_numruc, a.t130_tipdoc, a.t130_serie, a.t130_desde, a.t130_hasta, a.t130_userna, a.t130_fecact ");
		sb.append("from t130detmau a ");
		sb.append("where ");
		sb.append("a.t130_numruc = '" + numRuc + CONECTORAND);
		sb.append("a.t130_tipdoc = '" + codCpe + CONECTORAND);
		sb.append("a.t130_serie = '" + numSerieCpe + CONECTORAND);
		sb.append("a.t130_desde <= " + numCpe + " and a.t130_hasta >= " + numCpe);

		Query query = entityManager.createNativeQuery(sb.toString(), T130detmau.class);
		try {
			T130detmau t130 = (T130detmau) query.getSingleResult();
			return 1;
		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			Throwable a = e.getCause();
			while(a!=null) {
				if(a.getCause() instanceof ConnectException) {
					utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR, "buscarRangoNumCpe, ConnectException: " + e.getMessage());
    			throw new ConexionException(e);
				}
				a = a.getCause();
			}
			utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR, "buscarRangoNumCpe, Exception: " + e.getMessage());

			throw e;
		}

	}
}
