package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripRating extends Model {

    @Id
    public long tripRatingID;

    public long tripID;

    public int rating; //add constraints

    public static final Finder<Long, TripRating> find = new Finder<>(TripRating.class);
}
