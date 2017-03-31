package com.andrescalpa7.masacorporalfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public EditText et_alt;
    public EditText et_Peso;
    public TextView tv_res;
    public ImageView iv_most;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_alt = (EditText) findViewById(R.id.et_alt);
        et_Peso = (EditText) findViewById(R.id.et_Peso);
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        et_alt.setText(prefe.getString("altura",""));
        et_Peso.setText(prefe.getString("peso",""));

        tv_res = (TextView) findViewById(R.id.tv_res);
        iv_most = (ImageView) findViewById(R.id.iv_most);


    }

    public void acercade(View view) {
        Intent i = new Intent(this, AcercaDe.class );
        startActivity(i);
    }

    public void registrar(View v){

        Calendar c = Calendar.getInstance();

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "datos.txt", Activity.MODE_APPEND));
            archivo.write( c.getTime() + "\n" +getString(R.string.altura) + et_alt.getText().toString() + " " );
            archivo.write( getString(R.string.peso) + et_Peso.getText().toString() + " ||" + "\n");
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, R.string.datosGrabados,
                Toast.LENGTH_SHORT);
        t.show();

        Intent i = new Intent(this, Registro.class );
        startActivity(i);

    }

    public void salir(View v) {
        System.exit(0);
    }


    public void calcu(View view){

        String valor1 = et_alt.getText().toString();
        String valor2 = et_Peso.getText().toString();


        try {
            Float altura = Float.parseFloat(valor1);
            int peso = Integer.parseInt(valor2);
            Float calculo = peso/(altura*altura);
            Double calculo2 = Math.rint(calculo*100)/100;
            String resultado = String.valueOf(calculo2);

            if (calculo < 16) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.DelgadezSevera));

                iv_most.setImageResource(R.drawable.uno);
            } else if (calculo > 16 && calculo < 16.99) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.DelgadezModerada));
                iv_most.setImageResource(R.drawable.dos);
            } else if (calculo > 17 && calculo < 18.49) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.DelgadezAceptable));
                iv_most.setImageResource(R.drawable.tres);
            } else if (calculo > 18.50 && calculo < 24.99) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.PesoNormal));
                iv_most.setImageResource(R.drawable.cuatro);
            } else if (calculo > 25 && calculo < 29.99) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.SobrePeso));
                iv_most.setImageResource(R.drawable.cinco);
            } else if (calculo > 30 && calculo < 34.99) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.ObesoTipo) + "1");
                iv_most.setImageResource(R.drawable.seis);
            } else if (calculo > 35 && calculo < 40) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.ObesoTipo) + "2");
                iv_most.setImageResource(R.drawable.siete);
            } else if (calculo > 40) {
                tv_res.setText(getString(R.string.Indice1) + resultado + "/" + getString(R.string.ObesoTipo) + "3");
                iv_most.setImageResource(R.drawable.ocho);
            }
        }

        catch (NumberFormatException nu){
            Toast toast = Toast.makeText(this, R.string.IngreseValores, Toast.LENGTH_SHORT);
            toast.show();
        }

        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(getString(R.string.alt), et_alt.getText().toString());
        editor.putString(getString(R.string.pes), et_Peso.getText().toString());
        editor.commit();
        Toast.makeText(this, R.string.datosGrabados, Toast.LENGTH_LONG).show();
    }
}
