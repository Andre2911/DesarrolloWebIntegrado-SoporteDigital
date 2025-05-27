package com.empresa.solicitudes.soporte123.controller;


import com.empresa.solicitudes.soporte123.dto.ResponseUsuariosPorEmpresasDto;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;

    /**
     * Datos del usuario autenticado
     * @param id
     * @return
     */
    @GetMapping("/perfil/{id}")
    public ResponseEntity<MovUsuario> getUsuario(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                usuarioService.getUsuario(id)
        );
    }


    /**
        Listar usuarios por empresa
     */
    @GetMapping("/empresa/{id}")
    public ResponseEntity<List<MovUsuario>> getUsuariosByEmpresa(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                usuarioService.getUsuariosByEmpresa(id)
        );

    }

    @PutMapping()
    public ResponseEntity<Void> updateUsuario(@RequestBody MovUsuario movUsuario) {
        usuarioService.updateUsuario(movUsuario);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
