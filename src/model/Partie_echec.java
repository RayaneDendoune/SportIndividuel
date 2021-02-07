package model;

import java.sql.Time;

public class Partie_echec {

    private String id_partie_echec;
    private int elo_adversaire;
    private Time duree;
    private int niveau_competence_mentale;
    private char issue_partie;
    private int niveau_concentration;
    private String id_joueur;

    public Partie_echec() {}

    public Partie_echec(String id_partie_echec, int elo_adversaire, Time duree, int niveau_competence_mentale, char issue_partie, int niveau_concentration, String id_joueur) {
        this.id_partie_echec = id_partie_echec;
        this.elo_adversaire = elo_adversaire;
        this.duree = duree;
        this.niveau_competence_mentale = niveau_competence_mentale;
        this.issue_partie = issue_partie;
        this.niveau_concentration = niveau_concentration;
        this.id_joueur = id_joueur;
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

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public int getNiveau_competence_mentale() {
        return niveau_competence_mentale;
    }

    public void setNiveau_competence_mentale(int niveau_competence_mentale) {
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

    public String getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(String id_joueur) {
        this.id_joueur = id_joueur;
    }
}
