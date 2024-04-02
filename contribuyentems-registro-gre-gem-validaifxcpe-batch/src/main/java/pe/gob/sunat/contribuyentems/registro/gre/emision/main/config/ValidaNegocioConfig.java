package pe.gob.sunat.contribuyentems.registro.gre.emision.main.config;

import java.util.Arrays;
import java.util.List;
import pe.gob.sunat.tecnologia.arquitectura.framework.mongodb.config.MongoDBConfig;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.config.KafkaConsumerConfig;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.config.KafkaProducerConfig;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.config.MicroserviceConfig;

public class ValidaNegocioConfig extends MicroserviceConfig {

  private static ValidaNegocioConfig config;
  private KafkaConsumerConfig kafkaConsumerValidaIfxCpe;
  private KafkaProducerConfig kafkaProducerEnvioAceptado;
  private KafkaProducerConfig kafkaProducerEnvioRechazado;
  private KafkaProducerConfig kafkaProducerEnvioIfxMasivo;
  private KafkaProducerConfig kafkaProducerEnvioIfxRecauda;
  private KafkaProducerConfig kafkaProducerEnvioAduanas;
  private MongoDBConfig mongodb;

  public static ValidaNegocioConfig getConfig() {
    return config;
  }

  public void loadConfig() {
    ValidaNegocioConfig.config = this;
  }

  public KafkaConsumerConfig getKafkaConsumerValidaIfxCpe() {
    return kafkaConsumerValidaIfxCpe;
  }

  public void setKafkaConsumerValidaIfxCpe(KafkaConsumerConfig kafkaConsumerValidaIfxCpe) {
    this.kafkaConsumerValidaIfxCpe = kafkaConsumerValidaIfxCpe;
  }

  public KafkaProducerConfig getKafkaProducerEnvioAceptado() {
    return kafkaProducerEnvioAceptado;
  }

  public void setKafkaProducerEnvioAceptado(KafkaProducerConfig kafkaProducerEnvioAceptado) {
    this.kafkaProducerEnvioAceptado = kafkaProducerEnvioAceptado;
  }

  public KafkaProducerConfig getKafkaProducerEnvioRechazado() {
    return kafkaProducerEnvioRechazado;
  }

  public void setKafkaProducerEnvioRechazado(KafkaProducerConfig kafkaProducerEnvioRechazado) {
    this.kafkaProducerEnvioRechazado = kafkaProducerEnvioRechazado;
  }



	public KafkaProducerConfig getKafkaProducerEnvioIfxMasivo() {
		return kafkaProducerEnvioIfxMasivo;
	}

	public void setKafkaProducerEnvioIfxMasivo(KafkaProducerConfig kafkaProducerEnvioIfxMasivo) {
		this.kafkaProducerEnvioIfxMasivo = kafkaProducerEnvioIfxMasivo;
	}

	public KafkaProducerConfig getKafkaProducerEnvioIfxRecauda() {
		return kafkaProducerEnvioIfxRecauda;
	}

	public void setKafkaProducerEnvioIfxRecauda(KafkaProducerConfig kafkaProducerEnvioIfxRecauda) {
		this.kafkaProducerEnvioIfxRecauda = kafkaProducerEnvioIfxRecauda;
	}

	public KafkaProducerConfig getKafkaProducerEnvioAduanas() {
		return kafkaProducerEnvioAduanas;
	}

	public void setKafkaProducerEnvioAduanas(KafkaProducerConfig kafkaProducerEnvioAduanas) {
		this.kafkaProducerEnvioAduanas = kafkaProducerEnvioAduanas;
	}

	public MongoDBConfig getMongodb() {
    return mongodb;
  }

  public void setMongodb(MongoDBConfig mongodb) {
    this.mongodb = mongodb;
  }

  public KafkaProducerConfig obtenerKafkaProducerEnvioAceptado() {
    List<KafkaProducerConfig> producers = Arrays.asList(this.getKafkaProducerEnvioAceptado());
    return producers.stream().filter(kp -> true).findFirst().orElse(null);
  }
  
  public KafkaProducerConfig obtenerKafkaProducerEnvioRechazado() {
    List<KafkaProducerConfig> producers = Arrays.asList(this.getKafkaProducerEnvioRechazado());
    return producers.stream().filter(kp -> true).findFirst().orElse(null);
  }
  
  public KafkaProducerConfig obtenerKafkaProducerEnvioIfxMasivo() {
    List<KafkaProducerConfig> producers = Arrays.asList(this.getKafkaProducerEnvioIfxMasivo());
    return producers.stream().filter(kp -> true).findFirst().orElse(null);
  }
  
  public KafkaProducerConfig obtenerKafkaProducerEnvioIfxRecauda() {
    List<KafkaProducerConfig> producers = Arrays.asList(this.getKafkaProducerEnvioIfxRecauda());
    return producers.stream().filter(kp -> true).findFirst().orElse(null);
  }
  
  public KafkaProducerConfig obtenerKafkaProducerEnvioAduanas() {
    List<KafkaProducerConfig> producers = Arrays.asList(this.getKafkaProducerEnvioAduanas());
    return producers.stream().filter(kp -> true).findFirst().orElse(null);
  }
}