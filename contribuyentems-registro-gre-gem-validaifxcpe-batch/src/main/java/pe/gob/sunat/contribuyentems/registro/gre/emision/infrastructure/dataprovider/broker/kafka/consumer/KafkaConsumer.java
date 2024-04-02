
package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.dataprovider.broker.kafka.consumer;

import java.util.Observable;
import java.util.Observer;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.business.usecase.impl.ValidaNegocioServiceImpl;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.Consumer;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.exception.ErrorException;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.exception.LoopException;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.exception.RetriesException;
import pe.gob.sunat.tecnologiams.arquitectura.framework.common.util.ConstantesUtils;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.util.UtilLog;

public class KafkaConsumer implements Observer {
	private UtilLog utilLog;
	private ValidaNegocioServiceImpl registrarComprobanteService;
	private Consumer observable = null;

	public KafkaConsumer(Consumer observable) {
		this.observable = observable;
		BeanManager bm = CDI.current().getBeanManager();
		Bean<UtilLog> bean = (Bean<UtilLog>) bm.getBeans(UtilLog.class).iterator().next();
		CreationalContext<UtilLog> ctx = bm.createCreationalContext(bean);
		UtilLog uLog = (UtilLog) bm.getReference(bean, UtilLog.class, ctx);
		utilLog = uLog;

		Bean<ValidaNegocioServiceImpl> beanService = (Bean<ValidaNegocioServiceImpl>) bm
				.getBeans(ValidaNegocioServiceImpl.class).iterator().next();
		CreationalContext<ValidaNegocioServiceImpl> ctxService = bm.createCreationalContext(beanService);
		ValidaNegocioServiceImpl cacheService = (ValidaNegocioServiceImpl) bm.getReference(beanService,
				ValidaNegocioServiceImpl.class, ctxService);

		registrarComprobanteService = cacheService;

		utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO, "********************* START KafkaConsumer ********************* ",
				Thread.currentThread().getStackTrace());

	}

	@Override
	public void update(Observable obs, Object obj) {
		
		try {
			ConsumerRecord<String, Object> record = (ConsumerRecord<String, Object>) obj;
			utilLog.imprimirLog(ConstantesUtils.LEVEL_DEBUG, "MESSAGE PRODUCER-->" + record.value(),
					Thread.currentThread().getStackTrace());

			registrarComprobanteService.validaNegocio(record.value().toString());
		} catch (ConexionException c) {
			
			utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR, "Excepcion CreateConnectionException {}", c.getStackTrace());
			
			throw new LoopException(c);
			
		} catch (ProcesoException p) {
			
			utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR, "Excepcion ProcesoException {}", p.getStackTrace());
			
			throw new RetriesException(p);
			
		} catch (Exception e) {
			utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR,
					"Excepcion ErrorException {}, posible envio a topico para reproceso " + UtilLog.getStackTraceFromException(e));
			throw new ErrorException(e);
		}
		
	}
}