package com.example.borja.alarmcharger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Borja on 29/01/2016.
 */

public class PowerConnectionReceiver extends BroadcastReceiver {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    public static Location loc;

    @Override
    public void onReceive(Context context, Intent intent) {
        final boolean armed = MainActivity.armed;
        final boolean gpsArmed = MainActivity.gpsArmed;
        final boolean soundArmed = MainActivity.soundArmed;


        MediaPlayer mp;
        mp = MediaPlayer.create(context, R.raw.alarma);
        if (armed == true && soundArmed == true) {
            mp = MediaPlayer.create(context, R.raw.alarma);
            mp.setLooping(true);
            mp.start();
        }
        final Context fContext = context;

        if (soundArmed == true) {
            final MediaPlayer finalMp = mp;
            new CountDownTimer(60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    final boolean armed = MainActivity.armed;
                    if (armed == false) {
                        finalMp.stop();
                    }
                }

                public void onFinish() {
                    finalMp.stop();
                }
            }.start();

        }
        if (gpsArmed == true) {
            final MediaPlayer finalMp = mp;
            new CountDownTimer(172800000, 3600000) {

                public void onTick(long millisUntilFinished) {
                    final boolean armed = MainActivity.armed;
                    if (armed == true) {

                        comenzarLocalizacion(fContext);
                    } else if (armed == false) {
                        finalMp.stop();
                    }
                }

                public void onFinish() {

                }
            }.start();
        }
    }
    private void comenzarLocalizacion(Context context) {

        try {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(context.openFileInput("correo.txt")));

            Opciones.correoenviar = fin.readLine();
            fin.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
        LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new LocationListener() {
            Location location;

            @Override
            public void onLocationChanged(Location location) {
                this.location = location;
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider) {}
            @Override
            public void onProviderDisabled(String provider) {}
        };
        locManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locListener);

        double altitude = 0;
        double longitude = 0;

        loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (loc != null) {
            altitude = loc.getAltitude();
            longitude = loc.getLongitude();
        }
        Toast.makeText(context,
                "Enviando mail de emergencia", Toast.LENGTH_SHORT).show();
        final double finalAltitude = altitude;
        final double finalLongitude = longitude;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    GMailSender sender = new GMailSender("alarmcharger@gmail.com", "castelao");
                    sender.sendMail("Alarm Charger: Movil Robado",
                            "Tu movil ha sido robado\nUbicacion actual a " + dateFormat.format(cal.getTime()) + ":\n\nAltitude: " + Double.toString(finalAltitude) + "\nLongitude: " + Double.toString(finalLongitude),
                            "alarmcharger@gmail.com",
                            Opciones.correoenviar);
                    System.out.println("Enviado");

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
                return null;
            }
        }.execute();
    }


}







