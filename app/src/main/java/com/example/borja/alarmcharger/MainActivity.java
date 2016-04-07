package com.example.borja.alarmcharger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static boolean armed = false;
    public static boolean gpsArmed = true;
    public static boolean soundArmed = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ImageView unplug = (ImageView) findViewById(R.id.unplug);
        final ImageView lock = (ImageView) findViewById(R.id.lock);

        Button armar = (Button) findViewById(R.id.botonlock);
        armar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (armed == true) {
                    armed = false;
                    Toast.makeText(getApplicationContext(),
                            "Desarmado", Toast.LENGTH_SHORT).show();
                    unplug.setVisibility(View.VISIBLE);
                    lock.setVisibility(View.INVISIBLE);
                } else {
                    if(Opciones.correoenviar=="" || Opciones.correoenviar==null){
                        Toast.makeText(getApplicationContext(),
                                "Inserte tu correo desde Settings antes de armarlo", Toast.LENGTH_SHORT).show();
                    }else {
                        armed = true;
                        Toast.makeText(getApplicationContext(),
                                "Armado", Toast.LENGTH_SHORT).show();
                        unplug.setVisibility(View.INVISIBLE);
                        lock.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Opciones.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

