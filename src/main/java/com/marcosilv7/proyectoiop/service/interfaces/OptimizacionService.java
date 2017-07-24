package com.marcosilv7.proyectoiop.service.interfaces;

import com.marcosilv7.proyectoiop.dao.domain.*;

import java.util.List;

public interface OptimizacionService {

    List<Proveedor> obtenerProveedores();
    List<Producto> obtenerProductos();
    void registrarProducto(Producto producto);
    Producto modificar(Producto producto);
    Producto obtenerProducto(int id);
    List<Periodo> obtenerPeriodos();
    List<Demanda> obtenerDemanda();
    List<ResultadoCompra> obtenerResultadoCompra();
    List<ResultadoInventario> obtenerResultadoInventario();

}
