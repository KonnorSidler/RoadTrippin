package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripRating extends Model {

    @Id
    private long ratingID;

    private int tripRating;
    private long tripId;


    public static final Finder<Long, TripRating> find = new Finder<>(TripRating.class);

    public void setTripRating(int tripRating) {
        this.tripRating = tripRating;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public long getRatingID() {
      return this.ratingID;
    }


    public int getTripRating() {
        return this.tripRating;
    }

    public long getTripId() {
        return this.tripId;
    }

}
