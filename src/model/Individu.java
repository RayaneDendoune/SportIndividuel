package model;

import javax.persistence.*;

/**
 * \file Individu.java
 * \brief Classe qui permet de créer l'objet Individu
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création d'une Individu.
 *
 */
//Table "Individu" pour la base de donnée
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

    /**
     * \fn Individu(String id_individu, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, int elo)
     * \brief Constructeur d'Individu
     * \param [in] id_individu Clé primaire de la table Individu (Type String)
     * \param [in] nom Nom de l'individu (Type String)
     * \param [in] prenom Prenom de l'individu (Type String)
     * \param [in] mdp Mot de passe choisi par l'individu (Type String)
     * \param [in] sexe Sexe de l'individu (Type Character)
     * \param [in] age Age de l'individu (Type Integer)
     * \param [in] poids Poids de l'individu (Type Float)
     * \param [in] taille Taille de l'individu (Type Float)
     * \param [in] elo Elo de l'individu (Type Integer)
     */
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

    /**
     * \fn Individu(String id_individu, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille)
     * \brief Constructeur d'Individu
     * \param [in] id_individu Clé primaire de la table Individu (Type String)
     * \param [in] nom Nom de l'individu (Type String)
     * \param [in] prenom Prenom de l'individu (Type String)
     * \param [in] mdp Mot de passe choisi par l'individu (Type String)
     * \param [in] sexe Sexe de l'individu (Type Character)
     * \param [in] age Age de l'individu (Type Integer)
     * \param [in] poids Poids de l'individu (Type Float)
     * \param [in] taille Taille de l'individu (Type Float)
     */
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

    /**
     * \fn String getId_individu()
     * \brief Fonction qui retourne l'identifiant de l'individu
     * \return Retourne un String avec l'identifiant de l'individu
     */
    public String getId_individu() {
        return id_individu;
    }

    /**
     * \fn String getNom()
     * \brief Fonction qui retourne le nom de l'individu
     * \return Retourne un String avec le nom de l'individu
     */
    public String getNom() {
        return nom;
    }

    /**
     * \fn String getPrenom()
     * \brief Fonction qui retourne le prénom de l'individu
     * \return Retourne un String avec le prénom de l'individu
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * \fn String getMdp()
     * \brief Fonction qui retourne le mot de passe de l'individu
     * \return Retourne un String avec le mot de passe de l'individu
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * \fn char getSexe()
     * \brief Fonction qui retourne le sexe de l'individu
     * \return Retourne un Character avec le sexe de l'individu
     */
    public char getSexe() {
        return sexe;
    }

    /**
     * \fn int getAge()
     * \brief Fonction qui retourne l'age de l'individu
     * \return Retourne un Integer avec l'age de l'individu
     */
    public int getAge() {
        return age;
    }

    /**
     * \fn float getPoids()
     * \brief Fonction qui retourne le poids de l'individu
     * \return Retourne un Float avec le poids de l'individu
     */
    public float getPoids() {
        return poids;
    }

    /**
     * \fn float getTaille()
     * \brief Fonction qui retourne la taille de l'individu
     * \return Retourne un Float avec la taille de l'individu
     */
    public float getTaille() {
        return taille;
    }

    /**
     * \fn int getElo()
     * \brief Fonction qui retourne l'elo de l'individu
     * \return Retourne un Integer avec l'elo de l'individu
     */
    public int getElo() {
        return elo;
    }

    /**
     * \fn void setId_individu(String id_individu)
     * \brief Fonction qui modifie l'identifiant de l'individu
     * \param [in] id_individu Clé primaire de la table Individu (Type String)
     */
    public void setId_individu(String id_individu) {
        this.id_individu = id_individu;
    }

    /**
     * \fn void setNom(String nom)
     * \brief Fonction qui modifie le nom de l'individu
     * \param [in] nom Nom de l'individu (Type String)
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * \fn void setPrenom(String nom)
     * \brief Fonction qui modifie le prenom de l'individu
     * \param [in] prenom Prenom de l'individu (Type String)
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * \fn void setMdp(String mdp)
     * \brief Fonction qui modifie le mot de passe de l'individu
     * \param [in] mdp Mot de passe choisi par l'individu (Type String)
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * \fn void setMdp(String mdp)
     * \brief Fonction qui modifie le sexe de l'individu
     * \param [in] sexe Sexe de l'individu (Type Character)
     */
    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    /**
     * \fn void setAge(int age)
     * \brief Fonction qui modifie l'age de l'individu
     * \param [in] age Age de l'individu (Type Integer)
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * \fn void setPoids(float poids)
     * \brief Fonction qui modifie le poids de l'individu
     * \param [in] poids Poids de l'individu (Type Float)
     */
    public void setPoids(float poids) {
        this.poids = poids;
    }

    /**
     * \fn void setTaille(float taille)
     * \brief Fonction qui modifie la taille de l'individu
     * \param [in] taille Taille de l'individu (Type Float)
     */
    public void setTaille(float taille) {
        this.taille = taille;
    }

    /**
     * \fn void setElo(int elo)
     * \brief Fonction qui modifie l'elo de l'individu
     * \param [in] elo Elo de l'individu (Type Integer)
     */
    public void setElo(int elo) {
        this.elo = elo;
    }

    /**
     * \fn String toString()
     * \brief Fonction qui retourne un String
     * \return Retourne un String
     */
    public String toString() {
        String text="";
        //text+="L'id est " + getId_individu();
        text+="Le mdp est " + getMdp();
        return text;
    }

    /**
     * \fn int compareTo(Object o)
     * \brief Fonction qui permet de faire des comparaison
     * \return Retourne un Integer
     */
    @Override
    public int compareTo(Object o) {
        int comparePoids=(int)((Individu)o).getPoids();
        return (int) (this.poids-comparePoids);
    }
}
