package model;

public class Seance_cyclisme {

    private String id_seance_cyclisme;
    private float niveau_activite_physique;
    private float poids;
    private String objectif_seance;
    private float depense_energetique;
    private int besoin_proteine;
    private String id_cycliste;

    public Seance_cyclisme() {}

    public Seance_cyclisme(String id_seance_cyclisme, float niveau_activite_physique, float poids, String objectif_seance, float depense_energetique, int besoin_proteine, String id_cycliste) {
        this.id_seance_cyclisme = id_seance_cyclisme;
        this.niveau_activite_physique = niveau_activite_physique;
        this.poids = poids;
        this.objectif_seance = objectif_seance;
        this.depense_energetique = depense_energetique;
        this.besoin_proteine = besoin_proteine;
        this.id_cycliste = id_cycliste;
    }

    public String getId_seance_cyclisme() {
        return id_seance_cyclisme;
    }

    public void setId_seance_cyclisme(String id_seance_cyclisme) {
        this.id_seance_cyclisme = id_seance_cyclisme;
    }

    public float getNiveau_activite_physique() {
        return niveau_activite_physique;
    }

    public void setNiveau_activite_physique(float niveau_activite_physique) {
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

    public float getDepense_energetique() {
        return depense_energetique;
    }

    public void setDepense_energetique(float depense_energetique) {
        this.depense_energetique = depense_energetique;
    }

    public int getBesoin_proteine() {
        return besoin_proteine;
    }

    public void setBesoin_proteine(int besoin_proteine) {
        this.besoin_proteine = besoin_proteine;
    }

    public String getId_cycliste() {
        return id_cycliste;
    }

    public void setId_cycliste(String id_cycliste) {
        this.id_cycliste = id_cycliste;
    }
}
