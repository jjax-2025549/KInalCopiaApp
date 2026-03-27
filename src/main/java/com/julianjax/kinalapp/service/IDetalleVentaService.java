package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.DetalleVenta;
import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    //Listar todos los detalles registrados en el sistema
    List<DetalleVenta> listarTodos();

    //Guardar un nuevo detalle 
    DetalleVenta guardar(DetalleVenta detalle);

    //Buscar un detalle especifico por su ID
    Optional<DetalleVenta> buscarPorId(Long id);

    //Eliminar un detalle de venta
    void eliminar(Long id);

    //Verificar si el detalle existe
    boolean existePorId(Long id);
}