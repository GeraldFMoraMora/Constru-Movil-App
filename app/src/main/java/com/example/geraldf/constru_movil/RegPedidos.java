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

import com.example.geraldf.constru_movil.R;

import java.util.ArrayList;

import basedatos.ModeloObjPedido;
import basedatos.MotorBaseDatos;

/**
 * Created by GeraldF on 05/11/2016.
 */
public class RegPedidos extends AppCompatActivity {
    ListView prueba;
    ArrayList<String> listValues;
    private ModeloObjPedido _moModeloObjPedido;
    private String _pedidoConsultar;
    private Intent _screen;
    /**
     *
     * @param savedIntanceState
     */
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.actpedidos);
        listValues = new ArrayList<String>();
        this.prueba=(ListView)findViewById(R.id.turras1);
        this.crearmenu();
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
    public void crearmenu(){
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
            String id = tupla.getString(tupla.getColumnIndex(_moModeloObjPedido.ID));
            String fecha = tupla.getString(tupla.getColumnIndex(_moModeloObjPedido.FECHA));
            String hora = tupla.getString(tupla.getColumnIndex(_moModeloObjPedido.HORA));
            String estado = tupla.getString(tupla.getColumnIndex(_moModeloObjPedido.ESTADO));
            String total = tupla.getString(tupla.getColumnIndex(_moModeloObjPedido.TOTAL));
            // Acciones...
            System.out.println("ID:"+id);
            this.listValues.add(id);
        }
        bd.close();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listValues);
        this.prueba.setAdapter(arrayAdapter);
        prueba.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), listValues.get(position), Toast.LENGTH_SHORT).show();

                _pedidoConsultar=listValues.get(position).toString();


                openView();
                //ejecutarViewProducto();
            }
        });
    }
    /**
     * Metodo para poder utilizar una inicializacion de actividad por medio del intent.
     */
    public void openView(){
        _screen= new Intent(this, ViewPedidos.class);
        _screen.putExtra("_id_pedido",_pedidoConsultar);
        startActivity(_screen);
    }
}
