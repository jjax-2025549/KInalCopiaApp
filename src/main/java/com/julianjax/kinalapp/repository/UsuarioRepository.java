package com.julianjax.kinalapp.repository;

import com.julianjax.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Spring genera automáticamente el SELECT * FROM usuarios WHERE estado = ?
    List<Usuario> findByEstado(int estado);

}