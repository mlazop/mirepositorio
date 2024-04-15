package pe.gob.sunat.contribuyentems2.servicio.cpe.consulta.nfs.backend.ws.rest;

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

import pe.gob.sunat.contribuyentems2.servicio.cpe.consulta.nfs.backend.domain.Archivo;
import pe.gob.sunat.contribuyentems2.servicio.cpe.consulta.nfs.backend.domain.Respuesta;



@RestController
@RequestMapping(value = "/contribuyente")
public class NFSRestService {
	@GetMapping(value = "/message")
	public ResponseEntity showMessage() {
		return ResponseEntity.ok().body("HELLO DEMO DESPLIEGUE...");
	}

	@GetMapping(value = "/message/{mimensaje}")
	public ResponseEntity showMessage(@PathVariable("mimensaje") String mimensaje ) {
		return ResponseEntity.ok().body("Hola Mundo, este es mi mensaje : " + mimensaje );
	}
	
	
    
    @PostMapping("/ose/lectura/nfs")
    public ResponseEntity<?> convierte(@RequestBody Map<String, Object> requestBody) {
    	String nomArchivo;
    	String rutaArchivo;
    	if (requestBody.containsKey("nomArchivo") && requestBody.containsKey("rutaArchivo")) {
    	    nomArchivo = (String) requestBody.get("nomArchivo");
    	    rutaArchivo = (String) requestBody.get("rutaArchivo");
    	} else {
    		return ResponseEntity.badRequest().body("falta parametro nomArchivo/rutaArchivo");
    	}
    	if (nomArchivo == null || rutaArchivo == null || nomArchivo.isEmpty() || rutaArchivo.isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre del archivo o la ruta del archivo no pueden ser nulos o vacios");       
        }
                       
    	Path fileAbsolutePath = Paths.get(rutaArchivo, nomArchivo);
        File file = fileAbsolutePath.toFile();
        
        if (!file.exists()) {
            return ResponseEntity.badRequest().body(nomArchivo + " no se encuentra en la ruta especificada");
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
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar el archivo");
           }
       } 
    
    
    
    @PostMapping("/ose/lectura/nfs/multiple")
    
    public ResponseEntity<?> procesarArchivos(@RequestBody Map<String, List<Map<String, String>>> requestBody) {
    	List<Respuesta> respuestas = new ArrayList<>();
    	try {
            List<Map<String, String>> lstArchivos = requestBody.get("lstArchivos");
            if (lstArchivos == null || lstArchivos.isEmpty()) {
                return ResponseEntity.badRequest().body("La lista de archivos es incorrecta");
            }
            
            String nomArchivo;
            String rutaArchivo;
                        
            for (Map<String, String> archivo : lstArchivos) {
            	String mensajeError="";
            	if (archivo.containsKey("nomArchivo") && archivo.containsKey("rutaArchivo")) {
            	    nomArchivo = (String) archivo.get("nomArchivo");
            	    rutaArchivo = (String) archivo.get("rutaArchivo");
            	} else {
            	    return ResponseEntity.badRequest().body("falta parametro nomArchivo/rutaArchivo");
            	}
            		            	            	                
                if (nomArchivo == null || rutaArchivo == null || nomArchivo.isEmpty() || rutaArchivo.isEmpty()) {
                    return ResponseEntity.badRequest().body("El nombre del archivo o la ruta del archivo no pueden ser nulos o vacíos");
                }
              
                Path fileAbsolutePath = Paths.get(rutaArchivo, nomArchivo);
                File file = fileAbsolutePath.toFile();
                
                if (!file.exists()) {
                    return ResponseEntity.badRequest().body(nomArchivo + " no se encuentra en la ruta especificada");
                }
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

            return ResponseEntity.ok(respuestas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la solicitud");
        }
    }

}
