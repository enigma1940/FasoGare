package it.isig.fasogare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ChoixVilleActivity extends AppCompatActivity{
    DBHelper myDb;
    ArrayAdapter<String> ar;

    ArrayList<String> lsVille;

    int check= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_ville);

        //
        myDb = new DBHelper(ChoixVilleActivity.this);
        final Spinner spinVille = (Spinner)findViewById(R.id.spinVilles);
        lsVille = new ArrayList<>();

        Cursor cur = myDb.villeSelect();

        lsVille.add("Choisir une ville");

        while(cur.moveToNext()){
            lsVille.add(cur.getString(0));
        }

        //

        ar = new ArrayAdapter<String>(ChoixVilleActivity.this, R.layout.support_simple_spinner_dropdown_item, lsVille);

        spinVille.setAdapter(ar);

        spinVille.setSelection(0,false);

        spinVille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(lsVille.get(position).toString() != "Choisir une ville") {

                    Intent intent = new Intent(ChoixVilleActivity.this, gare.class);
                    intent.putExtra("ville",parent.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
