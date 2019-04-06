package models;

import io.ebean.Model;
import io.ebean.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripInfo extends Model {

    @Id
    private long tripId;

    private String startLocation;

    private String endLocation;

    private final int minStopCount = 1;
    private final int maxStopCount = 5;
    private int stopCount;

    public static final Finder<Long, TripInfo> find = new Finder<>(TripInfo.class);

    public void setStopCount(int count) {
        if((count < minStopCount) || (count > maxStopCount)) {
            throw new IllegalArgumentException("rating value out of range");
        }
        this.stopCount = count;
    }
    //calculation for average trip rating. potentially script
}
