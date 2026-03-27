package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Producto;
import com.julianjax.kinalapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService {

    //Inyectamos el repositorio de productos
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        //Retornamos todos los productos registrados
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        //Guardamos el producto en la base de datos
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        // Buscamos un producto especifico por su ID
        return productoRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        //Validamos si existe antes de intentar borrar
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el producto");
        }
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        //Verificamos existencia en el sistema
        return productoRepository.existsById(id);
    }
}