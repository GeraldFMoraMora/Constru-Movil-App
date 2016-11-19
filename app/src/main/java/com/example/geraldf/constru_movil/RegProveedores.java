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
public class RegProveedores extends AppCompatActivity {
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
        setContentView(R.layout.actproveedores);
        _tvUsuario=(EditText) findViewById(R.id.regProveedorUsuario);
        _tvContraseña=(EditText) findViewById(R.id.regProveedorContraseña);
        _tvCedula=(EditText) findViewById(R.id.regProveedorCedula);
        _tvNombre=(EditText) findViewById(R.id.regProveedorNombre);
        _tvPapellido=(EditText) findViewById(R.id.regProveedorApellido1);
        _tvSapellido=(EditText) findViewById(R.id.regProveedorApellido2);
        _tvFnacimiento=(EditText) findViewById(R.id.regProveedorNacimiento);
        _tvTelefono=(EditText) findViewById(R.id.regProveedorTelefono);
        _tvProvincia=(EditText) findViewById(R.id.regProveedorProvincia);
        _tvCanton=(EditText) findViewById(R.id.regProveedorCanton);
        _tvDistrito=(EditText) findViewById(R.id.regProveedorDistrito);
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
        consultUsuarios();
        MotorBaseDatos admi1= new MotorBaseDatos(this);
        SQLiteDatabase bd1= admi1.getWritableDatabase();
        this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_USUARIO, this._tvUsuario.getText().toString());
        this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_ROL, "3");
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
        Toast.makeText(getApplicationContext(), "llenado con exito", Toast.LENGTH_SHORT).show();
    }
    public void consultUsuarios(){
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
            String name = tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.USER));
            // Acciones...
            System.out.println(name);
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
            System.out.println("ID: "+id);
            System.out.println("ID ROL: "+id_rol);
            System.out.println("ID USER: "+id_user);
        }
        bd.close();
    }
}
