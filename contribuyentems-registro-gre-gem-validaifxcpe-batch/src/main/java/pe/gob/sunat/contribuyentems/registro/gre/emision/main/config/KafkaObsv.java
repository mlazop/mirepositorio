package pe.gob.sunat.contribuyentems.registro.gre.emision.main.config;

import pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.dataprovider.broker.kafka.consumer.KafkaConsumer;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.Consumer;
 
public class KafkaObsv {
 
    public KafkaObsv() {
        Consumer observable = new Consumer(ValidaNegocioConfig.getConfig().getKafkaConsumerValidaIfxCpe());
        KafkaConsumer to = new KafkaConsumer(observable);
        observable.addObserver(to);
        observable.subscribe();
    }
}