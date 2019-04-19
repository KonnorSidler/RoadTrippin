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

    private String waypointOne;
    private String waypointTwo;
    private String waypointThree;
    private String waypointFour;
    private String waypointFive;


    public static final Finder<Long, TripInfo> find = new Finder<>(TripInfo.class);

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartLocation() {
        return this.startLocation;
    }
    public String getEndLocation() {
        return this.endLocation;
    }

    public long getTripId() {
        return this.tripId;
    }

    public String getWaypointOne() {
        return waypointOne;
    }

    public void setWaypointOne(String waypointOne) {
        this.waypointOne = waypointOne;
    }

    public String getWaypointTwo() {
        return waypointTwo;
    }

    public void setWaypointTwo(String waypointTwo) {
        this.waypointTwo = waypointTwo;
    }

    public String getWaypointThree() {
        return waypointThree;
    }

    public void setWaypointThree(String waypointThree) {
        this.waypointThree = waypointThree;
    }

    public String getWaypointFour() {
        return waypointFour;
    }

    public void setWaypointFour(String waypointFour) {
        this.waypointFour = waypointFour;
    }

    public String getWaypointFive() {
        return waypointFive;
    }

    public void setWaypointFive(String waypointFive) {
        this.waypointFive = waypointFive;
    }
}
