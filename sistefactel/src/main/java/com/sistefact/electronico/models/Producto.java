package com.sistefact.electronico.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "producto")
public class Producto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String descripcion;
    private String codigo;
    private String estado;
    private Long stock;
    private Double precio;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(updatable= false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createAt;
    
    
    @JsonSerialize(using = ToStringSerializer.class )
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        createAt=LocalDateTime.now();        
    }
    
    @PreUpdate
    public void preUpdate(){
        updateAt=LocalDateTime.now();
        
    }
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long id) {
        this.idProducto = id;
    }

     

    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

   

	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public static final long serialVersionUID=1L;
}
