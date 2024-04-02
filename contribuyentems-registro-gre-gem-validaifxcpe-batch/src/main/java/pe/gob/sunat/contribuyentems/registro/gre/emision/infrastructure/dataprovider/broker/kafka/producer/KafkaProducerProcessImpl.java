package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.dataprovider.broker.kafka.producer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

import javax.inject.Inject;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.EnumSeg;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.GreConstantes;
import pe.gob.sunat.contribuyentems.registro.gre.emision.main.config.ValidaNegocioConfig;
import pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util.ConstantesUtils;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.utils.StringUtil;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.Producer;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.config.KafkaProducerConfig;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.exception.ErrorException;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.util.UtilLog;

public class KafkaProducerProcessImpl implements KafkaProducerProcess {

  @Inject
  private UtilLog utilLog;

  @Override
  public void enviarMensajeKafkaEnvioAceptado(Object mensaje) throws ErrorException {
    try {
      KafkaProducerConfig kafkaProducer = ValidaNegocioConfig.getConfig().obtenerKafkaProducerEnvioAceptado();

      if (kafkaProducer != null) {

        utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                MessageFormat.format("Si existe kafkaConsumer con el topico {0}", StringUtil.getJson(kafkaProducer)));

        Producer producer = new Producer(kafkaProducer);
        int intentos = 0;
        Boolean respuesta = Boolean.FALSE;
        do {
          intentos++;
          try {
            if (intentos > 1) Thread.sleep(1000 * (intentos - 1));
          } catch (Exception e) {
          }

          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO, "Enviando mensaje, intento: " + intentos);
          respuesta = producer.sendSync(mensaje);
        } while (intentos < GreConstantes.CANTIDAD_INTENTOS_PRODUCER && !respuesta);

        if (respuesta) {
          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                  MessageFormat.format("Se deja el mensaje en el topico {0} intentos: {1}", kafkaProducer.getTopic(), intentos));
        } else {
          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                  MessageFormat.format("No se logro dejar el mensaje en el topico {0} intentos: {1}", kafkaProducer.getTopic(), intentos));
        }
      } else {
        utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                MessageFormat.format("No existe kafkaConsumer con el topico {0}", ""));
      }

    } catch (Exception ex) {
      StringWriter printStackTrace = new StringWriter();
      ex.printStackTrace(new PrintWriter(printStackTrace));

      if (utilLog != null) {
        String msjTrace = printStackTrace.toString();
        utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR,
                MessageFormat.format("Error al dejar mensaje en Kafka ==> {0}", msjTrace),
                Thread.currentThread().getStackTrace());
      }
      throw ex;
    }

  }

  @Override
  public void enviarMensajeKafkaEnvioRechazado(Object mensaje) throws ErrorException {
    try {
      KafkaProducerConfig kafkaProducer = ValidaNegocioConfig.getConfig().obtenerKafkaProducerEnvioRechazado();

      if (kafkaProducer != null) {

        utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                MessageFormat.format("Si existe kafkaConsumer con el topico {0}", StringUtil.getJson(kafkaProducer)));

        Producer producer = new Producer(kafkaProducer);
        int intentos = 0;
        Boolean respuesta = Boolean.FALSE;
        do {
          intentos++;
          try {
            if (intentos > 1) Thread.sleep(1000 * (intentos - 1));
          } catch (Exception e) {
          }

          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO, "Enviando mensaje, intento: " + intentos);
          respuesta = producer.sendSync(mensaje);
        } while (intentos < GreConstantes.CANTIDAD_INTENTOS_PRODUCER && !respuesta);

        if (respuesta) {
          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                  MessageFormat.format("Se deja el mensaje en el topico {0} intentos: {1}", kafkaProducer.getTopic(), intentos));
        } else {
          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                  MessageFormat.format("No se logro dejar el mensaje en el topico {0} intentos: {1}", kafkaProducer.getTopic(), intentos));
        }


      } else {
        utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                MessageFormat.format("No existe kafkaConsumer con el topico {0}", ""));
      }

    } catch (Exception ex) {
      StringWriter printStackTrace = new StringWriter();
      ex.printStackTrace(new PrintWriter(printStackTrace));

      if (utilLog != null) {
        String msjTrace = printStackTrace.toString();
        utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR,
                MessageFormat.format("Error al dejar mensaje en Kafka ==> {0}", msjTrace),
                Thread.currentThread().getStackTrace());
      }
      throw ex;
    }

  }

  @Override
  public void enviarMensajeKafkaEnvioValidaciones(Object mensaje, String validacion) throws ErrorException {
    KafkaProducerConfig kafkaProducer = null;
    try {
      if (EnumSeg.VALIFXMAS.getKey().equals(validacion)) {
        kafkaProducer = ValidaNegocioConfig.getConfig().obtenerKafkaProducerEnvioIfxMasivo();
      } else if (EnumSeg.VALIFXREC.getKey().equals(validacion)) {
        kafkaProducer = ValidaNegocioConfig.getConfig().obtenerKafkaProducerEnvioIfxRecauda();
      } else if (EnumSeg.VALADUANA.getKey().equals(validacion)) {
        kafkaProducer = ValidaNegocioConfig.getConfig().obtenerKafkaProducerEnvioAduanas();
      }

      if (kafkaProducer != null) {

        utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                MessageFormat.format("Si existe kafkaConsumer con el topico {0}", StringUtil.getJson(kafkaProducer)));

        Producer producer = new Producer(kafkaProducer);
        int intentos = 0;
        Boolean respuesta = Boolean.FALSE;
        do {
          intentos++;
          try {
            if (intentos > 1) Thread.sleep(1000 * (intentos - 1));
          } catch (Exception e) {
          }

          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO, "Enviando mensaje, intento: " + intentos);
          respuesta = producer.sendSync(mensaje);
        } while (intentos < GreConstantes.CANTIDAD_INTENTOS_PRODUCER && !respuesta);

        if (respuesta) {
          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                  MessageFormat.format("Se deja el mensaje en el topico {0} intentos: {1}", kafkaProducer.getTopic(), intentos));
        } else {
          utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                  MessageFormat.format("No se logro dejar el mensaje en el topico {0} intentos: {1}", kafkaProducer.getTopic(), intentos));
        }


      } else {
        utilLog.imprimirLog(ConstantesUtils.LEVEL_INFO,
                MessageFormat.format("No existe kafkaConsumer con el topico {0}", ""));
      }

    } catch (Exception ex) {
      StringWriter printStackTrace = new StringWriter();
      ex.printStackTrace(new PrintWriter(printStackTrace));

      if (utilLog != null) {
        String msjTrace = printStackTrace.toString();
        utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR,
                MessageFormat.format("Error al dejar mensaje en Kafka ==> {0}", msjTrace),
                Thread.currentThread().getStackTrace());
      }
      throw ex;
    }

  }
}
