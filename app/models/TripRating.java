package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripRating extends Model {

    @Id
    private long tripRatingID;

    private long tripID;

    private final int minRateValue = 1;
    private final int maxRateValue = 5;
    private int rating; //add constraints

    public static final Finder<Long, TripRating> find = new Finder<>(TripRating.class);

    public void setRating(int value) {
        if((value < minRateValue) || (value > maxRateValue)) {
            throw new IllegalArgumentException("rating value out of range");
        }
        this.rating = value;
    }
}
