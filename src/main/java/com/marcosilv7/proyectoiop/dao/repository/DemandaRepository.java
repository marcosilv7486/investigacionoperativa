package com.marcosilv7.proyectoiop.dao.repository;

import com.marcosilv7.proyectoiop.dao.domain.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Repository
public interface DemandaRepository extends JpaRepository<Demanda,Integer> {

    @Query("Select d from Demanda  d where d.productoObj.id =:id")
    List<Demanda> findByProductoId(@Param("id") Integer id);
}
