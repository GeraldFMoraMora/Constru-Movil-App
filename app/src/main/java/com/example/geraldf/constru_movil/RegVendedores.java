package com.example.geraldf.constru_movil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by GeraldF on 05/11/2016.
 */
public class RegVendedores extends AppCompatActivity {
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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
