package com.empresa.solicitudes.soporte123.respository;


import com.empresa.solicitudes.soporte123.models.MovUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovUsuarioRepository extends JpaRepository<MovUsuario, Integer> {


    @Query("SELECT u from MovUsuario u where u.s_correo=:correo")
    Optional<MovUsuario> findByCorreo(@Param("correo") String correo);


    @Query("SELECT u from MovUsuario u where u.empresa.id=:id")
    List<MovUsuario> getUsuariosByEmpresa(@Param("id") Integer id);
}
