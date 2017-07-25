package com.marcosilv7.proyectoiop.service.interfaces;

import com.marcosilv7.proyectoiop.dao.domain.*;
import com.marcosilv7.proyectoiop.dto.Reporte;

import java.util.List;

public interface OptimizacionService {

    List<Proveedor> obtenerProveedores();
    List<Producto> obtenerProductos();
    void registrarProducto(Producto producto);
    Producto modificar(Producto producto);
    Producto obtenerProducto(int id);
    List<Periodo> obtenerPeriodos();
    List<Demanda> obtenerDemanda();
    List<Reporte> obtenerResultadoCompra();
    List<Reporte> obtenerResultadoInventario();

    Periodo obtenerPeriodo(Integer id);

    void registrarPeriodo(Periodo periodo);

    void modificarPeriodo(Periodo periodo);

    List<CategoriaProducto> obtenerCategorias();

    Object obtenerProveedor(Integer id);

    void registrarProveedor(Proveedor proveedor);

    void modificarProveedor(Proveedor proveedor);

    void registrarCategoria(CategoriaProducto categoria);

    void modificarCategoria(CategoriaProducto categoria);

    CategoriaProducto obtenerCategoria(Integer id);

    void registrarDemanda(Demanda demanda);

    void modificarDemanda(Demanda demanda);

    Object obtenerDemanda(Integer id);

    void limpiarReporte();

    double generarReporte();
}
