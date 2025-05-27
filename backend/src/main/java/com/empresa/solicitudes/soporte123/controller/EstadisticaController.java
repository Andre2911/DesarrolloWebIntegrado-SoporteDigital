package com.empresa.solicitudes.soporte123.controller;

import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.EstadisticaResumenResponse;
import com.empresa.solicitudes.soporte123.service.EstadisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadisticas")
@RequiredArgsConstructor
public class EstadisticaController {

    private final EstadisticaService estadisticaService;

    @GetMapping("/resumen")
    public ResponseEntity<ApiResponse<EstadisticaResumenResponse>> resumen() {
        ApiResponse<EstadisticaResumenResponse> response = estadisticaService.obtenerResumen();
        return ResponseEntity.ok(response);
    }
}
