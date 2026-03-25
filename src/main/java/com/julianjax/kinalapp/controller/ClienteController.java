package com.julianjax.kinalapp.controller;

import com.julianjax.kinalapp.entity.Cliente;
import com.julianjax.kinalapp.repository.ClienteRepository;
import com.julianjax.kinalapp.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("/clientes")
// Todas las rutas deben empezar por /clientes
public class ClienteController {

    // Inyectamos el SERVICIO y NO el repositorio
    // El controlador solo debe tener conexión con el Servicio
    private final ClienteRepository repo;

    // Como buena práctica la Inyección de Dependencias debe hacerse por el constructor
    private final IClienteService clienteService;

    public ClienteController(ClienteRepository repo, IClienteService clienteService) {
        this.repo = repo;
        this.clienteService = clienteService;
    }

    // GET /clientes - Devuelve todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.listarTodos();
        // Delegamos al servicio
        return ResponseEntity.ok(clientes);
        // 200 OK con la lista de clientes
    }

    // GET /clientes/activos - Devuelve solo los clientes con estado = 1
    @GetMapping("/activos")
    public ResponseEntity<List<Cliente>> listarActivos() {
        // El servicio llama al repositorio que consulta directo en MySQL
        List<Cliente> activos = clienteService.listarActivos();
        return ResponseEntity.ok(activos);
        // 200 OK con la lista de clientes activos
    }

    // {dpi} es una variable de ruta (valor a buscar)
    @GetMapping("/{dpi}")
    public ResponseEntity<Cliente> buscarPorDPI(@PathVariable String dpi) {
        // @PathVariable extrae el valor de la URL
        return clienteService.buscarPorDpi(dpi)
                // Si Optional tiene valor, devuelve 200 OK con el cliente
                .map(ResponseEntity::ok)
                // Si Optional está vacío, devuelve 404 NOT FOUND
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Cliente cliente) {
        // @RequestBody: toma el JSON del cuerpo y lo convierte a un objeto de tipo Cliente
        try {
            Cliente nuevoCliente = clienteService.guardar(cliente);
            // Intentamos guardar el cliente, pero puede lanzar IllegalArgumentException
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
            // 201 CREATED (más específico que el 200 para la creación de un recurso)
        } catch (IllegalArgumentException e) {
            // Si hay error de validación
            return ResponseEntity.badRequest().body(e.getMessage());
            // 400 BAD REQUEST con el mensaje de error
        }
    }

    // DELETE - Elimina un cliente por DPI
    @DeleteMapping("/{dpi}")
    public ResponseEntity<Void> eliminar(@PathVariable String dpi) {
        try {
            if (!clienteService.existePorDPI(dpi)) {
                return ResponseEntity.notFound().build();
                // 404 si no existe
            }
            clienteService.eliminar(dpi);
            return ResponseEntity.noContent().build();
            // 204 NO CONTENT (se ejecutó correctamente y no devuelve cuerpo)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            // 404 NOT FOUND
        }
    }

    // PUT - Actualizar cliente a través del DPI
    @PutMapping("/{dpi}")
    public ResponseEntity<?> actualizar(@PathVariable String dpi, @RequestBody Cliente cliente) {
        try {
            if (!clienteService.existePorDPI(dpi)) {
                // Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                // 404 NOT FOUND
            }
            // Actualizamos el cliente, pero esto puede lanzar una excepción
            Cliente clienteActualizado = clienteService.actualizar(dpi, cliente);
            return ResponseEntity.ok(clienteActualizado);
            // 200 OK con el cliente ya actualizado
        } catch (IllegalArgumentException e) {
            // Error cuando los datos sean incorrectos
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Cualquier otro error como: cliente no encontrado, etc
            return ResponseEntity.notFound().build();
            // 404 NOT FOUND
        }
    }
}