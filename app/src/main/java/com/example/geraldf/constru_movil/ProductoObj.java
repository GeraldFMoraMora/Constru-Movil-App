package com.example.geraldf.constru_movil;

/**
 * Created by GeraldF on 15/11/2016.
 * Clase que funciona con el objetivo de utilizar el paradigma para manipular los datos correpondientes
 * a cada producto que sea seleccionado para agregar en el carrito de compras.
 */
public class ProductoObj {
    private String _id;
    private String _nombre;
    private String _cantidad;

    public ProductoObj(String pid,String pnombre,String pcantidad){
        this.set_id(pid);
        this.set_nombre(pnombre);
        this.set_cantidad(pcantidad);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_cantidad() {
        return _cantidad;
    }

    public void set_cantidad(String _cantidad) {
        this._cantidad = _cantidad;
    }
}
