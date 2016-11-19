package com.example.geraldf.constru_movil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import basedatos.ModeloObjRol_Usu;
import basedatos.ModeloObjUsuario;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 05/11/2016.
 */
public class RegClientes extends AppCompatActivity {
    private EditText _tvUsuario, _tvContraseña, _tvCedula, _tvNombre, _tvPapellido, _tvSapellido,
    _tvFnacimiento, _tvTelefono, _tvProvincia, _tvCanton, _tvDistrito;

    private ContentValues _valuesUser;
    private ContentValues _valuesRol_Cliente;
    public ModeloObjUsuario _modeloObjUsuario= new ModeloObjUsuario();
    public ModeloObjRol_Usu _ModeloObjRol_usu= new ModeloObjRol_Usu();

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

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actclientes);
        _tvUsuario=(EditText) findViewById(R.id.regClienteUsuario);
        _tvContraseña=(EditText) findViewById(R.id.regClienteContraseña);
        _tvCedula=(EditText) findViewById(R.id.regClienteCedula);
        _tvNombre=(EditText) findViewById(R.id.regClienteNombre);
        _tvPapellido=(EditText) findViewById(R.id.regClientePrimerA);
        _tvSapellido=(EditText) findViewById(R.id.regClienteSegundoA);
        _tvFnacimiento=(EditText) findViewById(R.id.regClienteNacimiento);
        _tvTelefono=(EditText) findViewById(R.id.regClienteTelefono);
        _tvProvincia=(EditText) findViewById(R.id.regClienteProvincia);
        _tvCanton=(EditText) findViewById(R.id.regClienteCanton);
        _tvDistrito=(EditText) findViewById(R.id.regClienteDistrito);
        _valuesUser=new ContentValues();
        _valuesRol_Cliente= new ContentValues();
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
    public void llenadoDatos(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        this._valuesUser.put(this._modeloObjUsuario.USER, this._tvUsuario.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.CONTRASEÑA, this._tvContraseña.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.CEDULA, this._tvCedula.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.NOMBRE, this._tvNombre.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.P_APELLIDO, this._tvPapellido.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.S_APELLIDO, this._tvSapellido.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.F_NACIMIENTO, this._tvFnacimiento.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.TELEFONO, this._tvTelefono.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.PROVINCIA, this._tvProvincia.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.CANTON, this._tvCanton.getText().toString());
        this._valuesUser.put(this._modeloObjUsuario.DISTRITO, this._tvDistrito.getText().toString());
        bd.insert("USUARIO",null,this._valuesUser);
        bd.close();
        MotorBaseDatos admi1= new MotorBaseDatos(this);
        SQLiteDatabase bd1= admi1.getWritableDatabase();
        this.consultClientes();
        this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_USUARIO, this._tvUsuario.getText().toString());
        this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_ROL, "1");
        bd1.insert("ROL_USUARIO",null,this._valuesRol_Cliente);
        bd1.close();
        this.consultRolClientes();
        this._tvUsuario.setText("");
        this._tvContraseña.setText("");
        this._tvCedula.setText("");
        this._tvNombre.setText("");
        this._tvPapellido.setText("");
        this._tvSapellido.setText("");
        this._tvFnacimiento.setText("");
        this._tvTelefono.setText("");
        this._tvProvincia.setText("");
        this._tvCanton.setText("");
        this._tvDistrito.setText("");
        Toast.makeText(getApplicationContext(), "llenado con exito USUARIO Y ROL_USUARIO", Toast.LENGTH_SHORT).show();
    }
    public void consultClientes(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("USUARIO", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
         while(tupla.moveToNext()){
             String name = tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.NOMBRE));
             // Acciones...
             if(this._tvNombre.getText().toString()==name.toString()){
                 System.out.println(tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.USER)));
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
            String id = tupla.getString(tupla.getColumnIndex(_ModeloObjRol_usu.ID));
            String id_rol = tupla.getString(tupla.getColumnIndex(_ModeloObjRol_usu.ID_ROL));
            String id_user = tupla.getString(tupla.getColumnIndex(_ModeloObjRol_usu.ID_USUARIO));
            // Acciones...
        }
        bd.close();
    }

}

