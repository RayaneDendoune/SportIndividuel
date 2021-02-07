package model;

public class Amis {
    private String id_amis;
    private String id_individu;

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
