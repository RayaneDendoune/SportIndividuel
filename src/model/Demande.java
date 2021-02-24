package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(Individu.class)
@Entity
public class Demande {

    @Id String id_destinataire;
    @Id String id_expediteur;

    public Demande() { }

    public Demande(String id_destinataire, String id_expediteur) {
        this.id_destinataire = id_destinataire;
        this.id_expediteur = id_expediteur;
    }

    public String getId_destinataire() {
        return id_destinataire;
    }

    public void setId_destinataire(String id_destinataire) {
        this.id_destinataire = id_destinataire;
    }

    public String getId() {
        return id_expediteur;
    }

    public void setId(String id_expediteur) {
        this.id_expediteur = id_expediteur;
    }
}
