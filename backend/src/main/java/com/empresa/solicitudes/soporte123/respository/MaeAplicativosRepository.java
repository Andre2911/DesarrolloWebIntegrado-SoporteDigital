package com.empresa.solicitudes.soporte123.respository;

import com.empresa.solicitudes.soporte123.models.MaeAplicativos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MaeAplicativosRepository extends JpaRepository<MaeAplicativos, Integer> {
}
