package it.isig.fasogare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAM on 4/24/2017.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "fasogare.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE gare(ID INTEGER PRIMARY KEY AUTOINCREMENT,ville VARCHAR(50),nomgare VARCHAR(50),quartier VARCHAR(40),compagnie VARCHAR(50),num_chefgare VARCHAR(50),nom_chefgare VARCHAR(80),num_couriel VARCHAR(50),num_billeterie VARCHAR(50),LONG REAL,LAT REAL,photo VARCHAR(60) NULL)");
        db.execSQL("CREATE TABLE dest(ID INTEGER PRIMARY KEY AUTOINCREMENT, idgare INT NULL, ville VARCHAR(50) NULL, heure VARCHAR(40) NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS gare");
    }
    /*public boolean insert(String nom, double LONG, double LAT, String contact, String ville){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cnt = new ContentValues();
        cnt.put("nomgare", nom);
        cnt.put("LONG", LONG);
        cnt.put("LAT", LAT);
        cnt.put("contact", contact);
        cnt.put("ville", ville);
        long result = db.insert("gare", null, cnt);
        return result!=-1;
    }*/
    public boolean insertGare(String nomgare, String quartier, String compagnie, String num_chefgare, String nom_chefgare, String num_couriel, String num_billeterie, String ville, String photo, double LONG, double LAT){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ct = new ContentValues();
        ct.put("ville", ville);
        ct.put("nomgare", nomgare);
        ct.put("quartier", quartier);
        ct.put("compagnie", compagnie);
        ct.put("num_chefgare", num_chefgare);
        ct.put("nom_chefgare", nom_chefgare);
        ct.put("num_couriel", num_couriel);
        ct.put("num_billeterie", num_billeterie);
        ct.put("LONG", LONG);
        ct.put("LAT", LAT);
        ct.put("photo", photo);
        long result = db.insert("gare", null, ct);
        return result!=-1;
    }
    public Cursor readGare(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = {"ville","nomgare","quartier","compagnie","num_chefgare","nom_chefgare","num_couriel","num_billeterie","LONG","LAT","photo"};
        Cursor cr = db.query("gare", cols, "ID="+id, null, null, null, null, null );
        return cr;
    }
    /*public Cursor gareSelect(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = {"ID", "nomgare", "LONG", "LAT", "contact", "ville"};
        Cursor cr = db.query("gare", cols, null, null, null, null, null, null);
        return cr;
    }*/
    public Cursor nomGareSelect(String ville){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = {"ID","nomgare"};
        Cursor cr = db.query("gare", cols, "ville='"+ville+"'", null, null, null, null, null);
        return cr;
    }
    public Cursor villeSelect(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] cols = {"ville"};
        Cursor cr = db.query(true, "gare", cols, null, null, null, null, null, null);
        return cr;
    }

    /*public boolean insertDestination(){
        boolean result = false;

    }*/
}
