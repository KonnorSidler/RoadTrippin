package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripInfo extends Model {

    @Id
    public long tripId;

    public String startLocation;

    public String endLocation;

    public int stopCount;

    public static final Finder<Long, TripInfo> find = new Finder<>(TripInfo.class);

    //calculation for average trip rating. potentially script
}