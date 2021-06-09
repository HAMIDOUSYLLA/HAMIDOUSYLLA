package com.estem.applicationweither;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListeVille extends AppCompatActivity {
    ListView ls;
    Helper h= new Helper(ListeVille.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_ville);
        ls = findViewById(R.id.lst);
        ls = findViewById(R.id.lst);
        // pour récupérer tous les listes
        List<Ville> listevilles= h.getAllVille();
        // création d'une liste de nom de ville en chaine de caractère
        List<String> nomsVilles = new ArrayList<>();
        for (Ville v:listevilles){
            //je mets les noms des villes dans la liste nom de ville
            nomsVilles.add(v.getVille());
        }

    // sa c pour l'adaption de ma liste de nom dans la listeView
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1,nomsVilles);
        ls.setAdapter(adapter);


    }
}