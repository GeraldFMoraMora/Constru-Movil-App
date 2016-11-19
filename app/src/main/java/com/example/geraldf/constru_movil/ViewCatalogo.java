package com.example.geraldf.constru_movil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import basedatos.BaseDatosSecundaria;
import basedatos.ModeloObjPedido;
import basedatos.ModeloObjProducto;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 06/11/2016.
 */
public class ViewCatalogo extends AppCompatActivity{
    ModeloObjProducto _modeloObjProducto;
    ListView prueba;
    ArrayList<String> listValues;
    private String _productoConsultar;
    private Intent _screen;
    private Intent _entrada;
    private ArrayList<ProductoObj> _valuesParaPedido = new ArrayList<ProductoObj>();

    private String username;
    private String usernameVendedor;

    private Button _btnTerminar;

    private int contDatos=0;

    private SimpleDateFormat _fecha = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat _hora = new SimpleDateFormat("hh:mm:ss");
    private String _formatFecha;
    private String _formatHora;
    private int _totalPedido=0;

    private ModeloObjPedido _ModeloObjPedido;

    private ContentValues _valuesPedido;

    /**
     *
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnTerminar:
                    if (this.contDatos!=0){
                        this.contDatos=0;
                        _btnTerminar.setVisibility(View.INVISIBLE);
                        this.CreacionPedido();
                        _screen=new Intent(this,RegPedidos.class);
                        _screen.putExtra("username",username);
                        _screen.putExtra("usernameVendedor",usernameVendedor);
                        startActivity(_screen);
                    }
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
        setContentView(R.layout.actviewcatalogo);


        this._formatFecha= this._fecha.format(new Date());
        this._formatHora = this._hora.format(new Date());

        this._valuesPedido=new ContentValues();

        this._entrada=getIntent();

        this._btnTerminar=(Button) findViewById(R.id.btnTerminar);
        this.username=_entrada.getStringExtra("username");
        this.usernameVendedor=_entrada.getStringExtra("usernameVendedor");

        this.VereficardatosParaPedido();

        if (this.contDatos!=0){
            _btnTerminar.setVisibility(View.VISIBLE);
        }

        _modeloObjProducto=new ModeloObjProducto();

        this.prueba=(ListView)findViewById(R.id.turras);
        listValues = new ArrayList<String>();

        this.consultLawyers();


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
    public void consultLawyers(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("PRODUCTO", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){
            String name = tupla.getString(tupla.getColumnIndex(_modeloObjProducto.NOMBRE));
            // Acciones...
            this.listValues.add(name);
        }
        bd.close();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValues);
        this.prueba.setAdapter(arrayAdapter);
        prueba.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                VereficardatosParaPedido();
                _totalPedido=0;
                _productoConsultar=listValues.get(position).toString();
                ejecutarViewProducto();
            }
        });
    }

    /**
     * Metodo que se encarga de ejecutar la ventana correspondiente al producto seleccionado,
     * ademas de pasar los valores ventana por ventana para poder desde cualquier lado
     * interpretar los datos.
     */
    public void ejecutarViewProducto(){
        _screen=new Intent(this,ViewProductos.class);
        _screen.putExtra("_productoConsultar",this._productoConsultar);
        _screen.putExtra("username",username);
        _screen.putExtra("usernameVendedor",usernameVendedor);
        startActivity(_screen);
    }

    /**
     * Metodo que funciona para realizar un acontabilidad de los datos almacenados en la tabla
     * correspondiente.
     */
    public void VereficardatosParaPedido(){
        BaseDatosSecundaria admi= new BaseDatosSecundaria(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("DATOS", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){
            this.contDatos+=1;
            this._totalPedido+=Integer.parseInt(tupla.getString(tupla.getColumnIndex("Precio")).toString())*Integer.parseInt(tupla.getString(tupla.getColumnIndex("Cantidad")).toString());

            String name = tupla.getString(tupla.getColumnIndex("Producto"));
            String id = tupla.getString(tupla.getColumnIndex("Cantidad"));
        }
        bd.close();
    }
    /**
     *
     */
    public void CreacionPedido(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        this._valuesPedido.put(this._ModeloObjPedido.FECHA, this._formatFecha);
        this._valuesPedido.put(this._ModeloObjPedido.HORA, this._formatHora);
        this._valuesPedido.put(this._ModeloObjPedido.TOTAL, String.valueOf(this._totalPedido));
        this._valuesPedido.put(this._ModeloObjPedido.ESTADO, "1");
        bd.insert("PEDIDO",null,this._valuesPedido);
        bd.close();
        Toast.makeText(getApplicationContext(), "llenado con exito PEDIDO", Toast.LENGTH_SHORT).show();
    }

}
