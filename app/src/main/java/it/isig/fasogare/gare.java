package it.isig.fasogare;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class gare extends AppCompatActivity {
    DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gare);
        myDb = new DBHelper(gare.this);

        final TextView nomChef = (TextView)findViewById(R.id.txtNomChef), contactChef = (TextView)findViewById(R.id.txtContactChef),
                contactBill = (TextView) findViewById(R.id.txtContactBill), contactCour = (TextView)findViewById(R.id.txtContactCour),
                nomGare = (TextView)findViewById(R.id.txtNomGare), villeQ = (TextView)findViewById(R.id.txtQuartierVille);

        final ListView listVille = (ListView) findViewById(R.id.listVille);
        Cursor cur = myDb.nomGareSelect(getIntent().getExtras().getString("ville"));
        ArrayList<String> lstg = new ArrayList<>();
        while(cur.moveToNext()){
            lstg.add(cur.getString(0)+" - "+cur.getString(1));
        }
        ArrayAdapter<String> ar = new ArrayAdapter<String>(gare.this, R.layout.support_simple_spinner_dropdown_item, lstg);
        listVille.setAdapter(ar);

        final RelativeLayout layoutInfo = (RelativeLayout)findViewById(R.id.layoutInfoGare);
        findViewById(R.id.btnCloseInfo).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                layoutInfo.setVisibility(View.GONE);
            }
        });
        listVille.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView vId = (TextView) listVille.getChildAt(position);
                String tst = vId.getText().toString();
                gareClass gare = new gareClass();
                gare.setId(Integer.parseInt(tst.subSequence(0, tst.indexOf(" ")).toString()));
                gare.read(myDb);
                layoutInfo.setVisibility(View.VISIBLE);
                nomChef.setText(gare.getNom_chefgare());
                nomGare.setText(gare.getNomgare());
                contactChef.setText(gare.getNum_chefgare());
                contactBill.setText(gare.getNum_billeterie());
                contactCour.setText(gare.getNum_couriel());
                villeQ.setText(gare.getVille()+" : "+gare.getQuartier());
            }
        });
        //Toast.makeText(gare.this, getIntent().getExtras().getString("ville"), Toast.LENGTH_SHORT).show();
        //listVille
    }
}
