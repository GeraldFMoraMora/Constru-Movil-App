package com.example.geraldf.constru_movil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import basedatos.ModeloObjRol;
import basedatos.ModeloObjRol_Usu;
import basedatos.ModeloObjSucursal;
import basedatos.ModeloObjUsuario;
import basedatos.MotorBaseDatos;

/**
 *
 */
public class ConstruMainActivity extends AppCompatActivity {

    private TextView _btnRegistro;
    private TextView _btnMantenimiento;
    private TextView _btnControl;
    private TextView _Saludo0;
    private TextView _Saludo1;

    private Button _update;

    private RelativeLayout _layoutMainOptions;
    private RelativeLayout _layoutRegOptions;
    private RelativeLayout _layoutControlOptions;
    private RelativeLayout _layoutManteOptions;

    private Intent _screenReg;
    private Intent _entrada;
    private String username;
    private String usernameVendedor;

    private ContentValues _valuesRol;
    private ContentValues _valuesSucursales;
    private ContentValues _valuesUser;
    private ContentValues _valuesRol_Cliente;

    private ContentValues _valuesParaPedido;

    public ModeloObjRol _modeloObjRol;
    public ModeloObjSucursal _modeloObjSucursal;
    public ModeloObjUsuario _modeloObjUsuario;
    private ModeloObjRol_Usu _ModeloObjRol_usu;

    private Boolean bandera0=false;

    LinkedList comidas;
    Spinner prueba;

    /**
     * Metodo constructor de la clase
     */
    public ConstruMainActivity(){


    }

    /**
     *
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnStart:
                Toast.makeText(getApplicationContext(), "Hola gerald", Toast.LENGTH_SHORT).show();
                this.llenadoDatos();
                this._update.setVisibility(View.INVISIBLE);
                break;
            case R.id.tvRegistro:
                Toast.makeText(getApplicationContext(), "Registro", Toast.LENGTH_SHORT).show();
                _layoutMainOptions.setVisibility(View.INVISIBLE);
                _layoutRegOptions.setVisibility(View.VISIBLE);
                break;
            case R.id.tvControl:
                Toast.makeText(getApplicationContext(), "Control", Toast.LENGTH_SHORT).show();
                _layoutControlOptions.setVisibility(View.VISIBLE);
                _layoutMainOptions.setVisibility(View.INVISIBLE);
                break;
            case R.id.tvMantenimiento:
                Toast.makeText(getApplicationContext(), "Mantenimiento", Toast.LENGTH_SHORT).show();
                _layoutManteOptions.setVisibility(View.VISIBLE);
                _layoutMainOptions.setVisibility(View.INVISIBLE);
                break;
            case R.id.tvCatalogo:
                Toast.makeText(getApplicationContext(), "Catalogo", Toast.LENGTH_SHORT).show();
                _screenReg=new Intent(this,ViewCatalogo.class);
                _screenReg.putExtra("_valuesParaPedido",_valuesParaPedido);
                startActivity(_screenReg);
                break;
            case R.id.btnRegCategoria:
                _screenReg=new Intent(this,RegCategoria.class);
                startActivity(_screenReg);
                break;
            case R.id.btnRegCliente:
                _screenReg=new Intent(this,RegClientes.class);
                startActivity(_screenReg);
                break;
            case R.id.btnRegProducto:
                _screenReg=new Intent(this,RegProductos.class);
                startActivity(_screenReg);
                break;
            case R.id.btnRegProvedor:
                _screenReg= new Intent(this,RegProveedores.class);
                startActivity(_screenReg);
                break;
            case R.id.sesion:
                _screenReg=new Intent(this,ViewLoginVendedor.class);
                _screenReg.putExtra("usernameviewer", this.username);
                startActivity(_screenReg);
                break;
            case R.id.saludo:
                if (this._Saludo0.getText().equals("Iniciar Sesión")){
                    _screenReg=new Intent(this,ViewLoginCliente.class);
                    startActivity(_screenReg);
                }
                break;
        }
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constru_main);



        _btnRegistro =((TextView) findViewById(R.id.tvRegistro));
        _btnControl =((TextView) findViewById(R.id.tvControl));
        _btnMantenimiento = ((TextView) findViewById(R.id.tvMantenimiento));
        _layoutMainOptions=((RelativeLayout) findViewById(R.id.rlMainOptions));
        _layoutRegOptions=((RelativeLayout) findViewById(R.id.rlRegOptions));
        _layoutControlOptions=((RelativeLayout) findViewById(R.id.rlControlOptions));
        _layoutManteOptions=((RelativeLayout) findViewById(R.id.rlManteOptions));
        _update=((Button) findViewById(R.id.btnStart));

        //_CreatorEdit=(EditText)findViewById(R.id.creatortext1);
        _modeloObjRol= new ModeloObjRol();
        _modeloObjSucursal=new ModeloObjSucursal();

        _valuesRol =new ContentValues();
        _valuesSucursales=new ContentValues();
        _valuesUser= new ContentValues();
        _valuesRol_Cliente=new ContentValues();


        comidas = new LinkedList();
        ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, comidas);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        _Saludo0=(TextView) findViewById(R.id.saludo);
        _Saludo1=(TextView) findViewById(R.id.saludo1);
        _entrada=getIntent();
        this.username=_entrada.getStringExtra("usernameviewer");
        this.usernameVendedor=_entrada.getStringExtra("usernamevendedorviewer");
        _Saludo0.setText("Usuario: "+username);
        _Saludo1.setText("Vendedor: "+usernameVendedor);
        if(_Saludo0.getText().toString().equals("Usuario: "+null)){
            _Saludo0.setText("Iniciar Sesión");
        }
    }

    /**
     *
     */
    @Override
    public void onBackPressed(){
        //_Screen=new Intent(this, typegame.class);
        //startActivity(_Screen);
        _layoutRegOptions.setVisibility(View.INVISIBLE);
        _layoutControlOptions.setVisibility(View.INVISIBLE);
        _layoutManteOptions.setVisibility(View.INVISIBLE);
        _layoutMainOptions.setVisibility(View.VISIBLE);
    }
    /**
     *
     */
    public void llenadoDatos(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        //this._valuesRol.put(this._modeloObjCategoria.ID, "0");

        this.consultProveedores();

        this._valuesRol.put(this._modeloObjRol.TIPO, "Cliente");
        bd.insert("ROL",null,this._valuesRol);
        this._valuesRol.put(this._modeloObjRol.TIPO, "Vendedor");
        bd.insert("ROL",null,this._valuesRol);
        this._valuesRol.put(this._modeloObjRol.TIPO, "Proveedor");
        bd.insert("ROL",null,this._valuesRol);

        if(this.bandera0!=true){
            this._valuesUser.put(this._modeloObjUsuario.USER, "admin");
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
            this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_USUARIO, "admin");
            this._valuesRol_Cliente.put(this._ModeloObjRol_usu.ID_ROL, "2");
            bd1.insert("ROL_USUARIO",null,this._valuesRol_Cliente);
            bd1.close();
        }
        this._valuesSucursales.put(this._modeloObjSucursal.NOMBRE,"EPATEC Cartago");
        bd.insert("SUCURSAL",null,this._valuesSucursales);
        this._valuesSucursales.put(this._modeloObjSucursal.NOMBRE,"EPATEC Limon");
        bd.insert("SUCURSAL",null,this._valuesSucursales);
        this._valuesSucursales.put(this._modeloObjSucursal.NOMBRE,"EPATEC Perez");
        bd.insert("SUCURSAL",null,this._valuesSucursales);
        bd.close();
        
        consultRoles();
        Toast.makeText(getApplicationContext(), "llenado con exito", Toast.LENGTH_SHORT).show();
    }
    public void consultRoles(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("ROL", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){
            String name = tupla.getString(tupla.getColumnIndex(_modeloObjRol.TIPO));
            String id = tupla.getString(tupla.getColumnIndex(_modeloObjRol.ID));
            // Acciones...
            System.out.println("ROL:"+name);
            System.out.println("ROL ID:"+id);
            comidas.add(name);
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
            if("admin".equals(tupla.getString(tupla.getColumnIndex(_modeloObjUsuario.USER)))){
                this.bandera0=true;
            }
        }
        bd.close();
    }
}
