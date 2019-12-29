package com.sistefact.electronico.models;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="facturas_items")
public class ItemFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    Producto producto;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

   public Double calcularImporte(){
        return cantidad.doubleValue()*producto.getPrecio();
   }

    private static final long serialVersion=1L;
}
