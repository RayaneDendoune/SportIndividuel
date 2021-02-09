package model;

import javax.persistence.*;

@Entity
public class Seance_tennis {

    @Id
    private String id_seance_tennis;
    private float premier_service;
    private float deuxieme_service;
    private float troisieme_service;
    private int nb_set;
    private char issue_match;
    private float vitesse_moy_service;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)
    private Individu individu;

    public Seance_tennis() {}

    public Seance_tennis(String id_seance_tennis, float premier_service, float deuxieme_service, float troisieme_service, int nb_set, char issue_match, float vitesse_moy_service, Individu individu) {
        this.id_seance_tennis = id_seance_tennis;
        this.premier_service = premier_service;
        this.deuxieme_service = deuxieme_service;
        this.troisieme_service = troisieme_service;
        this.nb_set = nb_set;
        this.issue_match = issue_match;
        this.vitesse_moy_service = vitesse_moy_service;
        this.individu = individu;
    }

    public String getId_seance_tennis() {
        return id_seance_tennis;
    }

    public void setId_seance_tennis(String id_seance_tennis) {
        this.id_seance_tennis = id_seance_tennis;
    }

    public float getPremier_service() {
        return premier_service;
    }

    public void setPremier_service(float premier_service) {
        this.premier_service = premier_service;
    }

    public float getDeuxieme_service() {
        return deuxieme_service;
    }

    public void setDeuxieme_service(float deuxieme_service) {
        this.deuxieme_service = deuxieme_service;
    }

    public float getTroisieme_service() {
        return troisieme_service;
    }

    public void setTroisieme_service(float troisieme_service) {
        this.troisieme_service = troisieme_service;
    }

    public int getNb_set() {
        return nb_set;
    }

    public void setNb_set(int nb_set) {
        this.nb_set = nb_set;
    }

    public char getIssue_match() {
        return issue_match;
    }

    public void setIssue_match(char issue_match) {
        this.issue_match = issue_match;
    }

    public float getVitesse_moy_service() {
        return vitesse_moy_service;
    }

    public void setVitesse_moy_service(float vitesse_moy_service) {
        this.vitesse_moy_service = vitesse_moy_service;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }
}
