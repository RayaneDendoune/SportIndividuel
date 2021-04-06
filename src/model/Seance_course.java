package model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * \file Seance_course.java
 * \brief Classe qui permet de créer l'objet Seance_course
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création d'une séance de course
 *
 */
//Table "Seance_course" pour la base de données
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

    /**
     * \fn Seance_course(String id_seance_course, float distance, Time temps, float vitesse_moy, int nb_pas, Date date, Individu individu)
     * \brief Constructeur de Seance_course
     * \param [in] id_seance_course Clé primaire de la table Seance_course (Type String)
     * \param [in] distance Distance parcourue durant la séance (Type Float)
     * \param [in] temps Temps total de la séance effectuée (Type Time)
     * \param [in] vitesse_moy Vitesse Moyenne que l'individu avait durant la séance (Type Float)
     * \param [in] nb_pas Nombre de pas moyen effectué par l'utilisateur durant la séance (Type Integer)
     * \param [in] date Date où la séance à été effectuée (Type Date)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     */
    public Seance_course(String id_seance_course, float distance, Time temps, float vitesse_moy, int nb_pas, Date date, Individu individu) {
        this.id_seance_course = id_seance_course;
        this.distance = distance;
        this.temps = temps;
        this.vitesse_moy = vitesse_moy;
        this.nb_pas = nb_pas;
        this.date = date;
        this.individu = individu;
    }

    /**
     * \fn String getId_seance_course()
     * \brief Fonction qui retourne l'identifiant de la séance de course
     * \return Retourne un String avec l'identifiant de la séance de course
     */
    public String getId_seance_course() {
        return id_seance_course;
    }

    /**
     * \fn void setId_seance_course(String id_seance_course)
     * \brief Fonction qui modifie l'identifiant de la séance de course
     * \param [in] id_seance_course Clé primaire de la table Seance_course (Type String)
     */
    public void setId_seance_course(String id_seance_course) {
        this.id_seance_course = id_seance_course;
    }

    /**
     * \fn float getDistance()
     * \brief Fonction qui retourne la distance de la séance de course
     * \return Retourne un Float avec la distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * \fn void setDistance(float distance)
     * \brief Fonction qui modifie la distance de la séance de course
     * \param [in] distance Distance parcourue durant la séance (Type Float)
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * \fn Time getTemps()
     * \brief Fonction qui retourne le temps de la séance de course
     * \return Retourne un Time avec le temps total
     */
    public Time getTemps() {
        return temps;
    }

    /**
     * \fn void setTemps(Time temps)
     * \brief Fonction qui modifie le temps de la séance de course
     * \param [in] temps Temps total de la séance effectuée (Type Time)
     */
    public void setTemps(Time temps) {
        this.temps = temps;
    }

    /**
     * \fn float getVitesse_moy()
     * \brief Fonction qui retourne la vitesse moyenne de la séance de course
     * \return Retourne un Float avec la vitesse moyenne
     */
    public float getVitesse_moy() {
        return vitesse_moy;
    }

    /**
     * \fn void setVitesse_moy(float vitesse_moy)
     * \brief Fonction qui modifie la vitesse moyenne de la séance de course
     * \param [in] vitesse_moy Vitesse Moyenne que l'individu avait durant la séance (Type Float)
     */
    public void setVitesse_moy(float vitesse_moy) {
        this.vitesse_moy = vitesse_moy;
    }

    /**
     * \fn int getNb_pas()
     * \brief Fonction qui retourne le nombre de pas moyen de la séance de course
     * \return Retourne un Integer avec le nombre de pas moyen
     */
    public int getNb_pas() {
        return nb_pas;
    }

    /**
     * \fn void setNb_pas(int nb_pas)
     * \brief Fonction qui modifie le nombre de pas moyen de la séance de course
     * \param [in] nb_pas Nombre de pas moyen effectué par l'utilisateur durant la séance (Type Integer)
     */
    public void setNb_pas(int nb_pas) {
        this.nb_pas = nb_pas;
    }

    /**
     * \fn Date getDate()
     * \brief Fonction qui retourne la date de la séance de course
     * \return Retourne un Date avec la date de la séance
     */
    public Date getDate() {
        return date;
    }

    /**
     * \fn void setDate(Date date)
     * \brief Fonction qui modifie la date de la séance de course
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
     *
     */
    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    /**
     * \fn String toString()
     * \brief Fonction qui retourne un String
     * \return Retourne un String
     */
    public String toString() {
        String text ="";
        text += "L'id de la course est " + getId_seance_course();
        return text;
    }
}
