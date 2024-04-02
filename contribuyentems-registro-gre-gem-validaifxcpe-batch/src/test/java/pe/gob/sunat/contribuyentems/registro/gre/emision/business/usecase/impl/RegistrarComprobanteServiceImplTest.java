package pe.gob.sunat.contribuyentems.registro.gre.emision.business.usecase.impl;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.comprobante.domain.dao.ComprobanteGRERepository;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.frecuentes.domain.dao.FrecuentesGRERepository;
import pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.dataprovider.broker.kafka.producer.KafkaProducerProcess;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.util.UtilLog;

public class RegistrarComprobanteServiceImplTest {

	@InjectMocks
	@Spy
	private ValidaNegocioServiceImpl registrarComprobanteServiceImpl;

	@Mock
    private KafkaProducerProcess kafkaProducerProcess;
	
	@Mock
    private KafkaProducerProcess kafkaProducerProcessRevertir;
    
	@Mock
    private UtilLog utilLog;
    
    @Mock
    private ComprobanteGRERepository comprobanteRepository;

    @Mock
    private FrecuentesGRERepository comprobanteFrecuenteRepository;

    
	private String vJsonRegistrar = "{\"numRuc\":\"10097488431\",\"codCpe\":\"01\",\"numSerieCpe\":\"E001\",\"numCpe\":237,\"numOperacion\":\"290\",\"indPagoTotal\":false,\"esComprobanteFrecuente\":true,\"esEmisorElectronico\":true,\"comprobante\":{\"numRuc\":\"10097488431\",\"codCpe\":\"02\",\"numSerieCpe\":\"E001\",\"numCpe\":57,\"numOperacion\":1,\"codEstadoCpe\":\"0\",\"fecEmision\":\"2021-02-01T23:28:56.782Z\",\"fecVencimiento\":\"2020-02-01T23:28:56.782Z\",\"codInciso\":\"A\",\"desServicio\":\"ASESORIA INFORMATICA\",\"obsRecibo\":\"Observacion del Recibo de Honorario a Emitir\",\"codMoneda\":\"PEN\",\"fechaRegistro\":\"2020-01-01T23:28:56.782Z\",\"indServicioGratuito\":false,\"emisor\":{\"desRazonSocial\":\"EMPRESA DE PRUEBA\",\"desCorreo\":\"victorbaca2@yahoo.es\",\"codUbigeo\":\"010101\",\"desDepartamento\":\"LIMA\",\"desProvincia\":\"LIMA\",\"desDistrito\":\"SAN BORJA\",\"desDireccion\":\"av. san borja norte 234\",\"numTelefono\":\"987654321\"},\"receptor\":{\"codTipoDoc\":\"01\",\"numDocIdentidad\":\"41271139\",\"desRazonSocial\":\"EMPRESA DE PRUEBA\",\"desCorreo\":\"vbaca@gmail.com\",\"codUbigeo\":\"010101\",\"desDepartamento\":\"LIMA\",\"desProvincia\":\"LIMA\",\"desDistrito\":\"SAN BORJA\",\"desDireccion\":\"av. san borja norte 234\",\"numTelefono\":\"987654321\"},\"indPagoTotal\":false,\"mtoTributo\":0.00,\"mtoNC\":0.00,\"mtoNeto\":0.00,\"mtoTotalCpe\":150.00,\"mtoTotalPagos\":0.00,\"mtoTasTributo\":0.00,\"montoNetoPendientePago\":150.00,\"lstPagos\":[{\"numPago\":1,\"codMedioPago\":\"008\",\"mtoPago\":2,\"codMoneda\":\"PEN\",\"fecPago\":\"2020-01-01T18:28:56\",\"numOperacionEliminacion\":2,\"fechaOperacionEliminacion\":\"2020-02-01T18:28:56\",\"indEstadoPago\":\"R\"},{\"numPago\":2,\"codMedioPago\":\"008\",\"mtoPago\":4,\"codMoneda\":\"PEN\",\"fecPago\":\"2020-01-02T18:28:56\",\"indEstadoPago\":\"R\"}],\"auditoria\":{\"codUsuRegis\":\"VICTOR\",\"fecRegis\":\"2020-02-01T23:28:56.782Z\",\"codUsuModif\":\"VICTOR\",\"fecModif\":\"2020-02-03T23:28:56.782Z\"}}}";
	private String vJsonRevertir = "{\"indPagoTotal\":false,\"comprobante\":{\"numRuc\":\"10097488431\",\"codCpe\":\"02\",\"numSerieCpe\":\"E001\",\"numCpe\":3,\"numOperacion\":-1,\"indServicioGratuito\":false,\"reversion\":{\"numOperacionReversion\":291,\"fecReversion\":\"2021-02-01T09:20:04.882Z\"},\"indPagoTotal\":false}}";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	/*
	@Test
	public void registrarComprobanteTest01() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MensajeVO mensajeVO = mapper.readValue(vJsonRegistrar, MensajeVO.class);
		Comprobante comprobante = mensajeVO.getComprobante();
		comprobante.setId(new ObjectId());
		Mockito.when(comprobanteRepository.save(any(Comprobante.class))).thenReturn(comprobante);
		try {
			registrarComprobanteServiceImpl.emitirComprobante(vJsonRegistrar);
			assertTrue("Se ejecuto corectamente registrarComprobanteTest01",true);
		} catch (Exception e) {
		}
	}*/
    
//	@Test
//	public void revertirComprobanteTest01()  {
//		try {
//			registrarComprobanteServiceImpl.revertirComprobante(vJsonRevertir);
//			assertTrue("Se ejecuto corectamente revertirComprobanteTest01",true);
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}
}
