package com.empresa.solicitudes.soporte123.respository;


import com.empresa.solicitudes.soporte123.models.MaeTipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaeTipoSolicitudRepository extends JpaRepository<MaeTipoSolicitud, Integer> {
}
