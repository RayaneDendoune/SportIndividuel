package model;

import javax.persistence.*;

//Table "Demande" pour la base de donnée
@Entity
public class Demande {

    @Id
    private int no_demande;

    private String id_destinataire;

    private String id_expediteur;

    public Demande() { }

    public Demande(int no_demande, String id_destinataire, String id_expediteur) {
        this.no_demande = no_demande;
        this.id_destinataire = id_destinataire;
        this.id_expediteur = id_expediteur;
    }

    public int getNo_demande() { return no_demande; }

    public void setNo_demande(int no_demande) { this.no_demande = no_demande; }

    public String getId_destinataire() { return id_destinataire; }

    public void setId_destinataire(String id_destinataire) { this.id_destinataire = id_destinataire; }

    public String getId_expediteur() { return id_expediteur; }

    public void setId_expediteur(String id_expediteur) { this.id_expediteur = id_expediteur; }


}
