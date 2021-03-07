package model;

import javax.persistence.*;

@Entity
public class Individu implements Comparable{
    @Id
    private String id_individu;

    private String nom;
    private String prenom;
    private String mdp;
    private char sexe;
    private int age;
    private float poids;
    private float taille;
    private int elo;


    public Individu() {}

    public Individu(String id_individu, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, int elo) {
        super();
        this.id_individu = id_individu;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.sexe = sexe;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.elo = elo;
    }

    public Individu(String id_individu, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille) {
        super();
        this.id_individu = id_individu;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.sexe = sexe;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
    }

    public String getId_individu() {
        return id_individu;
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

    public void setId_individu(String id_individu) {
        this.id_individu = id_individu;
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

    public String toString() {
        String text="";
        //text+="L'id est " + getId_individu();
        text+="Le mdp est " + getMdp();
        return text;
    }

    @Override
    public int compareTo(Object o) {
        int comparePoids=(int)((Individu)o).getPoids();
        return (int) (this.poids-comparePoids);
    }
}
