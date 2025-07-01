package com.empresa.solicitudes.soporte123.respository;


import com.empresa.solicitudes.soporte123.models.MovSolicitud;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovSolicitudRepository extends JpaRepository<MovSolicitud, Integer> {

    List<MovSolicitud> findByUsuario(MovUsuario usuario);

    // En MovSolicitudRepository
    long countByEstadoIgnoreCase(String estado);


}
