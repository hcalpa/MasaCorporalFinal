package com.andrescalpa7.masacorporalfinal;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Registro extends AppCompatActivity {

    public TextView tv_resultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        tv_resultados=(TextView) findViewById(R.id.tv_resultados);
        String[] archivos = fileList();

        if (existe(archivos, "datos.txt"))
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput("datos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                tv_resultados.setText(todo);
            } catch (IOException e) {
            }
    }

    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }

    public void regresar(View ve) {
        finish();
    }

}
