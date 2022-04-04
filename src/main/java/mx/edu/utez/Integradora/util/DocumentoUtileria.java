package mx.edu.utez.Integradora.util;

import mx.edu.utez.Integradora.model.Solicitante;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class DocumentoUtileria {
    public static String guardarDocumento(MultipartFile multipartFile, String ruta) {
        String nombreDocumento= multipartFile.getOriginalFilename();
        try {
            String rutaArchivo= ruta+"/"+ nombreDocumento;
            File documento= new File(rutaArchivo);
            multipartFile.transferTo(documento);
            return nombreDocumento;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
