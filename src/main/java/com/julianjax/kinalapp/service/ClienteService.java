package com.julianjax.kinalapp.service;

import com.julianjax.kinalapp.entity.Cliente;
import com.julianjax.kinalapp.repository.ClienteRepository;
/*
 * Usamos el Transactional de Spring en lugar de jakarta
 * porque este sí soporta el atributo readOnly = true
 */
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Anotación que registra un Bean de Spring
 * Indica que la clase contiene lógica del negocio
 */
@Service
/*
 * Por defecto todos los métodos de esta clase serán transaccionales
 * Una transacción es algo que puede ocurrir o no
 */
@Transactional
public class ClienteService implements IClienteService {

    /*
     * private: Solo es accesible dentro de la misma clase
     * final: No puede cambiar porque es constante
     * ClienteRepository: El repositorio para acceder a la BD
     * Inyección de Dependencia, ya que Spring nos da el repositorio
     */
    private final ClienteRepository clienteRepository;

    /*
     * Constructor: se ejecuta al crear un objeto
     * Spring pasa el repositorio automáticamente (Inyección de Dependencia)
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        // Asignar el repositorio a nuestra variable de clase
    }

    // Indica que se está implementando un método de la interfaz
    @Override
    // Optimizar la consulta, solo lectura, para que no bloquee la BD
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
        // findAll() es un método de Spring que hace el SELECT * FROM clientes
        // este método viene de JPARepository
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarActivos() {
        // Llama al repositorio que hace SELECT * FROM clientes WHERE estado = 1
        return clienteRepository.findByEstado(1);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        /*
         * Método de guardar, crea un Cliente
         * Acá es donde colocamos la lógica del negocio antes de guardar
         * Primero validamos el dato
         */
        validarCliente(cliente);
        if (cliente.getEstado() == 0)
            cliente.setEstado(1);
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDpi(String dpi) {
        // Buscar un cliente por DPI
        return clienteRepository.findById(dpi);
        // Optional nos evita el NullPointerException
    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        // Método para actualizar un cliente existente
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("El cliente no se encontró con el DPI " + dpi);
            // Si no existe se lanza una excepción (error controlado)
        }
        cliente.setDPICliente(dpi);
        // Aseguramos que el DPI del objeto coincida con el de la URL
        // Por seguridad usamos el DPI de la URL y no el que viene en el JSON

        return clienteRepository.save(cliente);
        /*
         * save() no solo sirve para guardar sino también para actualizar si el dato
         * existe (DPI), entonces hace UPDATE, pero si no existe hace un INSERT.
         * Antes verificamos si existe o no el registro
         */
    }

    @Override
    public void eliminar(String dpi) {
        // Eliminar un cliente
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("El cliente no se encontró con el DPI " + dpi);
        }
        clienteRepository.deleteById(dpi);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorDPI(String dpi) {
        // Verificar si existe un cliente
        return clienteRepository.existsById(dpi);
    }

    // Método privado (solo puede utilizarse dentro de la clase)
    private void validarCliente(Cliente cliente) {
        /*
         * Validaciones del negocio: este método es privado porque
         * es algo interno del servicio
         */
        if (cliente.getDPICliente() == null || cliente.getDPICliente().trim().isEmpty()) {
            // Si el DPI es null o está vacío después de quitar espacios
            // Lanza una excepción con un mensaje
            throw new IllegalArgumentException("El DPI es un dato obligatorio");
        }

        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }

        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es un dato obligatorio");
        }
    }
}