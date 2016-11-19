package com.example.geraldf.constru_movil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

import basedatos.ModeloObjCat_Pro;
import basedatos.ModeloObjCategoria;
import basedatos.ModeloObjPro_Pro;
import basedatos.ModeloObjProducto;
import basedatos.ModeloObjRol_Usu;
import basedatos.ModeloObjSuc_Pro;
import basedatos.ModeloObjSucursal;
import basedatos.ModeloObjUsuario;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 05/11/2016.
 */
public class RegProductos extends AppCompatActivity {
    private ModeloObjUsuario _mModeloObjUsuario;
    private ModeloObjCategoria _modeloObjCategoria;
    private ModeloObjProducto _modeloObjProducto;
    private ModeloObjCat_Pro _ModeloObjCat_pro;
    private ModeloObjSucursal _ModeloObjSucursal;
    private ModeloObjSuc_Pro _ModeloObjSuc_pro;
    private ModeloObjUsuario _modeloObjUsuario;
    private ModeloObjRol_Usu _ModeloObjRol_usu;
    private ModeloObjPro_Pro _ModeloObjPro_pro;

    private LinkedList _eachProducto;
    private Boolean _bandera0 = false;
    private Boolean _bandera1 = false;
    private Boolean _bandera2 = false;
    private Boolean _bandera3 = false;

    private EditText _tvSucursal, _tvNombre, _tvPrecio, _tvCategoria, _tvProveedor, _tvCantidad,
            _tvExento, _tvDescripcion;

    private ContentValues _valuesUsuarios;
    private ContentValues _valuesProductos;
    private ContentValues _valuesCategoria_Producto;
    private ContentValues _valuesSucursal_Producto;
    private ContentValues _valuesUser;
    private ContentValues _valuesRol_Cliente;
    private ContentValues _valuesPro_Pro;

    private String[] _atributosCategorias = new String[]{_modeloObjCategoria.NOMBRE, _modeloObjCategoria.DESCRIPCION, _modeloObjProducto.ID};
    private String _nombreCategoria;
    private String _idCategoria;
    private String _idProducto;
    private String _nombreProducto;
    private String _nombreSucursal;
    private String _idSucursal;
    private String _nombreUsuario;
    private String _rolUsuario;

    private int _totalProductosRegistrados;

    /**
     *
     */
    public RegProductos() {
        this._bandera0 = false;
    }

    /**
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd0:
                this.consultCategorias();
                if (this._bandera0 == true) {
                    this.consultSucursales();
                    if(this._bandera1==true){
                        this.consultProveedores();
                        if(this._bandera2==true){
                            this.consultRolClientes();
                            if(this._bandera3==true){
                                this.llenadoDatosProducto();
                                this.llenadoSucursalProducto();
                                this.llenadoProveedorProducto();
                            }else{
                                Toast.makeText(getApplicationContext(), "Usuario no es un proveedor", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Usuario no existente", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Sucursal no existente", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Categoria no existente", Toast.LENGTH_SHORT).show();
                }
                this._bandera0 = false;
                this._bandera1 = false;
                this._bandera2 = false;
                this._bandera3 = false;
                break;
        }
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actproductos);

        this._tvSucursal = (EditText) findViewById(R.id.regProductoSucursal);
        this._tvCategoria = (EditText) findViewById(R.id.regProductoCategoria);
        this._tvPrecio = (EditText) findViewById(R.id.regProductoPrecio);
        this._tvNombre = (EditText) findViewById(R.id.regProductoNombre);
        this._tvProveedor = (EditText) findViewById(R.id.regProductoProvedor);
        this._tvCantidad = (EditText) findViewById(R.id.regProductoCantidad);
        this._tvExento = (EditText) findViewById(R.id.regProductoExento);
        this._tvDescripcion = (EditText) findViewById(R.id.regProductoDescripcion);

        this._eachProducto = new LinkedList();

        this._valuesProductos = new ContentValues();
        this._valuesCategoria_Producto = new ContentValues();
        this._valuesSucursal_Producto = new ContentValues();
        this._valuesUsuarios = new ContentValues();
        this._valuesPro_Pro = new ContentValues();
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * Metodo que se encarga de leer todos los datos que se encuentren almacenados dentro de la tabla
     * categorias.
     */
    public void consultCategorias() {
        MotorBaseDatos admi = new MotorBaseDatos(this);
        SQLiteDatabase bd = admi.getWritableDatabase();
        Cursor tupla = bd.query("CATEGORIA", // Nombre de la tabla
                this._atributosCategorias, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while (tupla.moveToNext()) {
            this._nombreCategoria = tupla.getString(tupla.getColumnIndex(this._modeloObjCategoria.NOMBRE));
            // Acciones...
            if (this._nombreCategoria.toString().equals(this._tvCategoria.getText().toString())) {
                this._bandera0 = true;
                this._idCategoria = tupla.getString(tupla.getColumnIndex(this._modeloObjCategoria.ID));
            }
        }
        bd.close();
    }

    /**
     *
     */
    public void llenadoDatosProducto() {
        MotorBaseDatos admi = new MotorBaseDatos(this);
        SQLiteDatabase bd = admi.getWritableDatabase();
        this._valuesProductos.put(this._modeloObjProducto.NOMBRE, this._tvNombre.getText().toString());
        this._valuesProductos.put(this._modeloObjProducto.DESCRIPCION, this._tvDescripcion.getText().toString());
        this._valuesProductos.put(this._modeloObjProducto.EXENTO, this._tvExento.getText().toString());
        this._valuesProductos.put(this._modeloObjProducto.CANTIDAD, this._tvCantidad.getText().toString());
        this._valuesProductos.put(this._modeloObjProducto.PRECIO, this._tvPrecio.getText().toString());
        bd.insert("PRODUCTO", null, this._valuesProductos);
        bd.close();

        this._nombreProducto = this._tvNombre.getText().toString();

        this.consultProductos();
        this.llenadoCategoriaProducto();

        Toast.makeText(getApplicationContext(), "llenado con exito PRODUCTO", Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo que se encarga de leer todos los datos que se encuentren almacenados dentro de la tabla
     * categorias.
     */
    public void consultProductos() {
        MotorBaseDatos admi = new MotorBaseDatos(this);
        SQLiteDatabase bd = admi.getWritableDatabase();
        Cursor tupla = bd.query("PRODUCTO", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while (tupla.moveToNext()) {
            String nombreProducto = tupla.getString(tupla.getColumnIndex(this._modeloObjProducto.NOMBRE));
            // Acciones...
            if (nombreProducto.toString().equals(this._nombreProducto)) {
                this._idProducto = tupla.getString(tupla.getColumnIndex(this._modeloObjProducto.ID));
            }
        }
        bd.close();
    }

    /**
     *
     */
    public void llenadoCategoriaProducto() {
        MotorBaseDatos admi = new MotorBaseDatos(this);
        SQLiteDatabase bd = admi.getWritableDatabase();
        this._valuesCategoria_Producto.put(this._ModeloObjCat_pro.ID_CATEGORIA, this._idCategoria);
        this._valuesCategoria_Producto.put(this._ModeloObjCat_pro.ID_PRODUCTO, this._idProducto);
        bd.insert("CATEGORIA_PRODUCTO", null, this._valuesCategoria_Producto);
        bd.close();
        this._idCategoria = "";
        this._tvDescripcion.setText("");
        Toast.makeText(getApplicationContext(), "llenado con exito Categoria_Producto", Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo que se encarga de leer todos slo datos necesarios de una sucursal, para luego determinar
     * si alguna de estas coincide con la que esta queriendo involucrar el usuario.
     */
    public void consultSucursales() {
        MotorBaseDatos admi = new MotorBaseDatos(this);
        SQLiteDatabase bd = admi.getWritableDatabase();
        Cursor tupla = bd.query("SUCURSAL", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while (tupla.moveToNext()) {
            this._nombreSucursal = tupla.getString(tupla.getColumnIndex(this._ModeloObjSucursal.NOMBRE));
            // Acciones...
            if (_nombreSucursal.toString().equals(this._tvSucursal.getText().toString())) {
                this._bandera1 = true;
                this._idSucursal = tupla.getString(tupla.getColumnIndex(this._ModeloObjSucursal.ID));
            }
        }
        bd.close();
    }
    /**
     *
     */
    public void llenadoSucursalProducto(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        this._valuesSucursal_Producto.put(this._ModeloObjSuc_pro.ID_SUCURSAL, this._idSucursal);
        this._valuesSucursal_Producto.put(this._ModeloObjSuc_pro.ID_PRODUCTO, this._idProducto);
        bd.insert("SUCURSAL_PRODUCTO",null,this._valuesSucursal_Producto);
        bd.close();
        this._idSucursal="";
        Toast.makeText(getApplicationContext(), "llenado con exito Sucursal_Producto", Toast.LENGTH_SHORT).show();
    }
    /**
     * Metodo para consultar la tabla de proveedores y obtener algunos datos importantes de ellos.
     */
    public void consultProveedores() {
        MotorBaseDatos admi = new MotorBaseDatos(this);
        SQLiteDatabase bd = admi.getWritableDatabase();
        Cursor tupla = bd.query("USUARIO", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while (tupla.moveToNext()) {
            // Acciones...
            this._nombreUsuario = tupla.getString(tupla.getColumnIndex(this._mModeloObjUsuario.USER)).toString();
            if (this._nombreUsuario.toString().equals(this._tvProveedor.getText().toString())) {
                this._bandera2 = true;
            }
        }
        bd.close();
    }
    public void consultRolClientes(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("ROL_USUARIO", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){
            if(this._nombreUsuario.equals(tupla.getString(tupla.getColumnIndex(_ModeloObjRol_usu.ID_USUARIO)))){
                if("3".equals(tupla.getString(tupla.getColumnIndex(_ModeloObjRol_usu.ID_ROL)))){
                    this._bandera3=true;
                }else{
                    this._bandera3=false;
                }
            }else{
                this._bandera3=false;
            }
        }
        bd.close();
    }
    /**
     *
     */
    public void llenadoProveedorProducto(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        this._valuesPro_Pro.put(this._ModeloObjPro_pro.ID_USUARIO, this._nombreUsuario);
        this._valuesPro_Pro.put(this._ModeloObjPro_pro.ID_PRODUCTO, this._idProducto);
        bd.insert("PROVEEDOR_PRODUCTO",null,this._valuesPro_Pro);
        bd.close();
        Toast.makeText(getApplicationContext(), "llenado con exito Proveedor_Producto", Toast.LENGTH_SHORT).show();
    }
}

