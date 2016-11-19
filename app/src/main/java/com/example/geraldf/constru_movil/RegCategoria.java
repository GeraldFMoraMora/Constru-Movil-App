package com.example.geraldf.constru_movil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import basedatos.ModeloObjCategoria;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 05/11/2016.
 */
public class RegCategoria extends AppCompatActivity {
    private EditText _tvNombre, _tvDescripcion;

    private ContentValues _valuesCategoria;

    public ModeloObjCategoria _modeloObjCategoria= new ModeloObjCategoria();
    /**
     *
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                this.llenadoDatos();
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actcategoria);
        _tvNombre=(EditText) findViewById(R.id.regCategoriaNombre);
        _tvDescripcion=(EditText) findViewById(R.id.regCategoriaDescripcion);
        _valuesCategoria=new ContentValues();
    }
    @Override
    public void onBackPressed(){
        finish();
    }

    /**
     *
     */
    public void llenadoDatos(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        //this._valuesCategoria.put(this._modeloObjCategoria.ID, "0");
        this._valuesCategoria.put(this._modeloObjCategoria.NOMBRE, this._tvNombre.getText().toString());
        this._valuesCategoria.put(this._modeloObjCategoria.DESCRIPCION, this._tvDescripcion.getText().toString());
        bd.insert("CATEGORIA",null,this._valuesCategoria);
        bd.close();
        consultLawyers();
        this._tvNombre.setText("");
        this._tvDescripcion.setText("");
        Toast.makeText(getApplicationContext(), "llenado con exito", Toast.LENGTH_SHORT).show();
    }
    public void consultLawyers(){
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
            String name = tupla.getString(tupla.getColumnIndex(_modeloObjCategoria.NOMBRE));
            // Acciones...
            System.out.println("Categoria:"+name);
        }
        bd.close();
    }
}
