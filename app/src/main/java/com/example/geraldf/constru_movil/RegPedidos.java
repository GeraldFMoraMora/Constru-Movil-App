package com.example.geraldf.constru_movil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.geraldf.constru_movil.R;
/**
 * Created by GeraldF on 05/11/2016.
 */
public class RegPedidos extends AppCompatActivity {
    /**
     *
     * @param savedIntanceState
     */
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.actpedidos);
    }

    /**
     *
     */
    @Override
    public void onBackPressed(){
        finish();
    }
}
