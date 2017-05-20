package it.isig.fasogare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static DBHelper myDb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHelper(MainActivity.this);
        /*
        ArrayList<gareClass> les_gares = new ArrayList<>();
        les_gares.add(new gareClass("TSR wemtenga", "wemtenga", "TSR", "+226 70 xx xx xx", "Michel", "+226 25 36 vv vv", "+226 25 xx xx xx", "Ouagadougou", null, 5.102447, 11.24782));
        les_gares.add(new gareClass("STAFF gounghin", "Gounghin", "STAFF", "+226 70 xx xx xx", "Michel", "+226 25 36 vv vv", "+226 25 xx xx xx", "Ouagadougou", null, 5.102447, 11.24782));
        les_gares.add(new gareClass("RAHIMO ZAD", "ZAD", "RAHIMO", "+226 70 xx xx xx", "Michel", "+226 25 36 vv vv", "+226 25 xx xx xx", "Bobo", null, 5.102447, 11.24782));
        les_gares.add(new gareClass("AIR namende", "Nabasnongin", "AIR namende", "+226 70 xx xx xx", "Michel", "+226 25 36 vv vv", "+226 25 xx xx xx", "Boulsa", null, 5.102447, 11.24782));
        les_gares.add(new gareClass("STAFF", "Secteur 2", "STAFF", "+226 70 xx xx xx", "Michel", "+226 25 36 vv vv", "+226 25 xx xx xx", "Yako", null, 5.102447, 11.24782));
        les_gares.add(new gareClass("TCV kilwin", "kilwin", "TCV", "+226 70 xx xx xx", "Michel", "+226 25 36 vv vv", "+226 25 xx xx xx", "Kaya", null, 5.102447, 11.24782));
        les_gares.add(new gareClass("TSR", "Secteur 2", "TSR", "+226 70 xx xx xx", "Michel", "+226 25 36 vv vv", "+226 25 xx xx xx", "Boromo", null, 5.102447, 11.24782));
        for(gareClass g : les_gares){
            if(g.create(myDb)) Toast.makeText(MainActivity.this, "Insertion OK", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "ERREUR D'INSERTION", Toast.LENGTH_SHORT).show();
        }*/


        findViewById(R.id.btnAbout).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(in);
            }
        });

        findViewById(R.id.btnCarte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this , mymap.class );
                startActivity(in);
            }
        });

        findViewById(R.id.btnGare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, ChoixVilleActivity.class);
                startActivity(in);
            }
        });
    }

    public void startMonCarActivity(View view) {
        Intent in = new Intent(MainActivity.this, MonCarActivity.class);
        startActivity(in);

    }
}
