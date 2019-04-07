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
    private long tripId;


    public static final Finder<Long, TripRating> find = new Finder<>(TripRating.class);

    public void setTripRating(int tripRating) {
        this.tripRating = tripRating;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }


    public int getTripRating() {
        return this.tripRating;
    }

    public long getTripRatingId() {
        return this.tripId;
    }

}
