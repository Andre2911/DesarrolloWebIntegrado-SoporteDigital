package com.empresa.solicitudes.soporte123.respository;


import com.empresa.solicitudes.soporte123.models.MovActividad;
import com.empresa.solicitudes.soporte123.models.MovAsignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovActividadesRepository extends JpaRepository<MovActividad, Integer> {

    @Query("SELECT AVG(a.n_horas_trabajadas) FROM MovActividad a")
    Double promedioHorasPorSolicitud();

    List<MovActividad> findByAsignacion(MovAsignacion asignacion);
}
