package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripRating extends Model {

    @Id
    private long tripRatingID;

    private int tripRating;
    private long tripID;


    public static final Finder<Long, TripRating> find = new Finder<>(TripRating.class);


    public TripRating(long tripRatingID, int tripRating, long tripID) {
        this.tripRatingID = tripRatingID;
        this.tripRating = tripRating;
        this.tripID = tripID;
    }

}
