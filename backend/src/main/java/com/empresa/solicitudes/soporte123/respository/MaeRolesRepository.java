package com.empresa.solicitudes.soporte123.respository;

import com.empresa.solicitudes.soporte123.models.MaeRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaeRolesRepository extends JpaRepository<MaeRoles, Integer> {
}
