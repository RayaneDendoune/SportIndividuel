package model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * \file Seance_natation.java
 * \brief Classe qui permet de créer l'objet Seance_natation
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création d'une séance de natation
 *
 */
//Table "Seance_natation" pour la base de donnée
@Entity
public class Seance_natation {

    @Id
    private String id_seance_natation;
    private int nb_longueur;
    private Time temps_total;
    private String type_nage;
    private int calorie_perdu;
    private Time temps_moy_longueur;
    private Date date;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)
    private Individu individu;

    public Seance_natation() {}

    /**
     * \fn Seance_natation(String id_seance_natation, int nb_longueur, Time temps_total, String type_nage, int calorie_perdu, Time temps_moy_longueur, Date date, Individu individu)
     * \brief Constructeur de Seance_natation
     * \param [in] id_seance_natation Clé primaire de la table Seance_natation (Type String)
     * \param [in] nb_longueur Nombre de longueur effectué durant la séance (Type Integer)
     * \param [in] temps_total Temps total passé durant la séance (Type Time)
     * \param [in] type_nage Type de nage effectué par l'individu durant sa séance (Type String)
     * \param [in] calorie_perdu Nombre de calorie perdu par l'individu pendant sa séance (Type Integer)
     * \param [in] temps_moy_longueur Temps moyen par longueur fait par l'individu durant sa séance (Type Time)
     * \param [in] date Date où la séance à été effectuée (Type Date)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     *
     */
    public Seance_natation(String id_seance_natation, int nb_longueur, Time temps_total, String type_nage, int calorie_perdu, Time temps_moy_longueur, Date date, Individu individu) {
        this.id_seance_natation = id_seance_natation;
        this.nb_longueur = nb_longueur;
        this.temps_total = temps_total;
        this.type_nage = type_nage;
        this.calorie_perdu = calorie_perdu;
        this.temps_moy_longueur = temps_moy_longueur;
        this.date = date;
        this.individu = individu;
    }

    /**
     * \fn String getId_seance_natation()
     * \brief Fonction qui retourne l'identifiant de la séance de natation
     * \return Retourne un String avec l'identifiant de la séance de natation
     */
    public String getId_seance_natation() {
        return id_seance_natation;
    }

    /**
     * \fn void setId_seance_natation(String id_seance_natation)
     * \brief Fonction qui modifie l'identifiant de la séance de natation
     * \param [in] id_seance_natation Clé primaire de la table Seance_natation (Type String)
     */
    public void setId_seance_natation(String id_seance_natation) {
        this.id_seance_natation = id_seance_natation;
    }

    /**
     * \fn int getNb_longueur()
     * \brief Fonction qui retourne le nombre de longueur faite par l'utilisateur durant la séance de natation
     * \return Retourne un Integer avec le nombre de longueur fait
     */
    public int getNb_longueur() {
        return nb_longueur;
    }

    /**
     * \fn void setNb_longueur(int nb_longueur)
     * \brief Fonction qui modifie le nombre de longueur faite par l'utilisateur durant la séance de natation
     * \param [in] nb_longueur Nombre de longueur effectué durant la séance (Type Integer)
     */
    public void setNb_longueur(int nb_longueur) {
        this.nb_longueur = nb_longueur;
    }

    /**
     * \fn Time getTemps_total()
     * \brief Fonction qui retourne le temps total de la séance de natation
     * \return Retourne un Time avec le temps total
     */
    public Time getTemps_total() {
        return temps_total;
    }

    /**
     * \fn void setTemps_total(Time temps_total)
     * \brief Fonction qui modifie le temps total de la séance de natation
     * \param [in] temps_total Temps total passé durant la séance (Type Time)
     */
    public void setTemps_total(Time temps_total) {
        this.temps_total = temps_total;
    }

    /**
     * \fn String getType_nage()
     * \brief Fonction qui retourne le type de nage de l'utilisateur
     * \return Retourne un String avec le type de nage de l'utilisateur
     */
    public String getType_nage() {
        return type_nage;
    }

    /**
     * \fn setType_nage(String type_nage)
     * \brief Fonction qui modifie le type de nage de l'utilisateur
     * \param [in] type_nage Type de nage effectué par l'individu durant sa séance (Type String)
     */
    public void setType_nage(String type_nage) {
        this.type_nage = type_nage;
    }

    /**
     * \fn int getCalorie_perdu()
     * \brief Fonction qui retourne les calories perdues de l'utilisateur durant la séance
     * \return Retourne un Integer avec les calories perdues de l'utilisateur
     */
    public int getCalorie_perdu() {
        return calorie_perdu;
    }

    /**
     * \fn void setCalorie_perdu(int calorie_perdu)
     * \brief Fonction qui modifie les calories perdues de l'utilisateur durant la séance
     * \param [in] calorie_perdu Nombre de calorie perdu par l'individu pendant sa séance (Type Integer)
     */
    public void setCalorie_perdu(int calorie_perdu) {
        this.calorie_perdu = calorie_perdu;
    }

    /**
     * \fn Time getTemps_moy_longueur()
     * \brief Fonction qui retourne le temps moyen par longueur de l'utilisateur durant la séance
     * \return Retourne un Time avec temps moyen par longueur de l'utilisateur
     */
    public Time getTemps_moy_longueur() {
        return temps_moy_longueur;
    }

    /**
     * \fn void setTemps_moy_longueur(Time temps_moy_longueur)
     * \brief Fonction qui modifie le temps moyen par longueur de l'utilisateur durant la séance
     * \param [in] calorie_perdu Nombre de calorie perdu par l'individu pendant sa séance (Type Integer)
     */
    public void setTemps_moy_longueur(Time temps_moy_longueur) {
        this.temps_moy_longueur = temps_moy_longueur;
    }

    /**
     * \fn Date getDate()
     * \brief Fonction qui retourne la date de la séance de natation
     * \return Retourne un Date avec la date de la séance
     */
    public Date getDate() {
        return date;
    }

    /**
     * \fn void setDate(Date date)
     * \brief Fonction qui modifie la date de la séance de natation
     * \param [in] date Date où la séance à été effectuée (Type Date)
     */
    public void setDate(Date date) {
        this.date = date;
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
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     */
    public void setIndividu(Individu individu) {
        this.individu = individu;
    }
}
