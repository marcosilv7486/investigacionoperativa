package com.marcosilv7.proyectoiop.dao.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Table(name="ResultadoCompra")
@Entity
public class ResultadoCompra implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String producto;
    @Column
    private String periodo;
    @Column
    private Integer compra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getCompra() {
        return compra;
    }

    public void setCompra(Integer compra) {
        this.compra = compra;
    }
}
