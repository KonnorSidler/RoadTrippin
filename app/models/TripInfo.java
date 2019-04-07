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


    public static final Finder<Long, TripInfo> find = new Finder<>(TripInfo.class);

    public TripInfo(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public String getStartLocation() {
        return this.startLocation;
    }
    public String getEndLocation() {
        return this.endLocation;
    }


}
