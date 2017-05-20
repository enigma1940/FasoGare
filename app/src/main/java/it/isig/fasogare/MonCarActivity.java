package it.isig.fasogare;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MonCarActivity extends AppCompatActivity {

    DBHelper myDb;
    ArrayAdapter<String> ar;

    ArrayList<String> lsVille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_car);

        loadTrajets();

    }

    private void loadTrajets(){

        myDb = new DBHelper(this);
        final Spinner spinVille = (Spinner)findViewById(R.id.spVilleA);
        final Spinner spinVilleB = (Spinner)findViewById(R.id.spVilleB);
        lsVille = new ArrayList<>();

        Cursor cur = myDb.villeSelect();

        lsVille.add("Choisir une ville");

        while(cur.moveToNext()){
            lsVille.add(cur.getString(0));
        }

        //

        ar = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lsVille);

        spinVille.setAdapter(ar);
        spinVilleB.setAdapter(ar);

    }
}
