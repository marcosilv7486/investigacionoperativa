package com.marcosilv7.proyectoiop.dao.repository;

import com.marcosilv7.proyectoiop.dao.domain.ResultadoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Repository
public interface ResultadoInventarioRepository extends JpaRepository<ResultadoInventario,Integer> {
    @Query(value = "select producto,periodo,inventario from ResultadoInventario   ",nativeQuery = true)
    List<Object[]> obtenerTodos();
}
