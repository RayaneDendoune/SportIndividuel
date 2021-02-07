package model;

public class Demande {
    private String id_destinataire;
    private String id;

    public Demande() { }

    public Demande(String id_destinataire, String id) {
        this.id_destinataire = id_destinataire;
        this.id = id;
    }

    public String getId_destinataire() {
        return id_destinataire;
    }

    public void setId_destinataire(String id_destinataire) {
        this.id_destinataire = id_destinataire;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
