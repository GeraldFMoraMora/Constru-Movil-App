package com.example.geraldf.constru_movil;

import android.content.ContentValues;
import android.content.Intent;
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
 * Created by GeraldF on 08/11/2016.
 */
public class ViewLoginVendedor extends AppCompatActivity {
    private Intent _screen;
    private Intent _entrada;

    private ContentValues _valuesUser;
    private ContentValues _valuesRol_Cliente;

    private EditText _usernameET;
    private EditText _usercontrasenaET;
    private String usernameviewer;
    private String usernamevendedorviewer;
    private String _user;
    private String _contrasena;
    private Boolean _bandera0=false;
    private Boolean _bandera1=false;
    private Boolean _bandera2=false;

    private ModeloObjUsuario _modeloObjUsuario= new ModeloObjUsuario();
    private ModeloObjRol_Usu _ModeloObjRol_usu;

    private String[] _atributos= new String[]{_modeloObjUsuario.USER,_modeloObjUsuario.CONTRASEÑA};
    /**
     *
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin2:
                this.consultLawyers();

                if(_bandera0.equals(true)){
                    if(_bandera1.equals(true)){
                        this.usernamevendedorviewer =this._usernameET.getText().toString();
                        _screen=new Intent(this,ConstruMainActivity.class);
                        _screen.putExtra("usernamevendedorviewer", usernamevendedorviewer);
                        _screen.putExtra("usernameviewer",this.usernameviewer);
                        startActivity(_screen);
                    }else{
                        Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstancedState){
        super.onCreate(savedInstancedState);
        setContentView(R.layout.actviewloginvendedor);

        _valuesUser= new ContentValues();
        _valuesRol_Cliente=new ContentValues();

        this._usernameET = (EditText) findViewById(R.id.entryUserNameVendedor);
        this._usercontrasenaET = (EditText) findViewById(R.id.entryUserPasswordVendedor);
        _entrada=getIntent();
        this.usernameviewer=_entrada.getStringExtra("usernameviewer");

        this.llenadoDatos();
    }
    @Override
    public void onBackPressed(){
        finish();
    }
    public void consultLawyers(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("USUARIO", // Nombre de la tabla
                this._atributos, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){
            this._user = tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.USER));
            // Acciones...
            this._contrasena= tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.CONTRASEÑA));

            if(this._usernameET.getText().toString().equals(this._user)){
                this._bandera0=true;
                if(this._usercontrasenaET.getText().toString().equals(this._contrasena)){
                    this._bandera1=true;
                }
            }
        }
        bd.close();
    }
    /**
     *
     */
    public void llenadoDatos(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();

        this.consultProveedores();

        if(this._bandera2!=true){
            this._valuesUser.put(this._modeloObjUsuario.USER, "admin22");
            this._valuesUser.put(this._modeloObjUsuario.CONTRASEÑA, "admin");
            this._valuesUser.put(this._modeloObjUsuario.CEDULA, "116040975");
            this._valuesUser.put(this._modeloObjUsuario.NOMBRE, "Vendedor");
            this._valuesUser.put(this._modeloObjUsuario.P_APELLIDO, "m");
            this._valuesUser.put(this._modeloObjUsuario.S_APELLIDO, "m");
            this._valuesUser.put(this._modeloObjUsuario.F_NACIMIENTO,"15-04-1995");
            this._valuesUser.put(this._modeloObjUsuario.TELEFONO, "86509736");
            this._valuesUser.put(this._modeloObjUsuario.PROVINCIA, "Cartago");
            this._valuesUser.put(this._modeloObjUsuario.CANTON, "Cartago");
            this._valuesUser.put(this._modeloObjUsuario.DISTRITO, "TEC");
            bd.insert("USUARIO",null,this._valuesUser);
            MotorBaseDatos admi1= new MotorBaseDatos(this);
            SQLiteDatabase bd1= admi1.getWritableDatabase();
            this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_USUARIO, "admin22");
            this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_ROL, "2");
            bd1.insert("ROL_USUARIO",null,this._valuesRol_Cliente);
            bd1.close();
            this._bandera2=false;
        }
        bd.close();
    }
    public void consultProveedores(){
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
            // Acciones...
            if("admin22".equals(tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.USER)))){
                this._bandera2=true;
            }
        }
        bd.close();
    }
}
