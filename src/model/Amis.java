package model;

public class Amis {
    private String id_amis;
    private String id;

    public Amis() {}

    public Amis(String id_amis, String id) {
        this.id_amis = id_amis;
        this.id = id;
    }

    public String getId_amis() {
        return id_amis;
    }

    public void setId_amis(String id_amis) {
        this.id_amis = id_amis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
