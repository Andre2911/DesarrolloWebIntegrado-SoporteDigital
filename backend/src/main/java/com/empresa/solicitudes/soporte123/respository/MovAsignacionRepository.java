package com.empresa.solicitudes.soporte123.respository;


import com.empresa.solicitudes.soporte123.dto.AsignacionesTableDTO;
import com.empresa.solicitudes.soporte123.models.MovAsignacion;
import com.empresa.solicitudes.soporte123.models.MovSolicitud;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovAsignacionRepository extends JpaRepository<MovAsignacion, Integer> {


    @Query("""
            SELECT a
            FROM MovSolicitud a
 """)
    List<MovSolicitud> findByColaboradorCorreo();




    List<MovAsignacion> findBySolicitud(MovSolicitud solicitud);
    boolean existsBySolicitudAndColaborador(MovSolicitud solicitud, MovUsuario colaborador);


}
