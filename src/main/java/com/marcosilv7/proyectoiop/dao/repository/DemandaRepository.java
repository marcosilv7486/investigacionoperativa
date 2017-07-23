package com.marcosilv7.proyectoiop.dao.repository;

import com.marcosilv7.proyectoiop.dao.domain.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Repository
public interface DemandaRepository extends JpaRepository<Demanda,Integer> {
}
