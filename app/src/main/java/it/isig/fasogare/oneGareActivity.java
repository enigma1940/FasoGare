package it.isig.fasogare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class oneGareActivity extends AppCompatActivity {
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_gare);
        myDb = new DBHelper(oneGareActivity.this);
        gareClass gare = new gareClass();
        gare.setId(getIntent().getExtras().getInt("id"));
        gare.read(myDb);

        TextView chefNum = (TextView)findViewById(R.id.chefNum), courNum = (TextView)findViewById(R.id.courNum), ticketNum = (TextView)findViewById(R.id.ticketNum),
        villeQ = (TextView)findViewById(R.id.villeQuartier);

        chefNum.setText("Chef gare : "+gare.getNum_chefgare());
        courNum.setText("Courrier : "+gare.getNum_couriel());
        ticketNum.setText("Billeterie : "+gare.getNum_billeterie());
        villeQ.setText(gare.getVille()+" - "+gare.getQuartier());

    }
}
