package com.empresa.solicitudes.soporte123.controller;


import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.AsignacionResponse;
import com.empresa.solicitudes.soporte123.dto.AsignacionResumenResponse;
import com.empresa.solicitudes.soporte123.dto.AsignarColaboradorRequest;
import com.empresa.solicitudes.soporte123.service.AsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@RequiredArgsConstructor
public class AsignacionController {

    private final AsignacionService asignacionService;

    @PostMapping
    public ResponseEntity<ApiResponse<AsignacionResponse>> asignarColaborador(
            @RequestBody AsignarColaboradorRequest request) {

        ApiResponse<AsignacionResponse> response = asignacionService.asignarColaborador(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/solicitud/{id}")
    public ResponseEntity<ApiResponse<List<AsignacionResumenResponse>>> listarPorSolicitud(
            @PathVariable Integer id,
            Principal principal) throws AccessDeniedException {

        ApiResponse<List<AsignacionResumenResponse>> response =
                asignacionService.listarAsignacionesDeSolicitud(id, principal.getName());
        return ResponseEntity.ok(response);
    }

}
