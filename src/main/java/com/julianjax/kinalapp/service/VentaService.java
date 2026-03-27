package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Venta;
import com.julianjax.kinalapp.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentaService {

    // Inyección del repositorio para persistencia en MySQL
    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarTodos() {
        // Retorna la lista completa de ventas desde la BD
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarActivos() {
        // Filtra ventas por estado activo (1)
        return ventaRepository.findByEstado(1);
    }

    @Override
    public Venta guardar(Venta venta) {
        // Si el estado viene nulo, lo seteamos en 1 por defecto
        if (venta.getEstado() == null) {
            venta.setEstado(1);
        }
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorId(Long id) {
        // Busca una venta específica evitando nulos con Optional
        return ventaRepository.findById(id);
    }

    @Override
    public Venta actualizar(Long id, Venta venta) {
        // Validamos que el ID exista antes de intentar el update
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con el id: " + id);
        }
        // Forzamos el ID de la URL al objeto para evitar inconsistencias
        venta.setCodigoVenta(id.intValue());
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long id) {
        // Verificamos existencia previo a la eliminación
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("No se encontro el registro a eliminar");
        }
        ventaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        // Retorna true si el ID ya está registrado en la base de datos
        return ventaRepository.existsById(id);
    }
}