package lauzon.levis.mag.database;


public class exercice {
    private long id;
    private String nom;
    private long refidmodel;
    private String goal;

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

    public long getRefidmodel() {
        return refidmodel;
    }

    public void setRefidmodel(long refidmodel) {
        this.refidmodel = refidmodel;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return nom;
    }
}
