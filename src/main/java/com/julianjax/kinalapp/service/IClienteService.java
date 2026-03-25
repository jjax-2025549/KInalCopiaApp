package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    /*
    * Interfaz: Es un contrato que dice QUE métodos debe tener
    * cualquier servicio de Clientes, No tiene
    * Implementación, solo la definición de los métodos
     */


    //Metodo que devuelve una lista de todos los Clientes
    List<Cliente> listarTodos();
    /*
    * List<Cliente> lo que hace es devolver una lista
    * de objetos de la entidad Clientes
     */

    //Nuevo metodo que lista solo los activos
    List<Cliente> listarActivos();

    //Metodo que guarda un Cliente en la BD
    Cliente guardar(Cliente cliente);
    //Parámetros: Recibe un objeto Cliente con los datos a
    //guardar

    //Optional - Contenedor que puede o no tener valor
    //evita el error de NullPointerException
    Optional<Cliente> buscarPorDpi(String dpi);

    //Método que actualiza un Cliente
    Cliente actualizar(String dpi, Cliente cliente);
    /*
    * Parametros - dpi: DPI del cliente a actualizar
    * Cliente cliente: Objeto con los datos nuevos
    * Retorna un objeto de tipo Cliente ya actualizado
     */

    /*
    * Metodo de tipo void para eliminar a un Cliente
    * void: mp retorna nigún valor a dato
    * Elimina un Cliente por su DPI
     */
    void eliminar(String dpi);

    //boolean - Retorna true si existe y false sino existe
    boolean existePorDPI (String dpi);

}
