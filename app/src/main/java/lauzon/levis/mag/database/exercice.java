package lauzon.levis.mag.database;


public class exercice {
    private long id;
    private String nom;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return nom;
    }
}
