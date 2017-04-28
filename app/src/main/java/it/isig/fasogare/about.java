package it.isig.fasogare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        TextView tabout = (TextView)findViewById(R.id.aboutLab);
        tabout.setText("Cette Application a ete cree par IT ISIG");

    }
}
