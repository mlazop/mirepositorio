package pe.gob.sunat.contribuyentems2.registro.cpe.consulta.nfs.backend.ws.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.sunat.contribuyentems2.registro.cpe.consulta.nfs.backend.domain.Archivo;
import pe.gob.sunat.contribuyentems2.registro.cpe.consulta.nfs.backend.domain.Respuesta;



@RestController
@RequestMapping(value = "/contribuyente")
public class NFSRestService {
	
    @PostMapping("/ose/lectura/nfs")
    public ResponseEntity<?> convierte(@RequestBody Map<String, Object> requestBody) {
    	String nomArchivo;
    	String rutaArchivo;
    	if (requestBody.containsKey("nomArchivo") && requestBody.containsKey("rutaArchivo")) {
    	    nomArchivo = (String) requestBody.get("nomArchivo");
    	    rutaArchivo = (String) requestBody.get("rutaArchivo");
    	} else {
    		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Se presentaron errores de validación que impidieron completar el Request, 0001- Parametros nomArchivo/rutaArchivo deben estar presentes");
    	}
    	if (nomArchivo == null || rutaArchivo == null || nomArchivo.isEmpty() || rutaArchivo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Se presentaron errores de validación que impidieron completar el Request, 0002- Parametros nomArchivo/rutaArchivo no pueden ser nullos o vacios");       
        }
                       
    	Path rutaCompleta = Paths.get(rutaArchivo, nomArchivo);
        File file = rutaCompleta.toFile();
        
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Se presentaron errores de validación que impidieron completar el Request, 0003- No se encontro el archivo en la ruta especificada");
        }
    	    	
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream())
           {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String base64Encoded = Base64.getEncoder().encodeToString(bytes);
             
            return ResponseEntity.ok(new Respuesta(base64Encoded, nomArchivo));
            
           } catch (IOException e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se presento una condicion inesperada que impidio completar el Request.");
           }
       } 
   
    
    
    @PostMapping("/ose/lectura/nfs/multiple")
    public ResponseEntity<?> procesarArchivos(@RequestBody Map<String, List<Map<String, String>>> requestBody) {
    	boolean unaRespuesta=false;
		List<Respuesta> respuestas = new ArrayList<>();
    	try {
            List<Map<String, String>> lstArchivos = requestBody.get("lstArchivos");
            if (lstArchivos == null || lstArchivos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Se presentaron errores de validación que impidieron completar el Request, 0001- Lista de archivos incorrecta.");
            }
            String nomArchivo;
            String rutaArchivo;

            for (Map<String, String> archivo : lstArchivos) {
            	if (archivo.containsKey("nomArchivo") && archivo.containsKey("rutaArchivo")) {
            	    nomArchivo = (String) archivo.get("nomArchivo");
            	    rutaArchivo = (String) archivo.get("rutaArchivo");
            	} else {
            		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Se presentaron errores de validación que impidieron completar el Request, 0002- Parametros nomArchivo/rutaArchivo deben estar presentes");
            	}
            	
                if ((nomArchivo == null || rutaArchivo == null || nomArchivo.isEmpty() || rutaArchivo.isEmpty())) {
                	return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Se presentaron errores de validación que impidieron completar el Request, 0003- Parametros nomArchivo/rutaArchivo no pueden ser nullos o vacios");    
                }
                else {
                	Path rutaCompleta = Paths.get(rutaArchivo, nomArchivo);
                	File file = rutaCompleta.toFile();
                	if (file.exists()) {
                		unaRespuesta=true;
                		FileInputStream fileInputStream = new FileInputStream(file);
                		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                		byte[] buffer = new byte[1024];
                		int length;
                		while ((length = fileInputStream.read(buffer)) != -1) {
                			byteArrayOutputStream.write(buffer, 0, length);
                		}
                		byte[] bytes = byteArrayOutputStream.toByteArray();
                		String base64Encoded = Base64.getEncoder().encodeToString(bytes);
                		respuestas.add(new Respuesta(base64Encoded, nomArchivo));
                		} 
                	}
            }
            if(!unaRespuesta) {
         	   return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Se presentaron errores de validación que impidieron completar el Request, 0004- No se encontro el archivo en la ruta especificada");  
            }
            return ResponseEntity.ok(respuestas);
          } catch (Exception e) {
        	  	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se presento una condicion inesperada que impidio completar el Request");
          }
    }

    
    
}




package pe.gob.sunat.contribuyentems2.registro.cpe.consulta.nfs.backend.domain;

public class Archivo {
	public String nomArchivo;
	public String rutaArchivo;
	
	public Archivo(String nomArchivo, String rutaArchivo) {
		this.nomArchivo=nomArchivo;
		this.rutaArchivo=rutaArchivo;
	}
	
	public String getnomArchivo() {
        return nomArchivo;
    }

    public void setnomArchivo(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    public String getrutaArchivo() {
        return rutaArchivo;
    }

    public void setrutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
}


package pe.gob.sunat.contribuyentems2.registro.cpe.consulta.nfs.backend.domain;

public class Respuesta {
	
	private String archivoB64;
	private String nomArchivo;
	
	public Respuesta(String archivoB64, String nomArchivo) {
		this.archivoB64 = archivoB64;
		this.nomArchivo = nomArchivo;
        
	}
	
	public String getNomArchivo() {
		return nomArchivo;
	}
	
	public String setNomArchivo(String nomArchivo) {
		return this.nomArchivo=nomArchivo;
	}

	public String getArchivoB64() {
		return archivoB64;
	}
	
	public String setArchivoB64(String archivoB64) {
		return this.archivoB64=archivoB64;
	}
	
	
}

