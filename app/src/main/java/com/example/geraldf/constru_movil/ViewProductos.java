package com.example.geraldf.constru_movil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import basedatos.BaseDatosSecundaria;
import basedatos.ModeloObjCat_Pro;
import basedatos.ModeloObjCategoria;
import basedatos.ModeloObjProducto;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 06/11/2016.
 */
public class ViewProductos extends AppCompatActivity{
    private ImageView _imagen;
    private Intent _entradaDatos;
    private String _productoSeleccionado;
    private ModeloObjProducto _modeloObjProducto;
    private ModeloObjCat_Pro _ModeloObjCat_pro;
    private ModeloObjCategoria _ModeloObjCategoria;
    private String[] _atributosProductos = new String[]{_modeloObjProducto.NOMBRE,_modeloObjProducto.PRECIO,_modeloObjProducto.CANTIDAD,_modeloObjProducto.ID};
    private String _productoNombre, _productoPrecio, _productoCantidad, _productoId;
    private Boolean _bandera0=false;
    private TextView _tvNombre,_tvPrecio,_tvCategoria,_tvCantidad;
    private EditText _etAdquirir;
    private String _idCategoria;
    private String _nombreCategoria;
    private ContentValues _valuesPedido;
    private String username,usernameVendedor;
    private Button _btnCarrito;
    /**
     *
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnCarrito:
                this.almacenoDatos();
                finish();
                break;
        }
    }
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actviewproductos);
        this._imagen=(ImageView)findViewById(R.id.imageViewProducto);
        this._imagen.setImageResource(R.drawable.imagen_no_disponible);

        this._valuesPedido= new ContentValues();

        this._tvNombre=(TextView) findViewById(R.id.viewNombre);
        this._tvPrecio=(TextView) findViewById(R.id.viewPrecio);
        this._tvCategoria=(TextView) findViewById(R.id.ViewCategoria);
        this._tvCantidad=(TextView) findViewById(R.id.ViewCantidad);

        this._etAdquirir=(EditText) findViewById(R.id.regCantidad);

        this._btnCarrito=(Button) findViewById(R.id.btnCarrito);

        this._entradaDatos=getIntent();
        this._productoSeleccionado=_entradaDatos.getStringExtra("_productoConsultar");
        this.username=_entradaDatos.getStringExtra("username");
        this.usernameVendedor=_entradaDatos.getStringExtra("usernameVendedor");

        Toast.makeText(getApplicationContext(), _productoSeleccionado, Toast.LENGTH_SHORT).show();

        this.consultEachProducto();

        if (_bandera0==true){
            this._tvNombre.setText(this._productoNombre);
            this._tvPrecio.setText(this._productoPrecio);
            this._tvCategoria.setText("Desconocido");
            this._tvCantidad.setText(this._productoCantidad);
            this.consultRelacion_Categoria_Producto();
            this.consultCategoria();
        }else{
            Toast.makeText(getApplicationContext(), "Error al cargar los datos del producto", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     */
    @Override
    public void onBackPressed(){
        finish();
    }

    /**
     *
     */
    public void consultEachProducto(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("PRODUCTO", // Nombre de la tabla
                this._atributosProductos, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){
            this._productoNombre = tupla.getString(tupla.getColumnIndex(_modeloObjProducto.NOMBRE));

            if(this._productoNombre.toString().equals(this._productoSeleccionado)){
                this._bandera0=true;
                this._productoNombre= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.NOMBRE));
                this._productoPrecio= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.PRECIO));
                this._productoCantidad= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.CANTIDAD));
                this._productoId= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.ID));
            }else{
            }
        }
        bd.close();
    }
    /**
     *
     */
    public void consultRelacion_Categoria_Producto(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("CATEGORIA_PRODUCTO", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){

            if(this._productoId.toString().equals(tupla.getString(tupla.getColumnIndex(_ModeloObjCat_pro.ID_PRODUCTO)))){
                this._idCategoria = tupla.getString(tupla.getColumnIndex(_ModeloObjCat_pro.ID_CATEGORIA));
            }else{
                System.out.println("No hay ninguna categoria relacionada con algun producto");
            }

        }
        bd.close();
    }
    /**
     *
     */
    public void consultCategoria(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("CATEGORIA", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){

            if(this._idCategoria.toString().equals(tupla.getString(tupla.getColumnIndex(_ModeloObjCategoria.ID)))){
                this._nombreCategoria = tupla.getString(tupla.getColumnIndex(_ModeloObjCategoria.NOMBRE));
                this._tvCategoria.setText(this._nombreCategoria);
            }else{
                System.out.println("No hay ninguna categoria relacionada con algun producto");
            }
        }
        bd.close();
    }
    /**
     * Metodo que se encargara de guardar los datos del producto a comprar en la base de datos
     * secundanria
     */
    public void almacenoDatos(){
        BaseDatosSecundaria administrador= new BaseDatosSecundaria(this);
         SQLiteDatabase bdsecunadaria= administrador.getWritableDatabase();
         _valuesPedido.put("Producto", this._productoNombre);
         _valuesPedido.put("Precio",this._productoPrecio);
         _valuesPedido.put("Cantidad",this._etAdquirir.getText().toString());
         _valuesPedido.put("Vendedor",this.username);
        _valuesPedido.put("Id_Producto",this._productoId);
         bdsecunadaria.insert("DATOS",null,this._valuesPedido);
         bdsecunadaria.close();
    }

}
