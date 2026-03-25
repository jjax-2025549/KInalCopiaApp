package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    /*
     * Interfaz: Es un contrato que dice QUE métodos debe tener
     * cualquier servicio de Usuarios, No tiene
     * Implementación, solo la definición de los métodos
     */

    //Metodo que devuelve una lista de todos los Usuarios
    List<Usuario> listarTodos();
    /*
     * List<Usuario> lo que hace es devolver una lista
     * de objetos de la entidad Usuarios
     */

    //Nuevo metodo que lista solo los activos
    List<Usuario> listarActivos();

    //Metodo que guarda un Usuario en la BD
    Usuario guardar(Usuario usuario);
    //Parámetros: Recibe un objeto Usuario con los datos a
    //guardar

    //Optional - Contenedor que puede o no tener valor
    //evita el error de NullPointerException
    Optional<Usuario> buscarPorId(int id);

    //Método que actualiza un Usuario
    Usuario actualizar(int id, Usuario usuario);
    /*
     * Parametros - id: ID del usuario a actualizar
     * Usuario usuario: Objeto con los datos nuevos
     * Retorna un objeto de tipo Usuario ya actualizado
     */

    /*
     * Metodo de tipo void para eliminar a un Usuario
     * void: no retorna ningún valor o dato
     * Elimina un Usuario por su ID
     */
    void eliminar(int id);

    //boolean - Retorna true si existe y false sino existe
    boolean existePorId(int id);

}