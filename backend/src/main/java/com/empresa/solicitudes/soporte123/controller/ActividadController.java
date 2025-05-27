package com.empresa.solicitudes.soporte123.controller;


import com.empresa.solicitudes.soporte123.dto.ActividadResponse;
import com.empresa.solicitudes.soporte123.dto.ActividadResumenResponse;
import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.RegistrarActividadRequest;
import com.empresa.solicitudes.soporte123.service.ActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/actividades")
@RequiredArgsConstructor
public class ActividadController {

    private final ActividadService actividadService;

    @PostMapping
    public ResponseEntity<ApiResponse<ActividadResponse>> registrarActividad(
            @RequestBody RegistrarActividadRequest request,
            Principal principal) throws AccessDeniedException {

        ApiResponse<ActividadResponse> response = actividadService.registrarActividad(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/asignacion/{id}")
    public ResponseEntity<ApiResponse<List<ActividadResumenResponse>>> listarActividades(
            @PathVariable Integer id,
            Principal principal) throws AccessDeniedException {

        ApiResponse<List<ActividadResumenResponse>> response =
                actividadService.listarPorAsignacion(id, principal.getName());
        return ResponseEntity.ok(response);
    }
}
