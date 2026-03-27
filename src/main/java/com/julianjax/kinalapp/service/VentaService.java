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

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta guardar(Venta venta) {
        if (venta.getEstado() == null) venta.setEstado(1);
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return ventaRepository.existsById(id);
    }
}