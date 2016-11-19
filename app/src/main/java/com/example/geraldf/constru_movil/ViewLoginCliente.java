package com.example.geraldf.constru_movil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import basedatos.ModeloObjUsuario;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 08/11/2016.
 */
public class ViewLoginCliente extends AppCompatActivity {
    private Intent _screen;
    private Intent _entrada;
    private EditText _usernameET;
    private EditText _usercontrasenaET;
    private String usernameviewer;
    private String _user;
    private String _contrasena;
    private Boolean _bandera0=false;
    private Boolean _bandera1=false;

    private ModeloObjUsuario _modeloObjUsuario= new ModeloObjUsuario();
    private String[] _atributos= new String[]{_modeloObjUsuario.USER,_modeloObjUsuario.CONTRASEÑA};

    private String username;
    private String usernameVendedor;

    /**
     *
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin1:
                this.consultClientesRegistrados();

                if(_bandera0.equals(true)){
                    if(_bandera1.equals(true)){
                        this.usernameviewer =this._usernameET.getText().toString();
                        _screen=new Intent(this,ConstruMainActivity.class);
                        _screen.putExtra("usernameviewer", usernameviewer);
                        _screen.putExtra("usernamevendedorviewer", usernameVendedor);
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
        setContentView(R.layout.actviewlogincliente);
        this._usernameET = (EditText) findViewById(R.id.entryUserName);
        this._usercontrasenaET = (EditText) findViewById(R.id.entryUserPassword);
        _entrada=getIntent();
        this.username=_entrada.getStringExtra("username");
        this.usernameVendedor=_entrada.getStringExtra("usernameVendedor");
    }
    @Override
    public void onBackPressed(){
        //this.usernameviewer =this._usernameET.getText().toString();
        //_screen=new Intent(this,ConstruMainActivity.class);
        //_screen.putExtra("usernameviewer", usernameviewer);
        //startActivity(_screen);
        finish();
    }
    public void consultClientesRegistrados(){
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
            System.out.println(_user);
            this._contrasena= tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.CONTRASEÑA));
            System.out.println(_contrasena);

            if(this._usernameET.getText().toString().equals(this._user)){
                this._bandera0=true;
                if(this._usercontrasenaET.getText().toString().equals(this._contrasena)){
                    this._bandera1=true;
                }
            }
        }
        bd.close();
    }
}
