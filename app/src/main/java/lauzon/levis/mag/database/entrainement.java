package lauzon.levis.mag.database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class entrainement {
    private long id;
    private long date;
    private int rating;
    private String infosupp;
    private long refidmodel;
    private int completed = 0;

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

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        //Set Language
        Locale locale = Locale.CANADA_FRENCH;

        //Set Display Format
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM",locale);

        //Set Time
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);

        //Return training in String
        return "Entrainement le " + formatter.format(calendar.getTime());
    }
}
