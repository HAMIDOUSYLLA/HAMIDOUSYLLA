package com.estem.applicationweither;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DetailVille extends AppCompatActivity {
    EditText ville,temperature;
    Button mod,sup;
    String id;
    Helper h=new Helper(DetailVille.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ville);
        ville=findViewById(R.id.ville);
        temperature = findViewById(R.id.temperature);
        mod = findViewById(R.id.mod);
        sup = findViewById(R.id.sup);
        id = getIntent().getStringExtra("id");
        Ville v=h.getOneVille(Integer.parseInt(id));
        ville.setText(v.getVille());
        temperature.setText(v.temperature);



    }
}