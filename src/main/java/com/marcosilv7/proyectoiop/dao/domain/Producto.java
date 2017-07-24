package com.marcosilv7.proyectoiop.dao.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by marcosilveriocastro on 23/07/17.
 */
@Table(name="PRODUCTOS")
@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @ManyToOne
    private Proveedor proveedor;

    @NotNull
    @Column
    private String codigo;

    @NotNull
    @Column
    @Size(max = 100)
    private String productos;

    @NotNull
    @Column
    @Min(0)
    private BigDecimal costoCompra;

    @NotNull
    @Column
    @Min(0)
    private BigDecimal costoPreparacion;

    @NotNull
    @Column
    @Min(0)
    private BigDecimal costoAlmacenaje;

    @NotNull
    @Column
    @Min(0)
    private BigDecimal costoUnidadPerdida;

    @NotNull
    @Column
    @Min(0)
    private Integer cantidadMinimaPorPedido;

    @NotNull
    @Column
    @Min(0)
    private Integer inventarioInicial;

    @NotNull
    @Column
    @Min(0)
    private Integer stockMinimoPorPeriodo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public BigDecimal getCostoPreparacion() {
        return costoPreparacion;
    }

    public void setCostoPreparacion(BigDecimal costoPreparacion) {
        this.costoPreparacion = costoPreparacion;
    }

    public BigDecimal getCostoAlmacenaje() {
        return costoAlmacenaje;
    }

    public void setCostoAlmacenaje(BigDecimal costoAlmacenaje) {
        this.costoAlmacenaje = costoAlmacenaje;
    }

    public BigDecimal getCostoUnidadPerdida() {
        return costoUnidadPerdida;
    }

    public void setCostoUnidadPerdida(BigDecimal costoUnidadPerdida) {
        this.costoUnidadPerdida = costoUnidadPerdida;
    }

    public Integer getCantidadMinimaPorPedido() {
        return cantidadMinimaPorPedido;
    }

    public void setCantidadMinimaPorPedido(Integer cantidadMinimaPorPedido) {
        this.cantidadMinimaPorPedido = cantidadMinimaPorPedido;
    }

    public Integer getInventarioInicial() {
        return inventarioInicial;
    }

    public void setInventarioInicial(Integer inventarioInicial) {
        this.inventarioInicial = inventarioInicial;
    }

    public BigDecimal getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(BigDecimal costoCompra) {
        this.costoCompra = costoCompra;
    }

    public Integer getStockMinimoPorPeriodo() {
        return stockMinimoPorPeriodo;
    }

    public void setStockMinimoPorPeriodo(Integer stockMinimoPorPeriodo) {
        this.stockMinimoPorPeriodo = stockMinimoPorPeriodo;
    }

    public Proveedor getProveedor() {
        if(proveedor==null)
            proveedor=new Proveedor();
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
