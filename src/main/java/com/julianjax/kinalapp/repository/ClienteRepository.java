package com.julianjax.kinalapp.repository;

import com.julianjax.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    // Spring genera automáticamente el SELECT * FROM clientes WHERE estado = ?
    List<Cliente> findByEstado(int estado);

}