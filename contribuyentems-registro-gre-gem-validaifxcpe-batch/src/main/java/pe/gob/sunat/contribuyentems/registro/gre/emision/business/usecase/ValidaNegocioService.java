package pe.gob.sunat.contribuyentems.registro.gre.emision.business.usecase;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util.ValidationException;

public interface ValidaNegocioService {
	void validaNegocio(String Comprobante) throws Exception, ValidationException, ProcesoException, ConexionException;
}
