package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Contacto;
import com.example.demo.repositories.ContactoRepository;

@Service
public class ContactoServiceImpl implements ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Override
    public Contacto guardarContacto(Contacto contacto) {
        Contacto contacto2 = contactoRepository.save(contacto);
        return contacto2;
    }

}
