package model;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * \file Amis.java
 * \brief Classe qui permet de créer l'objet Amis
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'Amis.
 *
 */
//Table "Amis" pour la base de donnée
@Entity
public class Amis {
    @Id
    private int no_amis;

    private String id_amis;
    private String id_individu;

    //@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)


    public Amis() {    }

    /**
     * \fn Amis(int no_amis, String id_amis, String id_individu)
     * \brief Constructeur d'Amis
     * \param [in] no_amis Clé primaire de la table Amis (Type Integer)
     * \param [in] id_amis Identifiant de l'ami (Type String)
     * \param [in] id_individu Identifiant de l'individu (Type String)
     */
    public Amis(int no_amis, String id_amis, String id_individu) {
        this.no_amis = no_amis;
        this.id_amis = id_amis;
        this.id_individu = id_individu;
    }

    /**
     * \fn int getNo_amis()
     * \brief Fonction qui retourne le numéro d'Amis
     * \return Retourne un Integer avec le numéro d'Amis
     */
    public int getNo_amis() {
        return no_amis;
    }

    /**
     * \fn void setNo_amis(int no_amis)
     * \brief Fonction qui modifie le numéro d'Amis
     * \param [in] no_amis Clé primaire de la table Amis (Type Integer)
     */
    public void setNo_amis(int no_amis) {
        this.no_amis = no_amis;
    }

    /**
     * \fn String getId_amis()
     * \brief Fonction qui retourne l'identifiant de l'Amis
     * \return Retourne un String avec l'identifiant de l'Amis
     */
    public String getId_amis() {
        return id_amis;
    }

    /**
     * \fn void setId_amis(String id_amis)
     * \brief Fonction qui modifie l'identifiant de l'Amis
     * \param [in] id_amis Identifiant de l'ami (Type String)
     */
    public void setId_amis(String id_amis) {
        this.id_amis = id_amis;
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
     * \fn void setId_individu(String id_individu)
     * \brief Fonction qui modifie l'identifiant de l'individu
     * \param [in] id_individu Identifiant de l'individu (Type String)
     */
    public void setId_individu(String id_individu) {
        this.id_individu = id_individu;
    }
}
