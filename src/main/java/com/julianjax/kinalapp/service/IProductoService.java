package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    // Metodo para listar todos los productos del inventario
    List<Producto> listarTodos();

    // Metodo para guardar un nuevo producto
    Producto guardar(Producto producto);

    // Busqueda de producto por su ID unico
    Optional<Producto> buscarPorId(Long id);

    // Metodo para eliminar un producto logicamente o de la BD
    void eliminar(Long id);

    // Verifica si el producto existe por su ID
    boolean existePorId(Long id);

    // Metodo para actualizar los datos de un producto existente
    Producto actualizar(Long id, Producto producto);
}