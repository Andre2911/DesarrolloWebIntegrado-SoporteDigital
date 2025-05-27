package com.empresa.solicitudes.soporte123.controller;


import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.NotificacionResponse;
import com.empresa.solicitudes.soporte123.service.NotificacionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificacionResponse>>> listarMisNotificaciones(Principal principal) {
        ApiResponse<List<NotificacionResponse>> response =
                notificacionService.obtenerMisNotificaciones(principal.getName());
        return ResponseEntity.ok(response);
    }
}
