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

    // Inyectamos el repositorio de productos para interactuar con la BD
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        // Retornamos todos los productos registrados en el inventario
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        // Guardamos un nuevo producto en la base de datos
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        // Buscamos un producto específico por su ID único
        return productoRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        // Validamos si el producto existe antes de intentar borrarlo
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el producto con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        // Verificamos si el producto existe en el sistema
        return productoRepository.existsById(id);
    }

    // --- MÉTODO PARA ACTUALIZAR (EL QUE TE FALTABA) ---
    @Override
    public Producto actualizar(Long id, Producto producto) {
        // Buscamos el registro actual para modificar sus campos
        return productoRepository.findById(id).map(p -> {
            p.setDescripcion(producto.getDescripcion());
            p.setPrecioUnitario(producto.getPrecioUnitario());
            p.setStock(producto.getStock());
            // Guardamos los cambios aplicados
            return productoRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));
    }
}