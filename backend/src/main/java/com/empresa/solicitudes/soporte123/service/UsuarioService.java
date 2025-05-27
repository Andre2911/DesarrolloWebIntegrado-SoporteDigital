package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.RegisterRequest;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UsuarioService {
    MovUsuario registrarUsuario(RegisterRequest registerRequest) throws Exception;
    List<MovUsuario> getUsuariosByEmpresa(Integer id);
    MovUsuario getUsuario(Integer id);
    void updateUsuario(MovUsuario movUsuario);
    void deleteUsuario(Integer id) throws UsernameNotFoundException;
}
