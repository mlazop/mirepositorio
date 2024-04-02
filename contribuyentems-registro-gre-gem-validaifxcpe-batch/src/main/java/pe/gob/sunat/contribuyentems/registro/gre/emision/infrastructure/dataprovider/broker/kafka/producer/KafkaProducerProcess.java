package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.dataprovider.broker.kafka.producer;

import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.exception.ErrorException;


public interface KafkaProducerProcess {
	
	void enviarMensajeKafkaEnvioAceptado(Object mensaje) throws ErrorException;
	void enviarMensajeKafkaEnvioRechazado(Object mensaje) throws ErrorException;
	void enviarMensajeKafkaEnvioValidaciones(Object mensaje, String validacion) throws ErrorException;
}
