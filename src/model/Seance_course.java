package model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Seance_course {

    @Id
    private String id_seance_course;
    private float distance;
    private Time temps;
    private float vitesse_moy;
    private int nb_pas;
    private Date date;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)
    private Individu individu;
    //private String id_coureur;

    public Seance_course() {}

    public Seance_course(String id_seance_course, float distance, Time temps, float vitesse_moy, int nb_pas, Date date, Individu individu) {
        this.id_seance_course = id_seance_course;
        this.distance = distance;
        this.temps = temps;
        this.vitesse_moy = vitesse_moy;
        this.nb_pas = nb_pas;
        this.date = date;
        this.individu = individu;
    }

    public String getId_seance_course() {
        return id_seance_course;
    }

    public void setId_seance_course(String id_seance_course) {
        this.id_seance_course = id_seance_course;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Time getTemps() {
        return temps;
    }

    public void setTemps(Time temps) {
        this.temps = temps;
    }

    public float getVitesse_moy() {
        return vitesse_moy;
    }

    public void setVitesse_moy(float vitesse_moy) {
        this.vitesse_moy = vitesse_moy;
    }

    public int getNb_pas() {
        return nb_pas;
    }

    public void setNb_pas(int nb_pas) {
        this.nb_pas = nb_pas;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    public String toString() {
        String text ="";
        text += "L'id de la course est " + getId_seance_course();
        return text;
    }
}
