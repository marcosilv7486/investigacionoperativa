package com.marcosilv7.proyectoiop.dao.repository;

import com.marcosilv7.proyectoiop.dao.domain.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marcosilveriocastro on 24/07/17.
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {
}
