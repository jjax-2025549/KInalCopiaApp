package com.julianjax.kinalapp.controller;

import com.julianjax.kinalapp.entity.Producto;
import com.julianjax.kinalapp.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    // GET: Lista todos los productos disponibles en la base de datos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // GET: Busca un producto específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crea un nuevo producto y devuelve el estado 201 (Created)
    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.guardar(producto), HttpStatus.CREATED);
    }

    // PUT: Actualiza un producto existente. Recibe el ID en la URL y los datos en el Body.
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        // El servicio se encarga de buscar el ID y sobreescribir los datos
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    // DELETE: Elimina un producto por ID si no tiene dependencias en otras tablas
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Validamos si el producto existe antes de intentar borrarlo
        if (!productoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}