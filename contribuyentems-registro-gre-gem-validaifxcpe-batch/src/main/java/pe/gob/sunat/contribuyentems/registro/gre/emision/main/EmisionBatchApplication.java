package pe.gob.sunat.contribuyentems.registro.gre.emision.main;

import java.util.Locale;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.validation.internal.ValidationExceptionMapper;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;

import io.dropwizard.lifecycle.ServerLifecycleListener;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import pe.gob.sunat.contribuyentems.registro.gre.emision.main.config.ValidaNegocioConfig;
import pe.gob.sunat.contribuyentems.registro.gre.emision.main.config.KafkaObsv;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.exceptions.GenericExceptionMapper;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.exceptions.UnprocessableEntityExceptionMapper;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.exceptions.WebApplicationExceptionMapper;
import pe.gob.sunat.tecnologia3.arquitectura.framework.kafka.healtcheck.KafkaConsumerHealthCheck;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.SunatApplication;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.exception.JsonParseExceptionMapper;

public class EmisionBatchApplication extends SunatApplication<ValidaNegocioConfig> {

    @Override
    public void onRun(ValidaNegocioConfig config, Environment environment) {

        environment.jersey().register(ValidationExceptionMapper.class);
        environment.jersey().register(JsonParseExceptionMapper.class);
        environment.jersey().register(JsonMappingExceptionMapper.class);
        environment.jersey().register(WebApplicationExceptionMapper.class);
        environment.jersey().register(GenericExceptionMapper.class);
        environment.jersey().register(UnprocessableEntityExceptionMapper.class);
        config.loadConfig();
		configureKafka(config, environment);

    }

    @Override
    public void onInitialize(Bootstrap<ValidaNegocioConfig> bootstrap) {
        bootstrap.addBundle(new ViewBundle<ValidaNegocioConfig>());
    }

    public static void main(String... params) throws Exception {
        Locale.setDefault(new Locale("es", "ES"));
        new EmisionBatchApplication().run(params);
    }
    
    private void configureKafka(ValidaNegocioConfig configuration, Environment environment) {
		environment.lifecycle().addServerLifecycleListener(new ServerLifecycleListener() {
			@Override
			public void serverStarted(Server server) {
				new Thread(() -> new KafkaObsv(), configuration.getKafkaConsumerValidaIfxCpe().getTopic()).start();
			}
		});
		
		if (configuration.getKafkaConsumerValidaIfxCpe().isKafkaHealthCheck()) {
			environment.healthChecks().register("healthCheck",
					new KafkaConsumerHealthCheck(configuration.getKafkaConsumerValidaIfxCpe().getTopic()));
		}
	}

}
