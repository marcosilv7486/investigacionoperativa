package com.marcosilv7.proyectoiop.service.impl;

import com.marcosilv7.proyectoiop.DemoLingo;
import com.marcosilv7.proyectoiop.configuracion.OperacionNoPermitidaException;
import com.marcosilv7.proyectoiop.dao.domain.*;
import com.marcosilv7.proyectoiop.dao.repository.*;
import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptimizacionServiceImpl implements OptimizacionService {

    private ProductoRepository productoRepository;
    private PeriodoRepository periodoRepository;
    private DemandaRepository demandaRepository;
    private ResultadoCompraRepository resultadoCompraRepository;
    private ResultadoInventarioRepository resultadoInventarioRepository;
    private ProveedorRepository proveedorRepository;
    private CategoriaRepository categoriaRepository;
    private DemoLingo lingo;


    @Autowired
    public OptimizacionServiceImpl(ProductoRepository productoRepository, PeriodoRepository periodoRepository,
                                   DemandaRepository demandaRepository,
                                   ResultadoCompraRepository resultadoCompraRepository,
                                   ResultadoInventarioRepository resultadoInventarioRepository,
                                   ProveedorRepository proveedorRepository,
                                   CategoriaRepository categoriaRepository,
                                   DemoLingo demoLingo) {
        this.productoRepository = productoRepository;
        this.periodoRepository = periodoRepository;
        this.demandaRepository = demandaRepository;
        this.resultadoCompraRepository = resultadoCompraRepository;
        this.resultadoInventarioRepository = resultadoInventarioRepository;
        this.proveedorRepository=proveedorRepository;
        this.categoriaRepository=categoriaRepository;
        this.lingo=demoLingo;
    }

    @Override
    public List<Proveedor> obtenerProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public void registrarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    @Transactional
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

    @Override
    public Periodo obtenerPeriodo(Integer id) {
        return periodoRepository.findOne(id);
    }

    @Override
    @Transactional
    public void registrarPeriodo(Periodo periodo) {
        periodoRepository.save(periodo);
    }

    @Override
    @Transactional
    public void modificarPeriodo(Periodo periodo) {
        periodoRepository.save(periodo);
    }

    @Override
    public List<CategoriaProducto> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Object obtenerProveedor(Integer id) {
        return proveedorRepository.findOne(id);
    }

    @Override
    @Transactional
    public void registrarProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    public void modificarProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    @Transactional
    public void registrarCategoria(CategoriaProducto categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public void modificarCategoria(CategoriaProducto categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaProducto obtenerCategoria(Integer id) {
        return categoriaRepository.findOne(id);
    }

    @Override
    @Transactional
    public void registrarDemanda(Demanda demanda) {
        List<Demanda> demandasRegistradas=demandaRepository.findByProductoId(demanda.getProductoObj().getId());
        List<Periodo> periodos=periodoRepository.findAll();
        //Validacion Demanda Repetida
        if(demandasRegistradas.stream().filter(p->p.getProductoObj().getId().equals(demanda.getProductoObj().getId()) &&
            p.getPeriodoObj().getId().equals(demanda.getPeriodoObj().getId())).collect(Collectors.toList()).size()>0){
            throw new OperacionNoPermitidaException("Ya se encontro la demanda registrada del producto con el periodo");
        }
        //Validacion Maximo numero de demandas
        if(demandasRegistradas.size()+1>periodos.size()){
            throw new OperacionNoPermitidaException("No se permite agregar mas demandas de este producto porque ya esta completo");
        }
        Producto producto=productoRepository.findOne(demanda.getProductoObj().getId());
        producto.setProductos(producto.getProductos());
        Periodo periodo=periodoRepository.findOne(demanda.getPeriodoObj().getId());
        periodo.setMes(periodo.getMes());
        demandaRepository.save(demanda);
    }

    @Override
    @Transactional
    public void modificarDemanda(Demanda demanda) {
        //VALIDACIONES
        Demanda entity=demandaRepository.findOne(demanda.getId());
        if(entity.getProductoObj().getId().equals(demanda.getPeriodoObj().getId())){
            if(!demanda.getPeriodoObj().getId().equals(entity.getPeriodoObj().getId())){
                List<Demanda> demandasRegistradas=demandaRepository.findByProductoId(demanda.getProductoObj().getId());
                if(demandasRegistradas.stream().filter(p-> p.getPeriodoObj().getId()
                        .equals(demanda.getPeriodoObj().getId())).collect(Collectors.toList()).size()>0){
                    throw new OperacionNoPermitidaException("Ya se encontro la demanda registrada del producto con el periodo");
                }
            }
        }else {
            List<Demanda> demandasRegistradas=demandaRepository.findByProductoId(demanda.getProductoObj().getId());
            if(demandasRegistradas.stream().filter(p->p.getProductoObj().getId().equals(demanda.getProductoObj().getId()) &&
                    p.getPeriodoObj().getId().equals(demanda.getPeriodoObj().getId())).collect(Collectors.toList()).size()>0){
                throw new OperacionNoPermitidaException("Ya se encontro la demanda registrada del producto con el periodo");
            }
        }
        Producto producto=productoRepository.findOne(demanda.getProductoObj().getId());
        entity.setProductoObj(producto);
        entity.setProducto(producto.getProductos());
        Periodo periodo=periodoRepository.findOne(demanda.getPeriodoObj().getId());
        entity.setPeriodos(periodo.getMes());
        entity.setPeriodoObj(periodo);
        entity.setDemanda(demanda.getDemanda());
        demandaRepository.save(entity);
    }

    @Override
    public Object obtenerDemanda(Integer id) {
        return demandaRepository.findOne(id);
    }

    @Override
    @Transactional
    public void generarReporte() {
        resultadoInventarioRepository.deleteAll();
        resultadoCompraRepository.deleteAll();
        List<ResultadoCompra> compras=new ArrayList<>();
        List<ResultadoInventario> inventarios=new ArrayList<>();
        //Crear el detalle por cada demanda
        for(Producto producto : productoRepository.findAll()){
            for(Periodo periodo : periodoRepository.findAll()){
                ResultadoCompra compra=new ResultadoCompra();
                compra.setCompra(0);
                compra.setPeriodo(periodo.getMes());
                compra.setProducto(producto.getProductos());
                compras.add(compra);

                ResultadoInventario inventario=new ResultadoInventario();
                inventario.setInventario(0);
                inventario.setPeriodo(periodo.getMes());
                inventario.setProducto(producto.getProductos());
                inventarios.add(inventario);
            }
        }
        resultadoInventarioRepository.save(inventarios);
        resultadoCompraRepository.save(compras);
        lingo.procesar();
        System.out.println("Procesando....");
    }
}
