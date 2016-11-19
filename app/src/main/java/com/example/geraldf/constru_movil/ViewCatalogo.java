package com.example.geraldf.constru_movil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import basedatos.ModeloObjProducto;
import basedatos.ModeloObjRol;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 06/11/2016.
 */
public class ViewCatalogo extends AppCompatActivity{
    private Intent _screen;
    ModeloObjProducto _modeloObjProducto;
    ListView prueba;
    ArrayList<String> listValues;
    private String _productoConsultar;
    /**
     *
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()){
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
            System.out.println("PRODUCTO:"+name);
            this.listValues.add(name);
        }
        bd.close();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValues);
        this.prueba.setAdapter(arrayAdapter);
        prueba.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(getApplicationContext(), listValues.get(position), Toast.LENGTH_SHORT).show();
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
        startActivity(_screen);
    }

}
