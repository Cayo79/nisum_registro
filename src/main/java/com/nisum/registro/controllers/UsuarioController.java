package com.nisum.registro.controllers;

import com.nisum.registro.bsd.BSDUsuarioInterface;
import com.nisum.registro.dto.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UsuarioController {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    BSDUsuarioInterface bsdUsuario;

    @GetMapping(value = "api/prueba")
    public ResponseEntity<?> pruebaUser() {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "No existen Usuarios Cargados");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "usuario")
    public ResponseEntity<?> listUser() {
        Map<String, Object> response = new HashMap<>();
        List<Usuario> listUsuario = bsdUsuario.findAll();

        if (listUsuario.size() == 0) {
            response.put("mensaje", "No existen Usuarios Cargados");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }
        response.put("usuarios", listUsuario);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("usuario")
    public ResponseEntity<?> create(@RequestBody Usuario user) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (user.getEmail() == null) {
                response.put("mensaje", "No posee direccion Email.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            } else {
                try {
                    response.put("usuario", bsdUsuario.save(user));
                    response.put("mensaje", "El usuario ha sido creado con éxito!");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
                } catch (IllegalAccessException e) {
                    response.put("error", e.getMessage());
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
                }
            }
        } catch (Exception ex) {
            LOG.error(" - Error Exception:" + ex.getMessage() + " *****************************");
            response.put("mensaje", "Valor Campo Invalido..!");
            response.put("error", "Exception Invalid Cast Field");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
