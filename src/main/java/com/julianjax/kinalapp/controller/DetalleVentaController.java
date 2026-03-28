package com.julianjax.kinalapp.controller;

import com.julianjax.kinalapp.entity.DetalleVenta;
import com.julianjax.kinalapp.service.IDetalleVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    // GET /detalles - Devuelve todos los detalles
    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        // Consultamos todos los productos vendidos en el historial
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    // GET /detalles/{id} - Buscar detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> buscarPorId(@PathVariable Long id) {
        // Buscamos un detalle especifico por su ID
        return detalleVentaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crear un nuevo detalle
    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalle) {
        // Registramos un nuevo producto en una venta (el ticket)
        return new ResponseEntity<>(detalleVentaService.guardar(detalle), HttpStatus.CREATED);
    }

    // PUT - Actualizar detalle por ID
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizar(@PathVariable Long id, @RequestBody DetalleVenta detalle) {
        // Verificamos que el detalle exista antes de actualizar
        if (!detalleVentaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
            // 404 NOT FOUND
        }
        DetalleVenta detalleActualizado = detalleVentaService.actualizar(id, detalle);
        return ResponseEntity.ok(detalleActualizado);
        // 200 OK con el detalle actualizado
    }

    // DELETE - Eliminar detalle por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Verificamos existencia para responder con 404 o 204
        if (!detalleVentaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        detalleVentaService.eliminar(id);
        return ResponseEntity.noContent().build();
        // 204 NO CONTENT
    }
}