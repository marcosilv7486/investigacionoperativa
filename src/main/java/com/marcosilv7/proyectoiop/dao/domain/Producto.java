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
    @NotNull(message = "Debe seleccionar un proveedor")
    private Proveedor proveedor;

    @NotNull(message = "Debe ingresar el codigo del producto")
    @Column
    private String codigo;

    @NotNull(message = "Debe ingresar el nombre del producto")
    @Column
    @Size(max = 100)
    private String productos;

    @NotNull(message = "Debe ingresar la concentracion del producto")
    @Column
    @Size(max = 100)
    private String concentracion;

    @NotNull(message = "Debe ingresar la presentacion del producto")
    @Column
    @Size(max = 100)
    private String presentacion;

    @ManyToOne
    @NotNull(message = "Debe seleccionar una categoria de producto")
    private CategoriaProducto categoriaProducto;

    @NotNull(message = "Debe ingresar el costo de Compra")
    @Column
    @Min(0)
    private BigDecimal costoCompra;

    @NotNull(message = "Debe ingresar el costo de preparacion")
    @Column
    @Min(0)
    private BigDecimal costoPreparacion;

    @NotNull(message = "Debe ingresar el costo de Almacenaje")
    @Column
    @Min(0)
    private BigDecimal costoAlmacenaje;

    @NotNull(message = "Debe ingresar el costo por unidad perdida")
    @Column
    @Min(0)
    private BigDecimal costoUnidadPerdida;

    @NotNull(message = "Debe ingresar la cantidad Minima por pedido")
    @Column
    @Min(0)
    private Integer cantidadMinimaPorPedido;

    @NotNull(message = "Debe ingresar el inventario inicial")
    @Column
    @Min(0)
    private Integer inventarioInicial;

    @NotNull(message = "Debe ingresar el stock minimo por periodo")
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

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public CategoriaProducto getCategoriaProducto() {
        if(categoriaProducto==null)
            categoriaProducto=new CategoriaProducto();
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }
}
