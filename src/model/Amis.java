package model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Amis {
    @Id
    private String id_amis;


    //@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)
    private String id_individu;

   // @OneToMany(mappedBy = "amis")
    //private ArrayList<Individu> individus;

    public void setId_individu(String id_individu) {
        this.id_individu = id_individu;
    }

   // public void setIndividus(ArrayList<Individu> individus) {
   //     this.individus = individus;
   // }

    public String getId_individu() {
        return id_individu;
    }

    //public ArrayList<Individu> getIndividus() {
      //  return individus;
    //}

    public Amis() {}

    public Amis(String id_amis, String id_individu) {
        this.id_amis = id_amis;
        this.id_individu = id_individu;
    }

    public String getId_amis() {
        return id_amis;
    }

    public void setId_amis(String id_amis) {
        this.id_amis = id_amis;
    }

    public String getId() {
        return id_individu;
    }

    public void setId(String id_individu) {
        this.id_individu = id_individu;
    }
}
