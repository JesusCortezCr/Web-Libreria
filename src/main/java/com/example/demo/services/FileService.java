package com.example.demo.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String guardarArchivo(MultipartFile file) throws IOException;

    void eliminarArchivo(String rutaRelativa) throws IOException;

}
