package pe.gob.sunat.contribuyentems.registro.gre.emision.main.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import static javax.persistence.SynchronizationType.SYNCHRONIZED;

public class EntityManagerInit {
	@PersistenceContext(unitName = "cpe")
	private EntityManager emCpe;

	@Produces
	@Named("cpe")
	@ApplicationScoped
	public EntityManager createEntityManagerFactoryCpe() {
		if (emCpe == null) {
			return getEntityManager("cpe");
		}
		return emCpe;
	}

	private EntityManager getEntityManager(String unitName) {
		return Persistence.createEntityManagerFactory(unitName).createEntityManager(SYNCHRONIZED);
	}
}