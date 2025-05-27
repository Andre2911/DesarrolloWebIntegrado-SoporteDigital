package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.respository.MovUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetaIlService implements UserDetailsService {

    private final MovUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        MovUsuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(usuario.getS_correo(), usuario.getS_contrasena(),
                Collections.singleton(new SimpleGrantedAuthority(usuario.getRol().getS_nombre_rol())));
    }


}
