package com.empresa.solicitudes.soporte123.respository;

import com.empresa.solicitudes.soporte123.dto.IdLabelDTO;
import com.empresa.solicitudes.soporte123.models.MaeAplicativos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MaeAplicativosRepository extends JpaRepository<MaeAplicativos, Integer> {

    @Query("SELECT new com.empresa.solicitudes.soporte123.dto.IdLabelDTO(a.id, a.nombre) " +
            "FROM MaeAplicativos a ORDER BY a.nombre")
    List<IdLabelDTO> getIdLabelAplicativo();
}
