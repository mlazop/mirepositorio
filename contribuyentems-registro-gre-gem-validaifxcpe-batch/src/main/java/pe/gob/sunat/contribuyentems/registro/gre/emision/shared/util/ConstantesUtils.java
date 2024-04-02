package pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util;

public class ConstantesUtils {

	//CONSTANTES VALIDANEGOCIO
	public static final String COD_ESTADO_ACTIVO = "00";
	public static final String IND_CONDICION_ACTIVO = "00";
	public static final String[] COD_TRIBUTO = {"030101","030201","030401"};
	public static final String COD_TIPO_CPE_REMITENTE = "09";
	public static final String COD_TIPO_CPE_TRANSPORTISTA = "31";
	public static final String[] COD_MOTIVO_BAJA = {"01","02"};
	public static final String[] IND_TRASLADO_VEHICULOS = {"M1","L"};
	public static final String IND_MODALIDAD_TRASLADO = "01";
	public static final String TIP_DOC_IDE_RUC = "6";
	public static final String TIP_DOC_IDE_DNI = "1";
	public static final String VACIO = "";
	public static final String COD_SUBPARTIDA_NACIONAL = "1042";
	public static final String TIPO_DOCUMENTO_01 = "01";
	public static final String TIPO_DOCUMENTO_03 = "03";
	public static final String TIPO_DOCUMENTO_04 = "04";
	public static final String TIPO_DOCUMENTO_09 = "09";
	public static final String TIPO_DOCUMENTO_31 = "31";
	public static final String TIPO_DOCUMENTO_48 = "48";
	public static final String TIPO_DOCUMENTO_49 = "49";
	public static final String TIPO_DOCUMENTO_50 = "50";
	public static final String TIPO_DOCUMENTO_52 = "52";
	public static final String TIPO_DOCUMENTO_80 = "80";
	public static final String[] COD_COMPROBANTE_ELECTRONICO = {"01","03","04"};
	public static final String[] COD_COMPROBANTE_FISICO = {"01","03","04","09","48"};
	public static final String CAMPO_NUMRUC = "numRuc";
	public static final String CAMPO_CODCPE = "codCpe";
	public static final String CAMPO_DOCRELACIONADO_CODTIPODOCUMENTO = "docRelacionado.codTipoDocumento";
	public static final String CAMPO_DOCRELACIONADO_NUMDOCUMENTO = "docRelacionado.numDocumento";
	public static final String CAMPO_DOCRELACIONADO_NUMRUC = "docRelacionado.numRuc";
	public static final String CAMPO_EMISION_FECEMISION = "emision.fecEmision";
	public static final Integer NUMDIASMAX = 15;
	public static final String CAMPO_NUMPARAMETRO = "numParametro";
	public static final String CAMPO_LISTAPARAMETROS_CODPARAMETRO = "listaParametros.codParametro";
	public static final String CAMPO_CODPARTIDA = "codPartida";
	//
	
	public static final String IND_VALIDACION_OK = "0";
	public static final String IND_VALIDACION_ERROR = "1";
	public static final String IND_VALIDACION_EXCEPCION = "2";
	
  public static final String LOG_BEAN = "logBean";
  public static final String LOG_INPUT = "input";
  public static final String TRAZA = "traza";
  public static final String TRAZA_EXCEPTION = "trazaException";
  public static final String TRAZA_DEBUG = "trazaDebug";
  public static final String LEVEL_INFO = "INFO"; 
  public static final String LEVEL_DEBUG = "DEBUG";
  public static final String LEVEL_ERROR = "ERROR";
  public static final String LEVEL_WARN = "WARN";
  public static final String TRAZA_JPA = "trazaJpa";
  public static final String NUM_RUC_ACTIVO = "00";
  public static final String COD_COMPROBANTE_RHE = "02";
  public static final String COD_SERIE_COMPROBANTE_RHE = "E001";

	public static final String DATE_FORMAT_JSON = "yyyy-MM-dd'T'HH:mm:ss";
  public static final String TOPICO_REPLICA_COMPROBANTE = "topic-contribuyentems-servicio-replica-comprobante";
  public static final String TOPICO_REPLICA_REVERSION = "topic-contribuyentems-replica-gre-revertir-comprobante";
  public static final String LINEA_CABECERA = "================================================================================";

	public static final String IND_TIPO_1 = "1";
	public static final String IND_ESTADO_EMI = "0";
	public static final String NUM_INDICE_1 = "1";
	public static final String COD_ANEXO_0000 = "0000";
	public static final String IND_ESTADO_SER = "0";
  public static final String IND_FRECUENTE_NO = "0";
  public static final String IND_FRECUENTE_SI = "1";
  public static final String MODTRASLADO_PUBLICO = "1";
  public static final String MODTRASLADO_PRIVADO = "2";
  public static final String ACTIVO_FRECUENTE = "1";
  public static final String FAVORITO_FRECUENTE = "0";
  public static final String COD_CPE_GRE_REMITENTE = "09";
  public static final String COD_CPE_GRE_TRANSPORTISTA = "31";
  public static final String COD_CPE_GRE_POR_EVENTOS = "G9";
  public static final String DESC_CPE_GRE_POR_EVENTOS = "Guía de Remisión por Eventos";
    //public static final String URI_BASE_ADUANA = "http://api.sunat.peru";
    public static final String COD_MOTTRASLADO_IMPOR = "08";
    public static final String COD_MOTTRASLADO_EXPOR = "09";
    public static final String COD_MOTTRASLADO_OTROS = "13";
    public static final String TOTAL = "1";
    public static final String NO_TOTAL = "0";
  public static final String IND_TIPO_VEHICULO_PRIN = "1";
  public static final String ddMMyy_SLASH = "dd/MM/yy";
  public static final String yyyyMMdd = "yyyy-MM-dd";
  public static final String IND_TIPO_VEH_PRI = "1";
  public static final String IND_TIPO_VEH_SEC = "2";
  public static final String DES_TIPO_VEH_PRI = "Principal";
  public static final String DES_TIPO_VEH_SEC = "Secundario";
  public static final String IND_TUC_CHV = "1";
  public static final String URI_INGRESO = "/v1/controladuanero/ingreso/declaracion/e/declaracionesaduaneras/";
  public static final String URI_SERIES_INGRESO = "/v1/controladuanero/ingreso/declaracion/e/declaracionesaduaneras/";
  public static final String URI_SALIDA = "/v1/controladuanero/salida/declaracion/e/declaracionesexportaciones/";
  public static final String URI_SERIES_SALIDA = "/v1/controladuanero/ingreso/declaracion/e/declaracionesexportaciones/";
  public static final String TIPO_DUC_RUC = "6";
  public static final String GUION = "-";
  public static final String IND_TIP_TRASL_TOT = "1";
  public static final String CON_SALDO = "1";
  public static final String NO_FRECUENTE = "0";
  public static final String REG_EXP_48 = "48";
  public static final String REG_EXP_10 = "10";
  public static final String REG_EXP_18 = "18";
  public static final String REG_EXP_40 = "40";
  public static final String COD_ESTADO_BAJA = "02";
  public static final String COD_MOTIVO_BAJA_CAMBIODEST = "01";
  
  public static final String ULTIMOD_DIAS_CONSTANCIA = "15";
  
  public static final String URI_QR ="https://e-factura.sunat.gob.pe/v1/contribuyente/gre/comprobantes/descargaqr?hashqr=";
  
  private ConstantesUtils() {
    	super();
    }
  public static final String PROP_NAME_CODE_7020 = "7020";
  public static final String PROP_NAME_CODE_7022 = "7022";
  public static final String PROP_NAME_CODE_7021 = "7020";
  public static final String PROP_NAME_CODE_7023 = "7020";
  public static final String MS_PROCEDENCIA_VALIDAIFXCPE = "validaifxcpe";
}
