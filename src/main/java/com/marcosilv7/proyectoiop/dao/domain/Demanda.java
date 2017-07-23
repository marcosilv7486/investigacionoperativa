package com.marcosilv7.proyectoiop.dao.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Table(name="Demanda")
@Entity
public class Demanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto productoObj;

    @Column
    private String producto;

    @ManyToOne
    @JoinColumn(name="periodo_id")
    private Periodo periodoObj;

    @Column
    private String periodos;

    @Column
    @NotNull
    @Min(0)
    private Integer demanda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProductoObj() {
        return productoObj;
    }

    public void setProductoObj(Producto productoObj) {
        this.productoObj = productoObj;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Periodo getPeriodoObj() {
        return periodoObj;
    }

    public void setPeriodoObj(Periodo periodoObj) {
        this.periodoObj = periodoObj;
    }

    public String getPeriodos() {
        return periodos;
    }

    public void setPeriodos(String periodos) {
        this.periodos = periodos;
    }

    public Integer getDemanda() {
        return demanda;
    }

    public void setDemanda(Integer demanda) {
        this.demanda = demanda;
    }
}
