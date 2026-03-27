package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Venta;
import java.util.List;
import java.util.Optional;

public interface IVentaService {

    /*
     * Interfaz: Es un contrato que dice QUE métodos debe tener
     * cualquier servicio de Ventas, No tiene
     * Implementación, solo la definición de los métodos
     */

    //Metodo que devuelve una lista de todas las Ventas
    List<Venta> listarTodos();

    //Nuevo metodo que lista solo las activas
    List<Venta> listarActivos();

    //Metodo que guarda una Venta en la BD
    Venta guardar(Venta venta);

    //Optional - Contenedor que puede o no tener valor
    //evita el error de NullPointerException
    Optional<Venta> buscarPorId(Long id);

    //Método que actualiza una Venta
    Venta actualizar(Long id, Venta venta);

    //Metodo de tipo void para eliminar una Venta
    void eliminar(Long id);

    //boolean - Retorna true si existe y false sino existe
    boolean existePorId(Long id);

}