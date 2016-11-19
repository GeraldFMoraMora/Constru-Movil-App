package com.example.geraldf.constru_movil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        this._tvNombre=(TextView) findViewById(R.id.viewNombre);
        this._tvPrecio=(TextView) findViewById(R.id.viewPrecio);
        this._tvCategoria=(TextView) findViewById(R.id.ViewCategoria);
        this._tvCantidad=(TextView) findViewById(R.id.ViewCantidad);
        this._etAdquirir=(EditText) findViewById(R.id.regCantidad);

        this._entradaDatos=getIntent();
        this._productoSeleccionado=_entradaDatos.getStringExtra("_productoConsultar");
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
                System.out.println("kjnhdsakjchbjsadbhfjkhsdfjkhsgdjhkfgdsaiuyf");
                this._bandera0=true;
                this._productoNombre= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.NOMBRE));
                System.out.println(this._productoNombre);
                this._productoPrecio= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.PRECIO));
                System.out.println(this._productoPrecio);
                this._productoCantidad= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.CANTIDAD));
                System.out.println(this._productoCantidad);
                this._productoId= tupla.getString(tupla.getColumnIndex(_modeloObjProducto.ID));
            }else{
                System.out.println("No aun");
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

}
