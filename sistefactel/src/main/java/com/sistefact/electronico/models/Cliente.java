package com.sistefact.electronico.models;



import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
   
    private String nombre;
    @NotBlank(message = " Apellido no puede ser vacio")
    private String apellido;
    @NotBlank(message = "Correo no puede ser vacio")
    private String email;
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createAt;
   
    @Column(name="update_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateAt;
    private String telefono;
    @NotBlank(message = " DNI/RUC no puede ser vacio")
    private String identificador;

    private int status;
    private String company;
    private String direccion;
    private String ciudad;
    @Column(name="isruc")
    private int isRuc;
    @PrePersist
    public void prePersist(){
        createAt=new Date();
    }
    
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long id) {
        this.idCliente = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIsRuc() {
        return isRuc;
    }

    public void setIsRuc(int isRuc) {
        this.isRuc = isRuc;
    }

   


}
