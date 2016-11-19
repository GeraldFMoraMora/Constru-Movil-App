package com.example.geraldf.constru_movil;

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
    /**
     *
     * @param savedIntanceState
     */
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.actpedidos);
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
            String name = tupla.getString(tupla.getColumnIndex(_moModeloObjPedido.ID));
            // Acciones...
            System.out.println("ID:"+name);
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
                _pedidoConsultar=listValues.get(position).toString();

                //ejecutarViewProducto();
            }
        });
    }
}
