package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.RegisterRequest;
import com.empresa.solicitudes.soporte123.models.MaeEmpresas;
import com.empresa.solicitudes.soporte123.models.MaeRoles;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.respository.MaeEmpresasRepository;
import com.empresa.solicitudes.soporte123.respository.MaeRolesRepository;
import com.empresa.solicitudes.soporte123.respository.MovUsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final MaeRolesRepository maeRolesRepository;
    private final MovUsuarioRepository movUsuarioRepository;
    private final MaeEmpresasRepository maeEmpresasRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MovUsuario registrarUsuario(RegisterRequest request) throws Exception {
        MaeRoles rol = maeRolesRepository.findById(request.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        MaeEmpresas empresa = maeEmpresasRepository.findById(request.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrado"));

        MovUsuario usuario = new MovUsuario();
        usuario.setS_nombre(request.getNombre());
        usuario.setS_correo(request.getCorreo());
        usuario.setS_contrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setRol(rol);
        usuario.setEmpresa(empresa);

        return movUsuarioRepository.save(usuario);
    }

    @Override
    public List<MovUsuario> getUsuariosByEmpresa(Integer id) {
        return movUsuarioRepository.getUsuariosByEmpresa(id);
    }

    @Override
    public MovUsuario getUsuario(Integer id) {
        return movUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void updateUsuario(MovUsuario movUsuario) {
        MovUsuario existingUsuario = movUsuarioRepository.findById(movUsuario.getN_id_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existingUsuario.setS_nombre(movUsuario.getS_nombre());
        existingUsuario.setS_correo(movUsuario.getS_correo());
        existingUsuario.setRol(movUsuario.getRol());
        existingUsuario.setEmpresa(movUsuario.getEmpresa());
        movUsuarioRepository.save(existingUsuario);
    }

    @Override
    public void deleteUsuario(Integer id) throws UsernameNotFoundException {
        MovUsuario usuario = movUsuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        movUsuarioRepository.delete(usuario);
    }
}
