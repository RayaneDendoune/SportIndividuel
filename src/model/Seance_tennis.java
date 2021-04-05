package model;

import javax.persistence.*;

/**
 * \file Seance_tennis.java
 * \brief Classe qui permet de créer l'objet Seance_tennis
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création d'une séance de tennis
 *
 */
//Table "Seance_tennis" pour la base de donnée
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

    /**
     * \fn Seance_tennis(String id_seance_tennis, float premier_service, float deuxieme_service, float troisieme_service, int nb_set, char issue_match, float vitesse_moy_service, Individu individu)
     * \brief Constructeur de Seance_tennis
     * \param [in] id_seance_tennis Clé primaire de la table Seance_tennis (Type String)
     * \param [in] premier_service Premier Service meilleur service (Type Float)
     * \param [in] deuxieme_service Deuxieme Service meilleur service  (Type Float)
     * \param [in] troisieme_service Troisième Service meilleur service (Type Float)
     * \param [in] nb_set Nombre de Set durant le match (Type Integer)
     * \param [in] issue_match Issue du match (Type Character)
     * \param [in] vitesse_moy_service Vitesse moyenne des meilleurs services durant ce match (Type Float)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     */
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

    /**
     * \fn String getId_seance_tennis()
     * \brief Fonction qui retourne l'identifiant de la séance de tennis
     * \return Retourne un String avec l'identifiant de la séance de tennis
     */
    public String getId_seance_tennis() {
        return id_seance_tennis;
    }

    /**
     * \fn void setId_seance_tennis(String id_seance_tennis)
     * \brief Fonction qui modifie l'identifiant de la séance de tennis
     * \param [in] id_seance_tennis Clé primaire de la table Seance_tennis (Type String)
     */
    public void setId_seance_tennis(String id_seance_tennis) {
        this.id_seance_tennis = id_seance_tennis;
    }

    /**
     * \fn float getPremier_service()
     * \brief Fonction qui retourne le premier meilleur service de la séance de tennis
     * \return Retourne un Float avec le premier meilleur service
     */
    public float getPremier_service() {
        return premier_service;
    }

    /**
     * \fn void setPremier_service(float premier_service)
     * \brief Fonction qui modifie le premier meilleur service de la séance de tennis
     * \param [in] premier_service Premier Service meilleur service (Type Float)
     */
    public void setPremier_service(float premier_service) {
        this.premier_service = premier_service;
    }

    /**
     * \fn float getDeuxieme_service()
     * \brief Fonction qui retourne le deuxième meilleur service de la séance de tennis
     * \return Retourne un Float avec le deuxième meilleur service
     */
    public float getDeuxieme_service() {
        return deuxieme_service;
    }

    /**
     * \fn void setDeuxieme_service(float deuxieme_service)
     * \brief Fonction qui modifie le deuxième meilleur service de la séance de tennis
     * \param [in] deuxieme_service Deuxieme Service meilleur service  (Type Float)
     */
    public void setDeuxieme_service(float deuxieme_service) {
        this.deuxieme_service = deuxieme_service;
    }

    /**
     * \fn float getTroisieme_service()
     * \brief Fonction qui retourne le troisième meilleur service de la séance de tennis
     * \return Retourne un Float avec le troisième meilleur service
     */
    public float getTroisieme_service() {
        return troisieme_service;
    }

    /**
     * \fn void void setTroisieme_service(float troisieme_service)
     * \brief Fonction qui modifie le troisième meilleur service de la séance de tennis
     * \param [in] troisieme_service Troisième Service meilleur service (Type Float)
     */
    public void setTroisieme_service(float troisieme_service) {
        this.troisieme_service = troisieme_service;
    }

    /**
     * \fn int getNb_set()
     * \brief Fonction qui retourne le nombre de set de la séance de tennis
     * \return Retourne un Integer avec le nombre de set
     */
    public int getNb_set() {
        return nb_set;
    }

    /**
     * \fn void setNb_set(int nb_set)
     * \brief Fonction qui modifie le nombre de set de la séance de tennis
     * \param [in] nb_set Nombre de Set durant le match (Type Integer)
     */
    public void setNb_set(int nb_set) {
        this.nb_set = nb_set;
    }

    /**
     * \fn char getIssue_match()
     * \brief Fonction qui retourne l'issue du match de la séance de tennis
     * \return Retourne un Character avec l'issue du match
     */
    public char getIssue_match() {
        return issue_match;
    }

    /**
     * \fn void setIssue_match(char issue_match)
     * \brief Fonction qui modifie l'issue du match de la séance de tennis
     * \param [in] issue_match Issue du match (Type Character)
     */
    public void setIssue_match(char issue_match) {
        this.issue_match = issue_match;
    }

    /**
     * \fn float getVitesse_moy_service()
     * \brief Fonction qui retourne la vitesse moyenne des 3 meilleurs services de la séance de tennis
     * \return Retourne un Float avec la vitesse moyenne
     */
    public float getVitesse_moy_service() {
        return vitesse_moy_service;
    }

    /**
     * \fn void setVitesse_moy_service(float vitesse_moy_service)
     * \brief Fonction qui modifie la vitesse moyenne des 3 meilleurs services de la séance de tennis
     * \param [in] vitesse_moy_service Vitesse moyenne des meilleurs services durant ce match (Type Float)
     */
    public void setVitesse_moy_service(float vitesse_moy_service) {
        this.vitesse_moy_service = vitesse_moy_service;
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
}
