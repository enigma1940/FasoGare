package it.isig.fasogare;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

public class choixVille extends AppCompatActivity{
    DBHelper myDb;
    ArrayAdapter<String> ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_ville);
        myDb = new DBHelper(choixVille.this);
        final Spinner spinVille = (Spinner)findViewById(R.id.spinVilles);
        ArrayList<String> lsVille = new ArrayList<>();
        Cursor cur = myDb.villeSelect();
        lsVille.add("Choisir une ville");
        while(cur.moveToNext()){
            lsVille.add(cur.getString(0));
        }
        ar = new ArrayAdapter<String>(choixVille.this, R.layout.support_simple_spinner_dropdown_item, lsVille);
        spinVille.setAdapter(ar);
        spinVille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString() != "Choisir une ville") {
                    Intent intent = new Intent(choixVille.this, gare.class);
                    intent.putExtra("ville", parent.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
                spinVille.setAdapter(ar);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
