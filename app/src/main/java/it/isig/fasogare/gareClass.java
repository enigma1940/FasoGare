package it.isig.fasogare;

import android.database.Cursor;

/**
 * Created by SAM on 4/24/2017.
 */
public class gareClass {
    int id;
    String nomgare, quartier, compagnie, num_chefgare, nom_chefgare,num_couriel, num_billeterie, ville, photo;
    double LONG,LAT;

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomgare() {
        return nomgare;
    }

    public void setNomgare(String nomgare) {
        this.nomgare = nomgare;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public String getNum_chefgare() {
        return num_chefgare;
    }

    public void setNum_chefgare(String num_chefgare) {
        this.num_chefgare = num_chefgare;
    }

    public String getNom_chefgare() {
        return nom_chefgare;
    }

    public void setNom_chefgare(String nom_chefgare) {
        this.nom_chefgare = nom_chefgare;
    }

    public String getNum_couriel() {
        return num_couriel;
    }

    public void setNum_couriel(String num_couriel) {
        this.num_couriel = num_couriel;
    }

    public String getNum_billeterie() {
        return num_billeterie;
    }

    public void setNum_billeterie(String num_billeterie) {
        this.num_billeterie = num_billeterie;
    }

    public double getLONG() {
        return LONG;
    }

    public void setLONG(double LONG) {
        this.LONG = LONG;
    }

    public double getLAT() {
        return LAT;
    }

    public void setLAT(double LAT) {
        this.LAT = LAT;
    }
    public gareClass(){}
    public gareClass(String nomgare, String quartier, String compagnie, String num_chefgare, String nom_chefgare, String num_couriel, String num_billeterie, String ville, String photo, double LONG, double LAT) {
        this.nomgare = nomgare;
        this.quartier = quartier;
        this.compagnie = compagnie;
        this.num_chefgare = num_chefgare;
        this.nom_chefgare = nom_chefgare;
        this.num_couriel = num_couriel;
        this.num_billeterie = num_billeterie;
        this.ville = ville;
        this.photo = photo;
        this.LONG = LONG;
        this.LAT = LAT;
    }
    public boolean create(DBHelper myDb){
        return myDb.insertGare(this.nomgare, this.quartier, this.compagnie, this.num_chefgare, this.nom_chefgare, this.num_couriel, this.num_billeterie, this.ville, this.photo, this.LONG, this.LAT);
    }
    public void read(DBHelper mydb){
        Cursor cur = mydb.readGare(this.id);
        while(cur.moveToNext()){
            this.nomgare = cur.getString(1);
            this.quartier = cur.getString(2);
            this.compagnie = cur.getString(3);
            this.num_chefgare = cur.getString(4);
            this.nom_chefgare = cur.getString(5);
            this.num_couriel = cur.getString(6);
            this.num_billeterie = cur.getString(7);
            this.ville = cur.getString(0);
            this.photo = cur.getString(10);
            this.LONG = cur.getDouble(8);
            this.LAT = cur.getDouble(9);
        }
    }
}
