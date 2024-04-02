package pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.impl;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T4241cabcpe;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.T4241cabcpePK;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.T4241cabcpeComprobanteElectronico;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.utils.ConstantesUtils;
import pe.gob.sunat.tecnologiams.arquitectura.framework.jpa.dao.AbstractDao;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.util.UtilLog;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.net.ConnectException;

public class T4241cabcpeComprobanteElectronicoImpl extends AbstractDao<T4241cabcpe, T4241cabcpePK>
		implements T4241cabcpeComprobanteElectronico {

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
	public Class<T4241cabcpe> provideEntityClass() {
		return T4241cabcpe.class;
	}

	@Override
	public Integer buscarComprobantePagoElectronico(String numRuc, String codCpe, String numSerieCpe, Integer numCpe)
			throws ConexionException, ProcesoException, Exception {

		StringBuilder sb = new StringBuilder(
				"select FIRST 1 a.num_ruc, a.cod_cpe, a.num_serie_cpe, a.num_cpe,	a.cod_tipo_cpe, a.cod_docide_recep, a.num_docide_recep,"
						+ " a.des_nombre_recep, a.cod_moneda, a.des_observacion, a.ind_estado, a.ind_rechazo, a.mto_importe_total, "
						+ " a.num_id_xml, a.fec_emision, a.cod_est_anexo, a.ind_procedencia, a.mto_total_igv, a.mto_total_venta, a.num_per_regven, "
						+ " a.num_per_regcom, a.num_cuo_regven, a.cod_usumodif, a.fec_modif ");
		sb.append("from t4241cabcpe a ");
		sb.append("where ");
		sb.append("a.num_ruc = '" + numRuc + CONECTORAND);
		sb.append("a.cod_cpe = '" + codCpe + CONECTORAND);
		sb.append("a.num_serie_cpe = '" + numSerieCpe + CONECTORAND);
		sb.append("a.num_cpe = " + numCpe + " ");

		Query query = entityManager.createNativeQuery(sb.toString(), T4241cabcpe.class);
		try {
			T4241cabcpe t4241 = (T4241cabcpe) query.getSingleResult();

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
