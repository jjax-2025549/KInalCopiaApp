package com.julianjax.kinalapp.controller;

import com.julianjax.kinalapp.entity.Usuario;
import com.julianjax.kinalapp.repository.UsuarioRepository;
import com.julianjax.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("/usuarios")
// Todas las rutas deben empezar por /usuarios
public class UsuarioController {

    // Inyectamos el SERVICIO y NO el repositorio
    // El controlador solo debe tener conexión con el Servicio
    private final UsuarioRepository repo;

    // Como buena práctica la Inyección de Dependencias debe hacerse por el constructor
    private final IUsuarioService usuarioService;

    public UsuarioController(UsuarioRepository repo, IUsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

    // GET /usuarios - Devuelve todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        // Delegamos al servicio
        return ResponseEntity.ok(usuarios);
        // 200 OK con la lista de usuarios
    }

    // GET /usuarios/activos - Devuelve solo los usuarios con estado = 1
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> listarActivos() {
        // El servicio llama al repositorio que consulta directo en MySQL
        List<Usuario> activos = usuarioService.listarActivos();
        return ResponseEntity.ok(activos);
        // 200 OK con la lista de usuarios activos
    }

    // {id} es una variable de ruta (valor a buscar)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        // @PathVariable extrae el valor de la URL
        return usuarioService.buscarPorId(id)
                // Si Optional tiene valor, devuelve 200 OK con el usuario
                .map(ResponseEntity::ok)
                // Si Optional está vacío, devuelve 404 NOT FOUND
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        // @RequestBody: toma el JSON del cuerpo y lo convierte a un objeto de tipo Usuario
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            // Intentamos guardar el usuario, pero puede lanzar IllegalArgumentException
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
            // 201 CREATED
        } catch (IllegalArgumentException e) {
            // Si hay error de validación
            return ResponseEntity.badRequest().body(e.getMessage());
            // 400 BAD REQUEST con el mensaje de error
        }
    }

    // DELETE - Elimina un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            if (!usuarioService.existePorId(id)) {
                return ResponseEntity.notFound().build();
                // 404 si no existe
            }
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
            // 204 NO CONTENT
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            // 404 NOT FOUND
        }
    }

    // PUT - Actualizar usuario a través del ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            if (!usuarioService.existePorId(id)) {
                // Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                // 404 NOT FOUND
            }
            // Actualizamos el usuario
            Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
            // 200 OK con el usuario ya actualizado
        } catch (IllegalArgumentException e) {
            // Error cuando los datos sean incorrectos
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Cualquier otro error
            return ResponseEntity.notFound().build();
            // 404 NOT FOUND
        }
    }
}