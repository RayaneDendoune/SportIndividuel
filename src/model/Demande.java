package model;

import javax.persistence.*;

/**
 * \file Demande.java
 * \brief Classe qui permet de créer l'objet Demande
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création d'une Demande.
 *
 */
//Table "Demande" pour la base de donnée
@Entity
public class Demande {

    @Id
    private int no_demande;

    private String id_destinataire;

    private String id_expediteur;

    public Demande() { }

    /**
     * \fn Demande(int no_demande, String id_destinataire, String id_expediteur)
     * \brief Constructeur de Demande
     * \param [in] no_demande Clé primaire de la table Demande (Type Integer)
     * \param [in] id_destinataire Identifiant du destinataire (Type String)
     * \param [in] id_expediteur Identifiant de l'expéditeur (Type String)
     */
    public Demande(int no_demande, String id_destinataire, String id_expediteur) {
        this.no_demande = no_demande;
        this.id_destinataire = id_destinataire;
        this.id_expediteur = id_expediteur;
    }

    /**
     * \fn int getNo_demande()
     * \brief Fonction qui retourne le numéro de Demande
     * \return Retourne un Integer avec le numéro de Demande
     */
    public int getNo_demande() { return no_demande; }

    /**
     * \fn void setNo_demande(int no_demande)
     * \brief Fonction qui modifie le numéro de Demande
     * \param [in] no_demande Clé primaire de la table Demande (Type Integer)
     */
    public void setNo_demande(int no_demande) { this.no_demande = no_demande; }

    /**
     * \fn String getId_destinataire()
     * \brief Fonction qui retourne l'identifiant du destinataire
     * \return Retourne un String avec l'identifiant du destinataire
     */
    public String getId_destinataire() { return id_destinataire; }

    /**
     * \fn void setId_destinataire(String id_destinataire)
     * \brief Fonction qui modifie l'identifiant du destinataire
     * \param [in] id_destinataire Identifiant du destinataire (Type String)
     */
    public void setId_destinataire(String id_destinataire) { this.id_destinataire = id_destinataire; }

    /**
     * \fn String getId_expediteur()
     * \brief Fonction qui retourne l'identifiant de l'expéditeur
     * \return Retourne un String avec l'identifiant de l'expéditeur
     */
    public String getId_expediteur() { return id_expediteur; }

    /**
     * \fn void setId_expediteur(String id_expediteur)
     * \brief Fonction qui modifie l'identifiant de l'expéditeur
     * \param [in] id_expediteur Identifiant de l'expéditeur (Type String)
     */
    public void setId_expediteur(String id_expediteur) { this.id_expediteur = id_expediteur; }


}
