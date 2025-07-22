package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";


    @Override
    public String guardarArchivo(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        // Generamos un nombre único para evitar sobrescribir archivos
        String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path rutaArchivo = Paths.get(UPLOAD_DIR + nombreArchivo);

        // Creamos el directorio si no existe
        Files.createDirectories(rutaArchivo.getParent());

        // Escribimos el archivo en disco
        Files.write(rutaArchivo, file.getBytes());

        // Retornamos la ruta relativa para mostrarla luego (por ejemplo, en una imagen o enlace)
        return "/uploads/" + nombreArchivo;
    }

    @Override
    public void eliminarArchivo(String rutaRelativa) throws IOException {
        if (rutaRelativa == null || rutaRelativa.isEmpty()) 
        return;

    String rutaFisica = "src/main/resources/static" + rutaRelativa;
    Path path = Paths.get(rutaFisica);

    Files.deleteIfExists(path);
    }

    
}
