package com.empresa.solicitudes.soporte123.controller;


import com.empresa.solicitudes.soporte123.dto.*;
import com.empresa.solicitudes.soporte123.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;


    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<SolicitudResponse>> crearSolicitud(@RequestBody CrearSolicitudRequest crearSolicitudRequest, Authentication auth) throws Exception {
        String userneme = auth.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitudService.crearSolicitud(crearSolicitudRequest, userneme));
    }

    @GetMapping("/mis-solicitudes")
    public ResponseEntity<ApiResponse<List<SolicitudResumeResponse>>> listarMisSolicitudes(Principal principal) throws Exception {
        ApiResponse<List<SolicitudResumeResponse>> response =
                solicitudService.obtenerSolicitudesDelUsuario(principal.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SolicitudDetalleResponse>> verDetalle(
            @PathVariable Integer id,
            Principal principal) throws Exception {

        ApiResponse<SolicitudDetalleResponse> response = solicitudService.obtenerDetalle(id, principal.getName());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<ApiResponse<Void>> cerrarSolicitud(
            @PathVariable Integer id,
            Principal principal) throws Exception {

        ApiResponse<Void> response = solicitudService.cerrarSolicitud(id, principal.getName());
        return ResponseEntity.ok(response);
    }
}
