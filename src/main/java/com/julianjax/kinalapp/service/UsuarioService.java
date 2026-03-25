package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Usuario;
import com.julianjax.kinalapp.repository.UsuarioRepository;
/*
 * Usamos el Transactional de Spring en lugar de jakarta
 * porque este sí soporta el atributo readOnly = true
 */
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Anotación que registra un Bean de Spring
 * Indica que la clase contiene lógica del negocio
 */
@Service
/*
 * Por defecto todos los métodos de esta clase serán transaccionales
 * Una transacción es algo que puede ocurrir o no
 */
@Transactional
public class UsuarioService implements IUsuarioService {

    /*
     * private: Solo es accesible dentro de la misma clase
     * final: No puede cambiar porque es constante
     * UsuarioRepository: El repositorio para acceder a la BD
     * Inyección de Dependencia, ya que Spring nos da el repositorio
     */
    private final UsuarioRepository usuarioRepository;

    /*
     * Constructor: se ejecuta al crear un objeto
     * Spring pasa el repositorio automáticamente (Inyección de Dependencia)
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        // Asignar el repositorio a nuestra variable de clase
    }

    // Indica que se está implementando un método de la interfaz
    @Override
    // Optimizar la consulta, solo lectura, para que no bloquee la BD
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
        // findAll() es un método de Spring que hace el SELECT * FROM usuarios
        // este método viene de JPARepository
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarActivos() {
        // Llama al repositorio que hace SELECT * FROM usuarios WHERE estado = 1
        return usuarioRepository.findByEstado(1);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        /*
         * Método de guardar, crea un Usuario
         * Acá es donde colocamos la lógica del negocio antes de guardar
         * Primero validamos el dato
         */
        validarUsuario(usuario);
        if (usuario.getEstado() == 0)
            usuario.setEstado(1);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(int id) {
        // Buscar un usuario por ID
        return usuarioRepository.findById(id);
        // Optional nos evita el NullPointerException
    }

    @Override
    public Usuario actualizar(int id, Usuario usuario) {
        // Método para actualizar un usuario existente
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("El usuario no se encontró con el ID " + id);
            // Si no existe se lanza una excepción (error controlado)
        }
        usuario.setIdUsuario(id);
        // Aseguramos que el ID del objeto coincida con el de la URL
        // Por seguridad usamos el ID de la URL y no el que viene en el JSON

        return usuarioRepository.save(usuario);
        /*
         * save() no solo sirve para guardar sino también para actualizar si el dato
         * existe (ID), entonces hace UPDATE, pero si no existe hace un INSERT.
         * Antes verificamos si existe o no el registro
         */
    }

    @Override
    public void eliminar(int id) {
        // Eliminar un usuario
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("El usuario no se encontró con el ID " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(int id) {
        // Verificar si existe un usuario
        return usuarioRepository.existsById(id);
    }

    // Método privado (solo puede utilizarse dentro de la clase)
    private void validarUsuario(Usuario usuario) {
        /*
         * Validaciones del negocio: este método es privado porque
         * es algo interno del servicio
         */
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            // Si el nombre es null o está vacío después de quitar espacios
            // Lanza una excepción con un mensaje
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }

        if (usuario.getApellidoUsuario() == null || usuario.getApellidoUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es un dato obligatorio");
        }

        if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es un dato obligatorio");
        }

        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es un dato obligatorio");
        }
    }
}