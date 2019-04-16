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
        Form<UserAccount> userForm = formFactory.form(UserAccount.class);
        Form<TripInfo> tripForm = formFactory.form(TripInfo.class);
        Form<TripRating> tripRating = formFactory.form(TripRating.class);

        return ok(index.render(userForm, tripForm));
    }








    public Result saveRouteInfo(Http.Request request) {
        JsonNode json = request.body().asJson();
        TripInfo trip = new TripInfo();
        trip.setStartLocation(json.findPath("start").textValue());
        trip.setEndLocation(json.findPath("end").textValue());
        trip.save();
        return ok(Long.toString(trip.getTripId()) + "n");
    }


    public Result getRoutes() {
        List<TripInfo> trips = TripInfo.find.all();
        return ok(Json.toJson(trips));
    }




    public Result testLink(Http.Request request) {

      return ok("Got it!" + request.body().asJson());

    }

    

}
