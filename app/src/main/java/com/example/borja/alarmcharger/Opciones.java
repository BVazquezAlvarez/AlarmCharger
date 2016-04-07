package com.example.borja.alarmcharger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Opciones extends AppCompatActivity {
    public static String correoenviar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        Button save = (Button) findViewById(R.id.guardar);
        final EditText correo = (EditText) findViewById(R.id.newCorreo);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileWriter fichero = null;
                PrintWriter pw = null;
                try {
                    OutputStreamWriter fout =
                            new OutputStreamWriter(
                                    openFileOutput("correo.txt", getApplicationContext().MODE_PRIVATE));
                    correoenviar= String.valueOf(correo.getText());

                    fout.write(String.valueOf(correo.getText()));
                    Toast.makeText(getApplicationContext(),
                            "Nuevo correo guardado:" +correoenviar, Toast.LENGTH_SHORT).show();
                    correo.setText("");
                    fout.close();
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }
            }
        });

        Button vercorreo = (Button) findViewById(R.id.vercorreo);
        vercorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    BufferedReader fin =
                            new BufferedReader(
                                    new InputStreamReader(
                                            openFileInput("correo.txt")));

                    correoenviar = fin.readLine();
                    fin.close();
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al leer fichero desde memoria interna");
                }
                Toast.makeText(getApplicationContext(),
                        correoenviar, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.soundalarm:
                if (checked) {
                    MainActivity.soundArmed = true;
                    System.out.println(MainActivity.soundArmed);
                } else
                    MainActivity.soundArmed = false;
                System.out.println(MainActivity.soundArmed);
                break;
            case R.id.localizationalarm:
                if (checked) {
                    MainActivity.gpsArmed = true;
                    System.out.println(MainActivity.gpsArmed);
                } else
                    MainActivity.gpsArmed = false;
                System.out.println(MainActivity.gpsArmed);
                break;
        }
    }
}
