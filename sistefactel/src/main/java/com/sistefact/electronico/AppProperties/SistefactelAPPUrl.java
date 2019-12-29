package com.sistefact.electronico.AppProperties;

public class SistefactelAPPUrl {
    public static final String USUARIO_ROOT = "/user";
    public static final String USER_LOGIN = "/showLoginForm";
    public static final String USER_LOGIN_SESSION = "/login";
    public static final String REDIRECT_LOGIN = "redirect:showLoginForm";
    public static final String REDIRECT_HOME="redirect:home";
    public static final String HOME = "/home";
    public static final String LOGIN_FROM_EXTERNAL = "/loginFromExternal";
    //clientes
    public static final String CLIENTE_ROOT= "/cliente";
    public static final String CLIENTE_VIEW_ADD= "/addCliente";
    public static final String CLIENTE_SAVE= "/save/{id}";
    public static final String DELETE_CLIENTE= "/delete/{idCliente}";
    public static final String GET_CLIENTE= "/getById/{id}";
    public static final String LIST_ALL_CLIENTE="/listAll";
    public static final String PASSWORD_RECOVER_PAGE="recoverPasswordPage";
    //Producto
    public static final String PRODUCTO_ROOT= "/producto";
    public static final String PRODUCTOE_VIEW_ADD= "/addCliente";
    public static final String PRODUCTO_SAVE= "/save/{id}";
    public static final String DELETE_PRODUCTO= "/delete/{idProducto}";
    public static final String GET_PRODUCTO= "/getById/{id}";
    public static final String LIST_ALL_PRODUCTO="/listAll";
 
    //conductor
    public static final String CONDUCTOR_ROOT= "/conductor";
    public static final String CONDUCTOR_SAVE= "/save/{idConductor}";
    public static final String GET_CONDUCTOR= "/getById/{idConductor}";
    public static final String LIST_ALL_CONDUCTOR="/listAll";
    public static final String DELETE_CONDUCTOR= "/delete/{idConductor}";
    //vehiculos
    public static final String VEHICULO_ROOT= "/vehiculo";
    public static final String VEHICULO_SAVE= "/save/{idVehiculo}";
    public static final String GET_VEHICULO= "/getById/{idVehiculo}";
    public static final String LIST_ALL_VEHICULO="/listAll";
    public static final String DELETE_VEHICULO= "/delete/{idVehiculo}";
    //administrador
    public static final String ADMINISTRADOR_ROOT= "/administrador";
    public static final String LIST_ALL_ADMINISTRADOR="/listAll";
    public static final String GET_ADMINISTRADOR="/getById/{idAdministrador}";
    public static final String ADMINISTRADOR_SAVE= "/save";
    //Pedido
    public static final String PEDIDO_ROOT= "/pedido";
    
    //Invoice
    public static final String INVOICE_ROOT= "/invoice";
    public static final String INVOICE_SAVE= "/save/{idInvoice}";
    public static final String GET_INVOICE= "/getById/{idConductor}";
    public static final String LIST_ALL_INVOICE="/listAll";
    public static final String DELETE_INVOICE= "/delete/{idConductor}";
}
