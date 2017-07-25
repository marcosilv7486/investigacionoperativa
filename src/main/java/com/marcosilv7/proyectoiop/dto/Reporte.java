package com.marcosilv7.proyectoiop.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reporte implements Serializable{

    private String codigoProducto;
    private String nombreProducto;
    private String concentracion;
    private String proveedor;
    private String categoria;
    private List<Integer> valores;

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setValores(List<Integer> valores) {
        this.valores = valores;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<Integer> getValores() {
        if(valores==null)
            valores=new ArrayList<>();
        return valores;
    }
}
