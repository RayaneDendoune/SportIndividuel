package model;

import javax.persistence.*;
/**
 * \file Seance_cyclisme.java
 * \brief Classe qui permet de créer l'objet Seance_cyclisme
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création d'une séance de cyclisme
 *
 */
//Table "Seance_cyclisme" pour la base de données
@Entity
public class Seance_cyclisme {
    @Id
    private String id_seance_cyclisme;

    private String niveau_activite_physique;
    private float poids;
    private String objectif_seance;
    private int depense_energetique;
    private int besoin_proteine;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)
    private Individu individu;

    /*private String id_cycliste; */

    public Seance_cyclisme() {}

    /**
     * \fn Seance_cyclisme(String id_seance_cyclisme, String niveau_activite_physique, float poids, String objectif_seance, int depense_energetique, int besoin_proteine, Individu individu)
     * \brief Constructeur de Seance_cyclisme
     * \param [in] id_seance_cyclisme Clé primaire de la table Seance_cyclisme (Type String)
     * \param [in] niveau_activite_physique Niveau d'activité physique durant la séance (type String)
     * \param [in] poids Poids actuel de l'individu (Type Float)
     * \param [in] objectif_seance Objectif de la séance de l'individu (type String)
     * \param [in] depense_energetique Dépenses énergétiques faite par l'individu durant sa séance (type Integer)
     * \param [in] besoin_proteine Besoins en protéïnes de l'individu après sa séance (type Integer)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     *
     */
    public Seance_cyclisme(String id_seance_cyclisme, String niveau_activite_physique, float poids, String objectif_seance, int depense_energetique, int besoin_proteine, Individu individu) {
        this.id_seance_cyclisme = id_seance_cyclisme;
        this.niveau_activite_physique = niveau_activite_physique;
        this.poids = poids;
        this.objectif_seance = objectif_seance;
        this.depense_energetique = depense_energetique;
        this.besoin_proteine = besoin_proteine;
        this.individu = individu;
    }

    /**
     * \fn String getId_seance_cyclisme()
     * \brief Fonction qui retourne l'identifiant de la séance de cyclisme
     * \return Retourne un String avec l'identifiant de la séance de cyclisme
     */
    public String getId_seance_cyclisme() {
        return id_seance_cyclisme;
    }

    /**
     * \fn void setId_seance_cyclisme(String id_seance_cyclisme)
     * \brief Fonction qui modifie l'identifiant de la séance de cyclisme
     * \param [in] id_seance_cyclisme Clé primaire de la table Seance_cyclisme (Type String)
     */
    public void setId_seance_cyclisme(String id_seance_cyclisme) {
        this.id_seance_cyclisme = id_seance_cyclisme;
    }

    /**
     * \fn String getNiveau_activite_physique()
     * \brief Fonction qui retourne le niveau d'activité physique de la séance de cyclisme
     * \return Retourne un String avec le niveau d'activité physique
     */
    public String getNiveau_activite_physique() {
        return niveau_activite_physique;
    }

    /**
     * \fn void setNiveau_activite_physique(String niveau_activite_physique)
     * \brief Fonction qui modifie le niveau d'activité physique de la séance de cyclisme
     * \param [in] niveau_activite_physique Niveau d'activité physique durant la séance (type String)
     */
    public void setNiveau_activite_physique(String niveau_activite_physique) {
        this.niveau_activite_physique = niveau_activite_physique;
    }

    /**
     * \fn float getPoids()
     * \brief Fonction qui retourne le poids de l'utilisateur
     * \return Retourne un Float avec le poids de l'utilisateur
     */
    public float getPoids() {
        return poids;
    }

    /**
     * \fn  void setPoids(float poids)
     * \brief Fonction qui modifie le poids de l'utilisateur
     * \param [in] poids Poids actuel de l'individu (Type Float)
     */
    public void setPoids(float poids) {
        this.poids = poids;
    }

    /**
     * \fn String getObjectif_seance()
     * \brief Fonction qui retourne l'objectif de la séance de cyclisme
     * \return Retourne un String avec l'objectif de la séance
     */
    public String getObjectif_seance() {
        return objectif_seance;
    }

    /**
     * \fn void setObjectif_seance(String objectif_seance)
     * \brief Fonction qui modifie l'objectif de la séance de cyclisme
     * \param [in] objectif_seance Objectif de la séance de l'individu (type String)
     */
    public void setObjectif_seance(String objectif_seance) {
        this.objectif_seance = objectif_seance;
    }

    /**
     * \fn int getDepense_energetique()
     * \brief Fonction qui retourne les dépenses énergétiques de l'individu
     * \return Retourne un Integer avec les dépenses énergétiques
     */
    public int getDepense_energetique() {
        return depense_energetique;
    }

    /**
     * \fn void setDepense_energetique(int depense_energetique)
     * \brief Fonction qui modifie les dépenses énergétiques de l'individu
     * \param [in] depense_energetique Dépenses énergétiques faite par l'individu durant sa séance (type Integer)
     */
    public void setDepense_energetique(int depense_energetique) {
        this.depense_energetique = depense_energetique;
    }

    /**
     * \fn int getBesoin_proteine()
     * \brief Fonction qui retourne les besoins en protéïnes de l'individu
     * \return Retourne un Integer avec les besoins en protéïnes
     */
    public int getBesoin_proteine() {
        return besoin_proteine;
    }

    /**
     * \fn void setDepense_energetique(int depense_energetique)
     * \brief Fonction qui modifie les besoins en protéïnes de l'individu
     * \param [in] besoin_proteine Besoins en protéïnes de l'individu après sa séance (type Integer)
     */
    public void setBesoin_proteine(int besoin_proteine) {
        this.besoin_proteine = besoin_proteine;
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
