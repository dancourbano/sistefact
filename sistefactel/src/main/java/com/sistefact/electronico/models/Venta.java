package com.sistefact.electronico.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


@Entity
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVenta;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Column(name = "importe", nullable = false)
	private Double importe;
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(updatable = false)
	private LocalDateTime createAt;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime updateAt;
	@OneToMany(mappedBy = "venta", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<DetalleVenta> detalleVenta=new ArrayList<DetalleVenta>();
	public Long getIdVenta() {
		return idVenta;
	}

	@PrePersist
	public void prePersist() {
		createAt = LocalDateTime.now();

	}

	@PreUpdate
	public void preUpdate() {
		updateAt = LocalDateTime.now();

	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
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

	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Venta other = (Venta) obj;
			if (idVenta != other.idVenta)
				return false;
			return true;
		}
		@Override
	    public int hashCode() {
	        return 31;
	    }
}