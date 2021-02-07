package model;

import java.sql.Date;
import java.sql.Time;

public class Seance_natation {

    private String id_seance_natation;
    private int nb_longueur;
    private Time temps_total;
    private String type_nage;
    private int calorie_perdu;
    private Time temps_moy_longueur;
    private Date date;
    private String id_nageur;

    public Seance_natation() {}

    public Seance_natation(String id_seance_natation, int nb_longueur, Time temps_total, String type_nage, int calorie_perdu, Time temps_moy_longueur, Date date, String id_nageur) {
        this.id_seance_natation = id_seance_natation;
        this.nb_longueur = nb_longueur;
        this.temps_total = temps_total;
        this.type_nage = type_nage;
        this.calorie_perdu = calorie_perdu;
        this.temps_moy_longueur = temps_moy_longueur;
        this.date = date;
        this.id_nageur = id_nageur;
    }

    public String getId_seance_natation() {
        return id_seance_natation;
    }

    public void setId_seance_natation(String id_seance_natation) {
        this.id_seance_natation = id_seance_natation;
    }

    public int getNb_longueur() {
        return nb_longueur;
    }

    public void setNb_longueur(int nb_longueur) {
        this.nb_longueur = nb_longueur;
    }

    public Time getTemps_total() {
        return temps_total;
    }

    public void setTemps_total(Time temps_total) {
        this.temps_total = temps_total;
    }

    public String getType_nage() {
        return type_nage;
    }

    public void setType_nage(String type_nage) {
        this.type_nage = type_nage;
    }

    public int getCalorie_perdu() {
        return calorie_perdu;
    }

    public void setCalorie_perdu(int calorie_perdu) {
        this.calorie_perdu = calorie_perdu;
    }

    public Time getTemps_moy_longueur() {
        return temps_moy_longueur;
    }

    public void setTemps_moy_longueur(Time temps_moy_longueur) {
        this.temps_moy_longueur = temps_moy_longueur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId_nageur() {
        return id_nageur;
    }

    public void setId_nageur(String id_nageur) {
        this.id_nageur = id_nageur;
    }
}
