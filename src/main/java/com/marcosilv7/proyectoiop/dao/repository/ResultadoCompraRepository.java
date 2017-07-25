package com.marcosilv7.proyectoiop.dao.repository;

import com.marcosilv7.proyectoiop.dao.domain.ResultadoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Repository
public interface ResultadoCompraRepository extends JpaRepository<ResultadoCompra,Integer> {

    @Query(value = "select producto,periodo,compra from ResultadoCompra   ",nativeQuery = true)
    List<Object[]> obtenerTodos();
}
