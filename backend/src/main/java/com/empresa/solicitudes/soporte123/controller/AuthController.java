package com.empresa.solicitudes.soporte123.controller;


import com.empresa.solicitudes.soporte123.dto.AuthRequest;
import com.empresa.solicitudes.soporte123.dto.RegisterRequest;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.security.JwtUtil;
import com.empresa.solicitudes.soporte123.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generarToken(userDetails);

        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegisterRequest request) throws Exception {
        MovUsuario nuevoUsuario = usuarioService.registrarUsuario(request);
        return ResponseEntity.ok(nuevoUsuario);
    }

}
