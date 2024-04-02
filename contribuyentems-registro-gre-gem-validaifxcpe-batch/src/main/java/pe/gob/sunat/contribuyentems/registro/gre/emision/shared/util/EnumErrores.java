package pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util;

import org.apache.commons.lang3.StringUtils;

public enum EnumErrores {

    ERROR_500("500", "Internal Server Error - Se present\u00f3 una condici\u00f3n inesperada que impidi\u00f3 completar el Request"),
    ERROR_422("422", "Unprocessable Entity - Se presentar\u00f3n errores de validaci\u00f3n que impidieron completar el Request"),

    ERROR_2108("2108","Presentaci\u00f3n fuera de fecha"),
    ERROR_2010("2010","El contribuyente no est\u00e1 activo"),
    ERROR_2011("2011","El contribuyente no est\u00e1 habido"),
    ERROR_3439("3439","El RUC no est\u00e1 afecto a los tributos autorizados"),
    ERROR_2536("2536","Serie y n\u00famero no se encuentra registrado como baja por cambio de destinatario"),
    ERROR_3341("3341","No existe el n\u00famero de Declaraci\u00f3n Aduanera de Mercanc\u00edas (DAM) o el Declaraci\u00f3n Simplificada (DS)"),
    ERROR_3394("3394","Si existe m\u00e1s de una DAM o DS, deben coincidir los tipos y n\u00fameros de documento de identidad del importador o exportador consignados en dichos documentos"),
    ERROR_4432("4432","La constancia de Dep\u00f3sito ingresada ya ha sido referenciada en otra GRE emitida por el mismo remitente"),
    ERROR_3342("3342","La constancia de Dep\u00f3sito ingresada ya ha sido referenciada en otra GRE emitida"),
    ERROR_4404("4404","El N\u00famero de constancia de Dep\u00f3sito no se encuentra o a\u00fan no est\u00e1 disponible en los sistemas de SUNAT"),
    ERROR_4382("4382","El n\u00famero de comprobante consignado como documento relacionado no existe"),
    ERROR_3207("3207","Comprobante f\u00edsico no se encuentra autorizado"),
    ERROR_3207_RELACIONADO("3207","Comprobante f\u00edsico relacionado %s-%s no se encuentra autorizado"),
    ERROR_3408("3408","El n\u00famero del RUC %s del emisor del documento relacionado no existe"),
    ERROR_4435("4435","El tipo y n\u00famero de documento de identidad del destinatario es diferente al del importador que figura en la(s) DAM/DS"),
    ERROR_3443("3443","El n\u00famero del RUC del Destinatario no existe"),
    ERROR_4373("4373","El RUC del Destinatario no est\u00e1 activo"),
    ERROR_4374("4374","El RUC del destinatario no est\u00e1 habido"),
    ERROR_3444("3444","El N\u00famero del DNI del destinatario no existe"),
    ERROR_3401("3401","El N\u00famero del DNI del Pagador del flete no existe"), //jmv
    ERROR_2621("2621","N\u00famero de RUC del Proveedor no existe"),
    ERROR_4051("4051","El RUC del Proveedor no est\u00e1 activo"),
    ERROR_4052("4052","El RUC del Proveedor no est\u00e1 habido"),
    ERROR_2450("2450","N\u00famero de DNI no existe"),
    ERROR_3336("3336","N\u00famero de RUC del comprador no existe"),
    ERROR_4379("4379","El RUC del comprador no est\u00e1 activo"),
    ERROR_4380("4380","El RUC del comprador no est\u00e1 habido"),
    ERROR_3338("3338","N\u00famero de DNI del Comprobador no existe"),
    ERROR_3348("3348","El N\u00famero de RUC del Transportista no existe"),
    ERROR_3349("3349","El RUC del Transportista no est\u00e1 activo"),
    ERROR_3350("3350","El RUC del Transportista no est\u00e1 habido"),
    ERROR_3351("3351","El RUC del Transportista no est\u00e1 afecto a los tributos autorizados"),
    ERROR_4393("4393","El N\u00famero de Registro de MTC del transportista no se encuentra en las bases consultadas"),
    ERROR_4398("4398","El N\u00famero de placa no se encuentra en las bases consultadas"),
    ERROR_4400("4400","El N\u00famero de Constancia de Inscripci\u00f3n Vehicular o Certificado de Habilitaci\u00f3n Vehicular o la TUC (f\u00edsica o electr\u00f3nica) %s no se encuentra en las bases consultadas"),
    ERROR_3359("3359","El N\u00famero de DNI del conductor no existe"),
    ERROR_4412("4412","El N\u00famero de licencia de conducir no se encuentra en las bases consultadas - N\u00famero de licencia %s"),
    ERROR_3424("3424","Si el Motivo de traslado es 08-Importaci\u00f3n, y el punto de partida no es un puerto ni aeropuerto, el N\u00famero de Ruc del establecimiento debe corresponder al n\u00famero de RUC del dep\u00f3sito temporal consignado en la DAM o DS"),
    ERROR_3412("3412","El Numero de RUC asociado al establecimiento del punto de partida/llegada no existe"),
    ERROR_3366("3366","El codigo de establecimiento anexo del punto de partida no esta declarado en SUNAT"),
    ERROR_3367("3367","El codigo de establecimiento anexo del punto de partida no coincide con el ubigeo de partida consignado"),
    ERROR_3415("3415","Si el Motivo de traslado es 09-Exportación, y el punto de llegada no es un puerto, el Número de Ruc del establecimiento debe corresponder al número de RUC del depósito temporal consignado en la DAM o DS"),
    ERROR_3370("3370","El codigo de establecimiento anexo del punto de llegada no esta declarado en SUNAT"),
    ERROR_3371("3371","El codigo de establecimiento anexo del punto de llegada no coincide con el ubigeo de llegada consignado"),
    ERROR_4436("4436","Ya se ha emitido una GRE por esta DAM o DS. La generación de la GRE será bajo su responsabilidad pues no se cuenta con saldo suficiente para este tipo de traslado - serie %s"),
    ERROR_3378("3378","La Partida arancelaria % no se encuentra en el listado"),
    ERROR_3432("3432","El Numero de la serie en la DAM o DS no coincide con algún número de serie de la DAM o DS consignada - serie %s"),
    ERROR_3385("3385","El N\u00famero de RUC del Remitente no existe"),
    ERROR_4420("4420","El N\u00famero de RUC del Remitente no est\u00e1 activo"),
    ERROR_4421("4421","El N\u00famero de RUC del Remitente no est\u00e1 habido"),
    ERROR_3386("3386","El N\u00famero de DNI del Remitente no existe"),
    ERROR_3389("3389","El N\u00famero de RUC del SubContratador no existe"),
    ERROR_3402("3402","El N\u00famero de RUC del Pagador del flete no existe"), //jmv
    ERROR_4190("4190","El valor ingresado como descripci\u00f3n de motivo de traslado no cumple con el estandar"),
    ERROR_3433("3433","La GRE remitente no existe"),
    ERROR_3434("3434","Si existe una GRE Remitente como documento relacionado, el tipo y numero de documento de identidad del Destinatario deben ser los mismos que del destinatario consignado en la GRE Remitente"),
    ERROR_0306("0306","No se puede leer (parsear) el archivo XML (Encoding del XML no cumple con lo establecido)"),
    ERROR_0200("0200","No se pudo procesar su solicitud. (Ocurrio un error en el batch)"),
    ERROR_4204("4204","Comprobante f\\u00edsico no se encuentra autorizado como comprobante de contingencia"),
    ;

    EnumErrores(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCodigo() {
        return code;
    }
    
    public String getMensaje() {
        return msg;
    }
    
    public static String getMensaje(String code) {
        String msg = StringUtils.EMPTY;

        for (EnumErrores enumHTTP : EnumErrores.values()) {
            if (enumHTTP.code == code) {
                msg = enumHTTP.msg;
                break;
            }
        }
        return msg;
    }
}
