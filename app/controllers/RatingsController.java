package controllers;

import models.*;
import play.libs.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.userSettings;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.inject.Inject;
import java.util.List;

public class RatingsController extends Controller {

    public Result addRating(Http.Request request) {
        JsonNode json = request.body().asJson();
        TripRating rating = new TripRating();
        rating.setTripRating(Integer.parseInt(json.findPath("trip_rating").textValue()));
        rating.setTripId(Long.parseLong(json.findPath("trip_id").textValue()));
        rating.save();
        return ok("Rating saved!");
    }

    public Result getRatings() {
        List<TripRating> trips = TripRating.find.all();
        return ok(Json.toJson(trips));
    }

}
