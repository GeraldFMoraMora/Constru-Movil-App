package com.example.geraldf.constru_movil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import basedatos.BaseDatosSecundaria;
import basedatos.ModeloObjPedido;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 19/11/2016.
 */
public class ViewPedidos extends AppCompatActivity{
    private TextView _fecha,_hora,_estado,_total,_id;
    private String _id_pedido;
    private Intent _entrada;
    private String _hora1,_fecha1,_total1,_estado1,_id1;
    private ModeloObjPedido _ModeloObjPedido;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actviewpedidos);

        this._fecha=(TextView) findViewById(R.id._fechafecha);
        this._hora=(TextView) findViewById(R.id._fechafecha);
        this._id=(TextView) findViewById(R.id._fechafecha);
        this._total=(TextView) findViewById(R.id._fechafecha);
        this._estado=(TextView) findViewById(R.id._fechafecha);

        this._entrada=getIntent();
        this._id_pedido=_entrada.getStringExtra("_id_pedido");
        System.out.println("+++++************************+++++++++++++++++++********************++++++++++++"+_id_pedido);

        this.leerDatos();

        this._fecha.setText(this._fecha1);
        this._hora.setText(this._hora1);
        this._id.setText(this._id1);
        this._total.setText(this._total1);
        this._estado.setText(this._estado1);
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
    public void leerDatos(){
        MotorBaseDatos admi= new MotorBaseDatos(this);
        SQLiteDatabase bd= admi.getWritableDatabase();
        Cursor tupla = bd.query("PEDIDO", // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null); // Cláusula ORDER BY
        while(tupla.moveToNext()){
            if (this._id_pedido.equals(tupla.getString(tupla.getColumnIndex(_ModeloObjPedido.ID)))){
                this._id1 = tupla.getString(tupla.getColumnIndex(_ModeloObjPedido.ID));
                this._fecha1 = tupla.getString(tupla.getColumnIndex(_ModeloObjPedido.FECHA));
                this._hora1 = tupla.getString(tupla.getColumnIndex(_ModeloObjPedido.HORA));
                this._estado1 = tupla.getString(tupla.getColumnIndex(_ModeloObjPedido.ESTADO));
                this._total1 = tupla.getString(tupla.getColumnIndex(_ModeloObjPedido.TOTAL));
            }
            // Acciones...
            System.out.println("ID:"+_id1);
        }
        bd.close();
    }
}
