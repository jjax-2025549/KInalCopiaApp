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

    //Nuevo metodo que lista solo los activos
    List<Usuario> listarActivos();

    //Metodo que guarda un Usuario en la BD
    Usuario guardar(Usuario usuario);

    //Optional - Contenedor que puede o no tener valor
    //evita el error de NullPointerException
    Optional<Usuario> buscarPorId(Long id);

    //Método que actualiza un Usuario
    Usuario actualizar(Long id, Usuario usuario);

    //Metodo de tipo void para eliminar a un Usuario
    void eliminar(Long id);

    //boolean - Retorna true si existe y false sino existe
    boolean existePorId(Long id);

}