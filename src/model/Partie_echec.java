package model;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class Partie_echec {

    @Id
    private String id_partie_echec;
    private int elo_adversaire;
    private int futur_elo;
    private Time duree;
    private String niveau_competence_mentale;
    private char issue_partie;
    private int niveau_concentration;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)
    private Individu individu;

    public Partie_echec() {}

    public Partie_echec(String id_partie_echec, int elo_adversaire, int futur_elo, Time duree, String niveau_competence_mentale, char issue_partie, int niveau_concentration, Individu individu) {
        this.id_partie_echec = id_partie_echec;
        this.elo_adversaire = elo_adversaire;
        this.futur_elo = futur_elo;
        this.duree = duree;
        this.niveau_competence_mentale = niveau_competence_mentale;
        this.issue_partie = issue_partie;
        this.niveau_concentration = niveau_concentration;
        this.individu = individu;
    }

    public String getId_partie_echec() {
        return id_partie_echec;
    }

    public void setId_partie_echec(String id_partie_echec) {
        this.id_partie_echec = id_partie_echec;
    }

    public int getElo_adversaire() {
        return elo_adversaire;
    }

    public void setElo_adversaire(int elo_adversaire) {
        this.elo_adversaire = elo_adversaire;
    }

    public int getFutur_elo() {
        return futur_elo;
    }

    public void setFutur_elo(int futur_elo) {
        this.futur_elo = futur_elo;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public String getNiveau_competence_mentale() {
        return niveau_competence_mentale;
    }

    public void setNiveau_competence_mentale(String niveau_competence_mentale) {
        this.niveau_competence_mentale = niveau_competence_mentale;
    }

    public char getIssue_partie() {
        return issue_partie;
    }

    public void setIssue_partie(char issue_partie) {
        this.issue_partie = issue_partie;
    }

    public int getNiveau_concentration() {
        return niveau_concentration;
    }

    public void setNiveau_concentration(int niveau_concentration) {
        this.niveau_concentration = niveau_concentration;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }
}
