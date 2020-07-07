package com.example.myapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadLocale();

        Button changelang= findViewById(R.id.button1);
        Button changeactivity = findViewById(R.id.button2);

        changeactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main2);
            }
        });

        changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangelanguage();
            }
        });
    }

    private void showChangelanguage() {
        final String [] listitems = {"Español","English","French"};
        AlertDialog.Builder lbuilder=new AlertDialog.Builder(MainActivity.this);
        lbuilder.setTitle("Elige un Lenguaje");
        lbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0)
                {
                    setLocale("es");
                    recreate();
                }
                if (which == 1)
                {
                    setLocale("en");
                    recreate();
                }
                if (which == 2)
                {
                    setLocale("fr");
                    recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog mdialog = lbuilder.create();
        mdialog.show();
    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("my_lang",lang);
        editor.apply();



    }

    public void LoadLocale() {
        SharedPreferences prefs = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String strin = prefs.getString("my_lang","");
        setLocale(strin);

    }

}
