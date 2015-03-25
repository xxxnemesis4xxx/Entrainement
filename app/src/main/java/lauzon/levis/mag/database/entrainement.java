package lauzon.levis.mag.database;

public class entrainement {
    private long id;
    private long date;
    private int rating;
    private String infosupp;
    private long refidmodel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRefidmodel() {
        return refidmodel;
    }

    public void setRefidmodel(long refidmodel) {
        this.refidmodel = refidmodel;
    }

    public String getInfosupp() {
        return infosupp;
    }

    public void setInfosupp(String infosupp) {
        this.infosupp = infosupp;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.valueOf(date);
    }
}
