package com.estem.applicationweither;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Accueil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

    }


    public void QUitter(View view) {
        finish();
    }

    public void recherche(View view) {
        Intent i1= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i1);
    }

   public void voirListe(View view) {
        Intent i1= new Intent(getApplicationContext(),ListeVille.class);
        startActivity(i1);
    }


}