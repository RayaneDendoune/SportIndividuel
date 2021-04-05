package model;

import javax.persistence.*;
import java.sql.Time;

/**
 * \file Partie_echec.java
 * \brief Classe qui permet de créer l'objet Partie_echec
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création d'une partie d'echec.
 *
 */
//Table "Partie_echec" pour la base de donnée
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

    /**
     * \fn Partie_echec(String id_partie_echec, int elo_adversaire, int futur_elo, Time duree, String niveau_competence_mentale, char issue_partie, int niveau_concentration, Individu individu)
     * \brief Constructeur de Partie_echec
     * \param [in] id_partie_echec Clé primaire de la table Partie_echec (Type String)
     * \param [in] elo_adversaire Elo de l'adversaire (Type Integer)
     * \param [in] futur_elo Future Elo de l'individu après la partie en cours (Type Integer)
     * \param [in] duree Duree totale de la partie (Type Time)
     * \param [in] niveau_competence_mentale Niveau de Compétence Mentale (Type String)
     * \param [in] issue_partie Issue de la partie (Type Character)
     * \param [in] niveau_concentration Niveau de Concentration (Type Integer)
     * \param [in] individu Individu (Type Individu)
     */
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

    /**
     * \fn String getId_partie_echec()
     * \brief Fonction qui retourne l'identifiant de la partie d'échec
     * \return Retourne un String avec l'identifiant de la partie d'échec
     */
    public String getId_partie_echec() {
        return id_partie_echec;
    }

    /**
     * \fn void setId_partie_echec(String id_partie_echec)
     * \brief Fonction qui modifie l'identifiant de la partie d'échec
     * \param [in] id_partie_echec Clé primaire de la table Partie_echec (Type String)
     *
     */
    public void setId_partie_echec(String id_partie_echec) {
        this.id_partie_echec = id_partie_echec;
    }

    /**
     * \fn int getElo_adversaire()
     * \brief Fonction qui retourne l'elo de l'adversaire
     * \return Retourne un Integer avec l'elo de l'adversaire
     */
    public int getElo_adversaire() {
        return elo_adversaire;
    }

    /**
     * \fn void setElo_adversaire(int elo_adversaire)
     * \brief Fonction qui modifie l'elo de l'adversaire
     * \param [in] elo_adversaire Elo de l'adversaire (Type Integer)
     */
    public void setElo_adversaire(int elo_adversaire) {
        this.elo_adversaire = elo_adversaire;
    }

    /**
     * \fn int getFutur_elo()
     * \brief Fonction qui retourne l'elo de l'individu après la partie
     * \return Retourne un Integer avec l'elo de l'individu après la partie
     */
    public int getFutur_elo() {
        return futur_elo;
    }

    /**
     * \fn void setFutur_elo(int futur_elo)
     * \brief Fonction qui modifie l'elo de l'individu après la partie
     * \param [in] futur_elo Future Elo de l'individu après la partie en cours (Type Integer)
     */
    public void setFutur_elo(int futur_elo) {
        this.futur_elo = futur_elo;
    }

    /**
     * \fn Time getDuree()
     * \brief Fonction qui retourne la durée d'une partie
     * \return Retourne un Time avec la durée d'une partie
     */
    public Time getDuree() {
        return duree;
    }

    /**
     * \fn void setDuree(Time duree)
     * \brief Fonction qui modifie la durée d'une partie
     * \param [in] duree Duree totale de la partie (Type Time)
     */
    public void setDuree(Time duree) {
        this.duree = duree;
    }

    /**
     * \fn String getNiveau_competence_mentale()
     * \brief Fonction qui retourne le niveau de compétence mental
     * \return Retourne un String avec le niveau de compétence mental
     */
    public String getNiveau_competence_mentale() {
        return niveau_competence_mentale;
    }

    /**
     * \fn void setNiveau_competence_mentale(String niveau_competence_mentale)
     * \brief Fonction qui modifie le niveau de compétence mental
     * \param [in] niveau_competence_mentale Niveau de Compétence Mentale (Type String)
     */
    public void setNiveau_competence_mentale(String niveau_competence_mentale) {
        this.niveau_competence_mentale = niveau_competence_mentale;
    }

    /**
     * \fn char getIssue_partie()
     * \brief Fonction qui retourne l'issue de la partie
     * \return Retourne un Character avec l'issue de la partie
     */
    public char getIssue_partie() {
        return issue_partie;
    }

    /**
     * \fn void setIssue_partie(char issue_partie)
     * \brief Fonction qui modifie l'issue de la partie
     * \param [in] issue_partie Issue de la partie (Type Character)
     */
    public void setIssue_partie(char issue_partie) {
        this.issue_partie = issue_partie;
    }

    /**
     * \fn int getNiveau_concentration()
     * \brief Fonction qui retourne le niveau de concentration
     * \return Retourne un Integer avec le niveau de concentration
     */
    public int getNiveau_concentration() {
        return niveau_concentration;
    }

    /**
     * \fn void setNiveau_concentration(int niveau_concentration)
     * \brief Fonction qui modifie le niveau de concentration
     * \param [in] niveau_concentration Niveau de Concentration (Type Integer)
     */
    public void setNiveau_concentration(int niveau_concentration) {
        this.niveau_concentration = niveau_concentration;
    }

    /**
     * \fn Individu getIndividu()
     * \brief Fonction qui retourne l'individu
     * \return Retourne un Individu
     */
    public Individu getIndividu() {
        return individu;
    }

    /**
     * \fn void setIndividu(Individu individu)
     * \brief Fonction qui modifie l'individu
     * \param [in] individu Individu (Type Individu)
     */
    public void setIndividu(Individu individu) {
        this.individu = individu;
    }
}
