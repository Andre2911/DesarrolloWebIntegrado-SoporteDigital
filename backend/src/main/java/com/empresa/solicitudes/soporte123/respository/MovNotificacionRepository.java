package com.empresa.solicitudes.soporte123.respository;


import com.empresa.solicitudes.soporte123.models.MovNotificacion;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovNotificacionRepository extends JpaRepository<MovNotificacion, Integer> {

    List<MovNotificacion> findByUsuario(MovUsuario usuario);
}
