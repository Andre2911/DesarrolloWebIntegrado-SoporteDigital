package com.empresa.solicitudes.soporte123.respository;


import com.empresa.solicitudes.soporte123.dto.IdLabelDTO;
import com.empresa.solicitudes.soporte123.models.MaeTipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MaeTipoSolicitudRepository extends JpaRepository<MaeTipoSolicitud, Integer> {

    @Query("SELECT new com.empresa.solicitudes.soporte123.dto.IdLabelDTO(t.n_id_tipo_solicitud, t.s_nombre_tipo) " +
            "FROM MaeTipoSolicitud t ORDER BY t.s_nombre_tipo")
    List<IdLabelDTO> getIdLabelSolicitud();
}
