package model;

import javax.persistence.*;

@Entity
public class Individu {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String mdp;
    private char sexe;
    private int age;
    private float poids;
    private float taille;
    private int elo;
    private String frequence_jeu;
    private Amis amis;

    public Individu() {}

    public Individu(String id, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, int elo, String frequence_jeu) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.sexe = sexe;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.elo = elo;
        this.frequence_jeu = frequence_jeu;
    }

    public Individu(String id, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, int elo) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.sexe = sexe;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.elo = elo;
    }

    public Individu(String id, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, String frequence_jeu) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.sexe = sexe;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.frequence_jeu = frequence_jeu;
    }

    public Amis getAmis() {
        return amis;
    }

    public void setAmis(Amis amis) {
        this.amis = amis;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public char getSexe() {
        return sexe;
    }

    public int getAge() {
        return age;
    }

    public float getPoids() {
        return poids;
    }

    public float getTaille() {
        return taille;
    }

    public int getElo() {
        return elo;
    }

    public String getFrequence_jeu() {
        return frequence_jeu;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public void setFrequence_jeu(String frequence_jeu) {
        this.frequence_jeu = frequence_jeu;
    }
}
