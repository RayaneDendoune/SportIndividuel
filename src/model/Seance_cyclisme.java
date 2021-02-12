package model;

import javax.persistence.*;

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

    public Seance_cyclisme(String id_seance_cyclisme, String niveau_activite_physique, float poids, String objectif_seance, int depense_energetique, int besoin_proteine, Individu individu) {
        this.id_seance_cyclisme = id_seance_cyclisme;
        this.niveau_activite_physique = niveau_activite_physique;
        this.poids = poids;
        this.objectif_seance = objectif_seance;
        this.depense_energetique = depense_energetique;
        this.besoin_proteine = besoin_proteine;
        this.individu = individu;
    }

    public String getId_seance_cyclisme() {
        return id_seance_cyclisme;
    }

    public void setId_seance_cyclisme(String id_seance_cyclisme) {
        this.id_seance_cyclisme = id_seance_cyclisme;
    }

    public String getNiveau_activite_physique() {
        return niveau_activite_physique;
    }

    public void setNiveau_activite_physique(String niveau_activite_physique) {
        this.niveau_activite_physique = niveau_activite_physique;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getObjectif_seance() {
        return objectif_seance;
    }

    public void setObjectif_seance(String objectif_seance) {
        this.objectif_seance = objectif_seance;
    }

    public int getDepense_energetique() {
        return depense_energetique;
    }

    public void setDepense_energetique(int depense_energetique) {
        this.depense_energetique = depense_energetique;
    }

    public int getBesoin_proteine() {
        return besoin_proteine;
    }

    public void setBesoin_proteine(int besoin_proteine) {
        this.besoin_proteine = besoin_proteine;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }
}
