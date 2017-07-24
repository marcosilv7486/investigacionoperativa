package com.marcosilv7.proyectoiop.service.impl;

import com.marcosilv7.proyectoiop.dao.domain.*;
import com.marcosilv7.proyectoiop.dao.repository.*;
import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptimizacionServiceImpl implements OptimizacionService {

    private ProductoRepository productoRepository;
    private PeriodoRepository periodoRepository;
    private DemandaRepository demandaRepository;
    private ResultadoCompraRepository resultadoCompraRepository;
    private ResultadoInventarioRepository resultadoInventarioRepository;

    @Autowired
    public OptimizacionServiceImpl(ProductoRepository productoRepository, PeriodoRepository periodoRepository,
                                   DemandaRepository demandaRepository,
                                   ResultadoCompraRepository resultadoCompraRepository,
                                   ResultadoInventarioRepository resultadoInventarioRepository) {
        this.productoRepository = productoRepository;
        this.periodoRepository = periodoRepository;
        this.demandaRepository = demandaRepository;
        this.resultadoCompraRepository = resultadoCompraRepository;
        this.resultadoInventarioRepository = resultadoInventarioRepository;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void registrarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public Producto modificar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerProducto(int id) {
        return productoRepository.findOne(id);
    }

    @Override
    public List<Periodo> obtenerPeriodos() {
        return periodoRepository.findAll();
    }

    @Override
    public List<Demanda> obtenerDemanda() {
        return demandaRepository.findAll();
    }

    @Override
    public List<ResultadoCompra> obtenerResultadoCompra() {
        return null;
    }

    @Override
    public List<ResultadoInventario> obtenerResultadoInventario() {
        return null;
    }
}
