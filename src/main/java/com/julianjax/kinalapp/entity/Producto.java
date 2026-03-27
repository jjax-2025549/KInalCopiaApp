package com.julianjax.kinalapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Long codigoProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "stock")
    private Integer stock;

    public Producto() {
    }

    public Producto(Long codigoProducto, String descripcion, BigDecimal precioUnitario, Integer stock) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(Long codigoProducto) { this.codigoProducto = codigoProducto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}