package com.marcosilv7.proyectoiop.dao.repository;

import com.marcosilv7.proyectoiop.dao.domain.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Repository
public interface PeriodoRepository extends JpaRepository<Periodo,Integer>{
}
