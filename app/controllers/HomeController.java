package controllers;

import models.TripRating;
import models.UserAccount;
import play.mvc.*;
import views.html.*;
import models.TripInfo;
import play.data.FormFactory;
import play.data.Form;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import io.ebean.Model;
import play.libs.Json;
import play.libs.Json.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject
    FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.homepage.render());
    }



    public Result tripcreation() {

        return ok(index.render());
    }








    public Result saveRouteInfo(Http.Request request) {
        JsonNode json = request.body().asJson();
        TripInfo trip = new TripInfo();
        trip.setStartLocation(json.findPath("start").textValue());
        trip.setEndLocation(json.findPath("end").textValue());
        trip.setWaypointOne(json.findPath("waypointOne").textValue());
        trip.setWaypointTwo(json.findPath("waypointTwo").textValue());
        trip.setWaypointThree(json.findPath("waypointThree").textValue());
        trip.setWaypointFour(json.findPath("waypointFour").textValue());
        trip.setWaypointFive(json.findPath("waypointFive").textValue());
        trip.save();
        return ok(Long.toString(trip.getTripId()) + "n");
    }


    public Result getRoutes() {
        List<TripInfo> trips = TripInfo.find.all();
        return ok(Json.toJson(trips));
    }



    public Result getRoute(Long routeID) {
      return ok(createdTrip.render(routeID));
    }

    public Result getRouteFromDB(Http.Request request) {
        System.out.println("Got the getRouteFromDB");
        JsonNode json = request.body().asJson();
        Long routeID = Long.parseLong(json.findPath("route").textValue());
        TripInfo trip = TripInfo.find.byId(routeID);

        return ok(Json.toJson(trip));
    }

    public Result generateRandomRouteList(Http.Request request) {
      int resultCount = 5;
      List<TripInfo> trips = new ArrayList<TripInfo>();
      List<TripInfo> allTrips = TripInfo.find.all();
      int count = allTrips.size();
      for (int i = 0; i < resultCount; i++) {
        int randomRouteIdInt = (int) (Math.random() * count) + 1;
        Long randomRouteId = new Long(randomRouteIdInt);
        TripInfo newTrip = TripInfo.find.byId(randomRouteId);
        trips.add(newTrip);
      }

      return ok(Json.toJson(trips));

    }

    public Result generateRecentRouteList(Http.Request request) {
      int resultCount = 5;
      List<TripInfo> trips = new ArrayList<TripInfo>();
      List<TripInfo> allTrips = TripInfo.find.all();
      int size = allTrips.size();
      Long lastRouteId = new Long(size);

      if(size < 5) {
          long count = 1;
          for(int i = 0; i < size; i++) {
            trips.add(TripInfo.find.byId(count));
            count++;
          }
      }
      else {
          for (int i = 0; i < resultCount; i++) {
              trips.add(TripInfo.find.byId(lastRouteId));
              lastRouteId--;
          }
      }


      return ok(Json.toJson(trips));
    }




    public Result testLink(Http.Request request) {
      return ok("Got it!" + request.body().asJson());
    }



}
