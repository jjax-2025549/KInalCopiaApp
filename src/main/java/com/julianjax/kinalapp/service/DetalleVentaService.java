package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.DetalleVenta;
import com.julianjax.kinalapp.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalle) {
        // Aqui es donde se guarda el producto amarrado a la venta
        return detalleVentaRepository.save(detalle);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVenta actualizar(Long id, DetalleVenta detalle) {
        // Validamos que el detalle exista antes de actualizar
        if (!detalleVentaRepository.existsById(id)) {
            throw new RuntimeException("DetalleVenta no encontrado con el id: " + id);
        }
        // Forzamos el ID de la URL al objeto
        detalle.setCodigoDetalleVenta(id);
        return detalleVentaRepository.save(detalle);
    }

    @Override
    public void eliminar(Long id) {
        detalleVentaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return detalleVentaRepository.existsById(id);
    }
}