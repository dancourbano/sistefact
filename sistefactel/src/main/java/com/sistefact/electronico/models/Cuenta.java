package com.sistefact.electronico.models;



import javax.persistence.*;

/**
 * Created by DANIEL on 29/11/2016.
 */
@Entity
@Table(name = "cuenta")
public class Cuenta {
    public static final String ATTR_OBJECT_NAME = "cuenta";
    public static final String ATTR_ENTITY_ID = "idCuenta";
    public static final String ATTR_ENTITY_USER = "usuario";
    public static final String ATTR_ENTITY_PASSWORD = "password";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_cuenta;
    @Column(nullable = false)
    private String usuario;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String tipo;






    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        tipo = tipo;
    }
    public int getIdCuenta() {
        return id_cuenta;
    }
    public void setIdCuenta(int idCuenta) {
        this.id_cuenta = idCuenta;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta=" + id_cuenta +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
