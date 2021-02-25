package model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Amis {
    @Id
    private int no_amis;

    private String id_amis;
    private String id_individu;

    //@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Individu.class)


    public Amis() {    }

    public Amis(int no_amis, String id_amis, String id_individu) {
        this.no_amis = no_amis;
        this.id_amis = id_amis;
        this.id_individu = id_individu;
    }

    public int getNo_amis() {
        return no_amis;
    }

    public void setNo_amis(int no_amis) {
        this.no_amis = no_amis;
    }

    public String getId_amis() {
        return id_amis;
    }

    public void setId_amis(String id_amis) {
        this.id_amis = id_amis;
    }

    public String getId_individu() {
        return id_individu;
    }

    public void setId_individu(String id_individu) {
        this.id_individu = id_individu;
    }
}
