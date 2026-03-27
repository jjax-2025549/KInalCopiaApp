package com.julianjax.kinalapp.repository;

import com.julianjax.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Spring genera automáticamente el SELECT * FROM ventas WHERE estado = ?
    List<Venta> findByEstado(int estado);

}