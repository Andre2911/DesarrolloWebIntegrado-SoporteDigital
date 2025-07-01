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
@CrossOrigin(origins = "*", maxAge = 3600)
public class SolicitudController {

    private final SolicitudService solicitudService;


    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<SolicitudResponse>> crearSolicitud(@RequestBody CrearSolicitudRequest crearSolicitudRequest, Authentication auth) throws Exception {
        String userneme = auth.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitudService.crearSolicitud(crearSolicitudRequest, userneme));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SolicitudResponse>> actualizarSolicitud(
            @PathVariable Integer id,
            @RequestBody CrearSolicitudRequest request,
            Authentication auth
    ) throws Exception {
        String username = auth.getName();
        return ResponseEntity.ok(solicitudService.actualizarSolicitud(id, request, username));
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


    @GetMapping("/getTiposSolicitud")
    public ResponseEntity<ApiResponse<List<IdLabelDTO>>> getTiposSolicitud() throws Exception {
        ApiResponse<List<IdLabelDTO>> response = solicitudService.getTiposSolicitud();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAplicativos")
    public ResponseEntity<ApiResponse<List<IdLabelDTO>>> getAplicativos() throws Exception {
        ApiResponse<List<IdLabelDTO>> response = solicitudService.getAplicativos();
        return ResponseEntity.ok(response);
    }
}
